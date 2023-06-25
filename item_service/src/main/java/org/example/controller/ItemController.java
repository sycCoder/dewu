package org.example.controller;

import com.alibaba.fastjson2.JSONArray;
import org.example.entity.Item;
import org.example.entity.ItemClass;
import org.example.entity.ItemSale;
import org.example.entity.ItemSubclass;
import org.example.service.ItemService;
import org.example.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService = new ItemServiceImpl();

    @GetMapping("/all")
    List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping("/pagination/{limit}/{offset}")
    List<Item> getPaginationItems(@PathVariable Integer limit, @PathVariable Integer offset) {
        return itemService.getPaginationItems(limit, offset);
    }

    @GetMapping("/{itemId}")
    Map<String, Object> getItemByItemId(@PathVariable Integer itemId) {
        return itemService.getItemByItemId(itemId);
    }

    @GetMapping("/by/subclass/{id}")
    List<Item> getItemsBySubclass(@PathVariable Integer id) {
        return itemService.getItemsBySubclass(id);
    }

    @GetMapping("/by/class/{id}")
    List<Item> getItemsByClass(@PathVariable Integer id) {
        return itemService.getItemsByClass(id);
    }

    @GetMapping("/subclass/{itemClassId}")
    List<ItemSubclass> getSubclassesByItemClassId(@PathVariable Integer itemClassId) {
        return itemService.getSubclassesByItemClassId(itemClassId);
    }

    @GetMapping("/subclass")
    List<ItemSubclass> getAllSubclasses() {
        return itemService.getAllSubclasses();
    }

    @GetMapping("/class")
    List<ItemClass> getAllClasses() {
        return itemService.getAllClasses();
    }

    @GetMapping("/sales/{itemId}")
    public JSONArray getItemsOnSale(@PathVariable("itemId") Integer itemId) {
        return itemService.getClientItemSales(itemId);
    }

    @GetMapping("/sale/{itemSaleId}")
    public ItemSale getItemSaleById(@PathVariable Integer itemSaleId) {
        return itemService.getItemSaleById(itemSaleId);
    }

    @PutMapping("/processing/{itemSaleId}")
    public Boolean processingItemSale(@PathVariable Integer itemSaleId) {
        return itemService.processingItemSale(itemSaleId);
    }

    @PutMapping("/return/{itemSaleId}")
    public Boolean returnItemSale(@PathVariable Integer itemSaleId) {
        return itemService.returnItemSale(itemSaleId);
    }
}