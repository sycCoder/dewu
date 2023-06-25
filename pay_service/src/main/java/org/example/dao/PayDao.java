package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.Pay;

@Mapper
public interface PayDao {
    @Insert("insert into dewu.payment(order_id, payment, state, way) values (#{orderId}, #{payment}, false, #{way})")
    @Options(useGeneratedKeys = true, keyProperty = "payId")
    void createPayment(Pay pay);

    @Update("update dewu.payment set state = true where pay_id = #{payId}")
    Boolean pay(Integer payId);

    @Select("select order_id from dewu.payment where pay_id = #{payId}")
    Integer getOrderIdByPayId(Integer payId);

    @Select("select state from dewu.payment where order_id = #{orderId}")
    Boolean getPaymentStateByOrderId(Integer orderId);

    @Select("select state from dewu.payment where pay_id = #{payId}")
    Boolean getPaymentStateByPayId(Integer payId);


}
