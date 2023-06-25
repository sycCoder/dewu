package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.Address;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author shaoyichao
 */
@Mapper
public interface AddressDao {
    @Select("select county, address_id, address, address_default from dewu.address where user_id = #{userId} order by address_id desc")
    @Result(column = "address_id", property = "addressId")
    @Result(column = "address_default", property = "addressDefault")
    List<Address> getAllAddressByUserId(Integer userId);

    @Select("select county, address_id, address, address_default from dewu.address where user_id = #{userId} order by address_id desc limit 1")
    @Result(column = "address_id", property = "addressId")
    @Result(column = "address_default", property = "addressDefault")
    Address getOrderAddressByUserId(Integer userId);


    @Update("update dewu.address set county = #{county}, address = #{address} where user_id = #{userId}")
    Boolean updateAddress(String county, String address, Integer userId);

    @Delete("delete from dewu.address where address_id = #{addressId}")
    Boolean deleteAddress(Integer addressId);

    @Insert("insert into dewu.address (user_id, county, address, address_default) values (#{userId}, #{county}, #{address}, #{addressDefault})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId")
    void addAddress(Address address);

    @Update("update dewu.address set address_default = #{addressDefault} where address_id = #{addressId};")
    Boolean changeDefaultAddress(Boolean addressDefault, Integer addressId);

    @Select("select address_id from dewu.address where user_id = #{userId} and address_default = true")
    Integer getDefaultAddressId(Integer userId);

    @Select("select * from dewu.address where user_id = #{userId} and address_default = true")
    @Result(column = "address_id", property = "addressId")
    @Result(column = "address_default", property = "addressDefault")
    Address getDefaultAddress(Integer userId);
}
