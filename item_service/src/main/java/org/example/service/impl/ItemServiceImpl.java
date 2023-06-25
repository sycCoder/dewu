package org.example.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.example.dao.ItemDao;
import org.example.dao.ItemSaleDao;
import org.example.dao.ItemSizeDao;
import org.example.entity.*;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    ItemSizeDao itemSizeDao;
    @Autowired
    ItemSaleDao itemSaleDao;

    @Override
    public List<Item> getAllItem(){
        return itemDao.getAllItem();
    }

    @Override
    public List<Item> getItemsBySubclass(Integer itemSubclassId) {
        return itemDao.getItemsBySubclass(itemSubclassId);
    }

    @Override
    public List<Item> getItemsByClass(Integer itemClassId){
        return itemDao.getItemsByClass(itemClassId);
    }

    @Override
    public List<ItemSubclass> getSubclassesByItemClassId(Integer itemClassId) {
        return itemDao.getSubclassesByItemClassId(itemClassId);
    }

    @Override
    public List<ItemSubclass> getAllSubclasses() {
        return itemDao.getAllSubclasses();
    }

    @Override
    public List<ItemClass> getAllClasses() {
        return itemDao.getAllClasses();
    }

    @Override
    public Map<String, Object> getItemByItemId(Integer itemId) {
        Item item = itemDao.getItemByItemId(itemId);
        JSONObject json = JSONObject.from(item);
        if (item.getInfoClass() == 0) {
            ShoeInfo shoeInfo = itemDao.findShoeInfoByItemId(itemId);
            json.put("info", shoeInfo);
        } else if (item.getInfoClass() == 1){
            GarmentInfo garmentInfo = itemDao.findGarmentInfoByItemId(itemId);
            json.put("info", garmentInfo);
        }
        return json;
    }

    @Override
    public JSONArray getClientItemSales(Integer itemId) {
        List<ItemSize> itemSizeList = itemSizeDao.getAllItemSizeByItemId(itemId);
        JSONArray array = new JSONArray();
        for (ItemSize i : itemSizeList) {
            ItemSale itemSale = itemSaleDao.getLowestItemSaleByItemSizeId(i.getItemSizeId(), 0);
            JSONObject itemSaleJSON = new JSONObject();
            if (itemSale != null) {
                itemSaleJSON.put("price", itemSale.getPrice());
                itemSaleJSON.put("remain", true);
                itemSaleJSON.put("itemSaleId", itemSale.getItemSaleId());
            } else {
                itemSaleJSON.put("price", null);
                itemSaleJSON.put("remain", false);
            }
            itemSaleJSON.put("size", i.getSize());
            array.add(itemSaleJSON);
        }
        return array;
    }

    @Override
    public ItemSale getItemSaleById(Integer itemSaleId) {
        return itemDao.getItemSaleById(itemSaleId);
    }

    @Override
    public List<Item> getPaginationItems(Integer limit, Integer offset) {
        return itemDao.getPaginationItems(limit, offset * limit);
    }

    @Override
    public Boolean processingItemSale(Integer itemSaleId) {
        return itemSaleDao.changeItemSaleState(itemSaleId, 1);
    }

    @Override
    public Boolean returnItemSale(Integer itemSaleId) {
        return itemSaleDao.changeItemSaleState(itemSaleId, 0);
    }
}
