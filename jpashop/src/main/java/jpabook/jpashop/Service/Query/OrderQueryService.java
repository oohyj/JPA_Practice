//package jpabook.jpashop.Service.Query;
//
//import jpabook.jpashop.api.OrderApiController;
//import jpabook.jpashop.domain.Order;
//import jpabook.jpashop.repository.OrderRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static java.util.stream.Collectors.toList;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class OrderQueryService {
//
//    private final OrderRepository orderRepository;
//    public List<OrderApiController.OrderDto> orderV3(){
//        List<Order> orders = orderRepository.findAllWithItem();
//        /** OrderDto도 이 패키지로 가져오고 OrderApiController에서는 return orderQueryService.orderV3()을 반환하게 하면 됨
//         * */
//        List<OrderApiController.OrderDto> result = orders.stream()
//                .map(o -> new OrderApiController.OrderDto(o))
//                .collect(toList());
//
//        return result;
//    }
//}
