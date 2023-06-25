package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.Order;

import java.util.List;

@Mapper
public interface OrderDao {
    @Select("select * from dewu.order where user_id = #{userId}")
    @Results({
            @Result(column = "item_sale_id", property = "itemSaleId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_state", property = "orderState"),
    })
    List<Order> getAllOrdersByUserId(Integer userId);

    @Select("select * from dewu.order where order_id = #{ordeId}")
    @Results({
            @Result(column = "item_sale_id", property = "itemSaleId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_state", property = "orderState"),
    })
    Order getOrderByOrderId(Integer orderId);

    @Insert("insert into dewu.order (item_sale_id, user_id, create_time, order_state, payment, address, recipient, recipient_phone) values (#{itemSaleId},#{userId},#{createTime},#{orderState},#{payment},#{address},#{recipient},#{recipientPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void createOrder(Order order);

    @Update("update dewu.order set order_state = #{orderState} where order_id = #{orderId}")
    Boolean updateOrderState(Integer orderState, Integer orderId);

    @Select("select payment from dewu.order where order_id = #{orderId}")
    Integer getPaymentByOrderId(Integer orderId);

    @Update("update dewu.order set order_state = 1 where order_id = #{orderId}")
    Boolean cancelOrder(Integer orderId);

    @Select("select * from dewu.order where order_state = 0")
    @Results({
            @Result(column = "item_sale_id", property = "itemSaleId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_state", property = "orderState"),
    })
    List<Order> findAllUnpaidOrders();

    @Select("select * from dewu.order where user_id = #{userId} and order_state = #{orderState}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_state", property = "orderState")
    })
    List<Order> getOrdersByUserIdAndOrderState(Integer userId, Integer orderState);

    @Update("update dewu.order set order_state = 5 where order_id = #{orderId} and order_state between 2 and 4")
    Boolean createRefund(Integer orderId);

    @Update("update dewu.order set order_state = 6 where order_id = #{orderId} and order_state = 5")
    Boolean refundAccessed(Integer orderId);
}
