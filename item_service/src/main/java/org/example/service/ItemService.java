package org.example.service;

import com.alibaba.fastjson2.JSONArray;
import org.example.entity.Item;
import org.example.entity.ItemClass;
import org.example.entity.ItemSale;
import org.example.entity.ItemSubclass;

import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Item> getAllItem();

    List<Item> getItemsBySubclass(Integer itemSubclassId);

    List<Item> getItemsByClass(Integer itemClassId);

    List<ItemSubclass> getSubclassesByItemClassId(Integer itemClassId);

    List<ItemSubclass> getAllSubclasses();

    List<ItemClass> getAllClasses();

    Map<String, Object> getItemByItemId(Integer itemId);

    JSONArray getClientItemSales(Integer itemId);

    ItemSale getItemSaleById(Integer itemSaleId);

    List<Item> getPaginationItems(Integer limit, Integer offset);

    Boolean processingItemSale(Integer itemSaleId);

    Boolean returnItemSale(Integer itemSaleId);
}
