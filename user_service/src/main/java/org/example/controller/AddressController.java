package org.example.controller;

import com.alibaba.fastjson2.JSONObject;
import org.example.entity.Address;
import org.example.service.AddressService;
import org.example.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Autowired
    AddressService addressService = new AddressServiceImpl();

    @GetMapping("/{userId}")
    public List<Address> getAllAddressByUserId(@PathVariable Integer userId) {
        return addressService.getAllAddressByUserId(userId);
    }

    @GetMapping("/default/{userId}")
    public Address getOrderAddressByUserId(@PathVariable Integer userId) {
        return addressService.getOrderAddressByUserId(userId);
    }

    @PostMapping
    public JSONObject addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @DeleteMapping("/{addressId}")
    public JSONObject deleteAddress(@PathVariable Integer addressId) {
        return addressService.deleteAddress(addressId);
    }

    @PutMapping
    public JSONObject updateAddress(@RequestBody JSONObject request) {
        return addressService.updateAddress(request);
    }

    @PutMapping("/default")
    public JSONObject changeDefaultAddress(@RequestBody JSONObject request) throws Exception {
        return addressService.changeDefaultAddress(request);
    }
}
