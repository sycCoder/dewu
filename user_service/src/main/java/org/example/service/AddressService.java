package org.example.service;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressByUserId(Integer userId);

    Address getOrderAddressByUserId(Integer userId);

    JSONObject updateAddress(JSONObject request);

    JSONObject deleteAddress(Integer addressId);

    JSONObject addAddress(Address address);

    JSONObject changeDefaultAddress(JSONObject request) throws Exception;
}
