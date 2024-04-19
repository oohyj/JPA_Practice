package jpabook.jpashop.repository.order.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderItemQueryDto {


    public OrderItemQueryDto(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
    @JsonIgnore //엔티티가 아니라 화면에 뿌리기 위해 만든 dto여서
    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;




}