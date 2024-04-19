package jpabook.jpashop.repository.order.query;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDto() {
        List<OrderQueryDto> result =  findOrders(); //처음에 컬렉션인 orderItems 말고 다른 것만 가져옴, Query 1번 -> N개

        result.forEach(o -> {  //루프를 돌리면서 orderItems를 채워줌
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); //쿼리를 또 만들어서 orderItems를 가져옴, Query N번 -> 1+N 루프를 상품 개수대로 돌기 때문에
            o.setOrderItems(orderItems);
        });
        return result;

    }

    public List<OrderQueryDto> findAllByDto_optimization(){
        List<OrderQueryDto> result = findOrders(); //주문을 다 가지고 옴 , 여기서 쿼리 1번

        List<Long> orderIds = toOrderIds(result);

        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;

    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery( // 위에서 얻은 id 2개를 바로 파라미터 in절로 넣어버림 , 여기서 쿼리 1번
                        "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name , oi.orderPrice , oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i" +
                                " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream() //키는 orderId이고 값은 List<OrderItemQueryDto로 바뀜
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
        return orderItemMap;
    }

    private static List<Long> toOrderIds(List<OrderQueryDto> result) {
        List<Long> orderIds = result.stream() // stream으로 돌리면서
                .map(o -> o.getOrderId()) //OrderQueryDto를 OrderId로 바꿈 -> 그럼 OrderId의 리스트가 됨
                .collect(Collectors.toList()); // 이렇게 되면 지금 들어간 튜플이 2개니까 2개가 나옴
        return orderIds;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name , oi.orderPrice , oi.count)"+
                        " from OrderItem oi"+
                        " join oi.item i"+
                        " where oi.order.id = :orderId",OrderItemQueryDto.class)
                .setParameter("orderId" , orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id , m.name, o.orderDate ,o.status, d.address )" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderFlatDto> findAllByDto_flat() {

        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id , m.name , o.orderDate, o.status , d.address, i.name , oi.orderPrice , oi.count)"+
                        " from Order o"+
                        " join o.member m"+
                        " join o.delivery d"+
                        " join o.orderItems oi"+
                        " join oi.item i", OrderFlatDto.class)
                .getResultList();

    }
}
