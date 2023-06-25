package org.example.service.impl;

import com.alibaba.fastjson2.JSONObject;
import org.example.dao.AddressDao;
import org.example.entity.Address;
import org.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @Override
    public List<Address> getAllAddressByUserId(Integer userId) {
        return addressDao.getAllAddressByUserId(userId);
    }

    @Override
    public Address getOrderAddressByUserId(Integer userId) {
        Address address = addressDao.getDefaultAddress(userId);
        if (address != null) {
            return address;
        } else {
            return addressDao.getOrderAddressByUserId(userId);
        }
    }

    @Override
    public JSONObject updateAddress(JSONObject request) {
        Integer addressId = request.getInteger("addressId");
        String county = request.getString("county");
        String address = request.getString("address");
        Boolean state = addressDao.updateAddress(county, address, addressId);
        JSONObject response = new JSONObject();
        response.put("state", state);
        response.put("msg", state ? "地址修改成功": "地址修改失败");
        return response;
    }

    @Override
    public JSONObject deleteAddress(Integer addressId) {
        JSONObject response = new JSONObject();
        Boolean state = addressDao.deleteAddress(addressId);
        response.put("state", state);
        response.put("msg", state ? "地址删除成功": "地址删除失败");
        return response;
    }

    @Override
    public JSONObject addAddress(Address address) {
        JSONObject response = new JSONObject();
        List<Address> addressList = addressDao.getAllAddressByUserId(address.getUserId());
        address.setAddressDefault(addressList.isEmpty());
        try {
            addressDao.addAddress(address);
            response.put("state", true);
            response.put("addressId", address.getAddressId());
        } catch (Exception e) {
            response.put("state", false);
        }
        return response;
    }

    @Override
    public JSONObject changeDefaultAddress(JSONObject request) throws Exception {
        JSONObject response = new JSONObject();
        Integer userId = request.getInteger("userId");
        Integer addressId = request.getInteger("addressId");
        Integer defaultAddressId = addressDao.getDefaultAddressId(userId);
        if (addressDao.changeDefaultAddress(false, defaultAddressId)) {
            if (addressDao.changeDefaultAddress(true, addressId)){
                response.put("state", true);
                response.put("msg", "修改成功");
            } else {
                if (addressDao.changeDefaultAddress(false, addressId)){
                    response.put("state", false);
                    response.put("msg", "修改失败");
                } else {
                    throw new Exception("Address Default Changed Error");
                }
            }
        } else {
            response.put("state", false);
            response.put("msg", "不存在该地址");
        }
        return response;
    }
}
