package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.*;

import java.util.List;

@Mapper
public interface ItemDao {
    @Select("select * from dewu.item_class")
    @Results({
            @Result(column = "item_class_id", property = "itemClassId"),
            @Result(column = "item_class_name", property = "itemClassName"),
    })
    List<ItemClass> getAllClasses();

    @Select("select * from dewu.item_subclass where item_class_id = #{itemClassId}")
    @Results({
            @Result(column = "item_class_id", property = "itemClassId"),
            @Result(column = "item_subclass_id", property = "itemSubclassId"),
            @Result(column = "item_subclass_name", property = "itemSubclassName"),
            @Result(column = "item_subclass_img", property = "itemSubclassImg"),
    })
    List<ItemSubclass> getSubclassesByItemClassId(Integer itemClassId);

    @Select("select * from dewu.item_subclass")
    @Results({
            @Result(column = "item_class_id", property = "itemClassId"),
            @Result(column = "item_subclass_id", property = "itemSubclassId"),
            @Result(column = "item_subclass_name", property = "itemSubclassName"),
            @Result(column = "item_subclass_img", property = "itemSubclassImg"),
    })
    List<ItemSubclass> getAllSubclasses();

    @Select("select * from dewu.item where item_audit_state = true")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "lowest_price", property = "lowestPrice"),
            @Result(column = "item_audit_state", property = "itemAuditState"),
            @Result(column = "store_item", property = "storeItem"),
            @Result(column = "apply_user_id", property = "applyUserId"),
            @Result(column = "cover_image", property = "coverImage"),
            @Result(column = "cube_url", property = "cubeUrl"),
            @Result(column = "info_class", property = "infoClass"),
    })
    List<Item> getAllItem();

    @Select("select * from dewu.item where item_audit_state = true limit #{limit} offset #{offset}")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "store_item", property = "storeItem"),
            @Result(column = "lowest_price", property = "lowestPrice"),
            @Result(column = "cover_image", property = "coverImage"),
    })
    List<Item> getPaginationItems(Integer limit, Integer offset);

    @Select("select * from dewu.item where item_audit_state = true and item_id in (select item_id from dewu.item_subclass_item where item_subclass_id = #{itemSubclassId})")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "store_item", property = "storeItem"),
            @Result(column = "lowest_price", property = "lowestPrice"),
            @Result(column = "cover_image", property = "coverImage"),
    })
    List<Item> getItemsBySubclass(Integer itemSubclassId);

    @Select("select * from dewu.item where item_audit_state = true and item_id in (select item_id from dewu.item_subclass_item where item_subclass_id in (select item_subclass_id from dewu.item_class where item_class_id = #{itemClassId}))")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "store_item", property = "storeItem"),
            @Result(column = "lowest_price", property = "lowestPrice"),
            @Result(column = "cover_image", property = "coverImage"),
    })
    List<Item> getItemsByClass(Integer itemClassId);

    @Select("select * from dewu.item_image where item_id = #{item_id}")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_image_id", property = "itemImageId"),
    })
    List<ItemImage> getImagesByItemId(Integer itemId);


    @Select("select * from dewu.item where item_id = #{item_id}")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "lowest_price", property = "lowestPrice"),
            @Result(column = "item_audit_state", property = "itemAuditState"),
            @Result(column = "brand", property = "brand"),
            @Result(column = "store_item", property = "storeItem"),
            @Result(column = "apply_user_id", property = "applyUserId"),
            @Result(column = "cover_image", property = "coverImage"),
            @Result(column = "cube_url", property = "cubeUrl"),
            @Result(column = "info_class", property = "infoClass"),
            @Result(column = "item_id", property = "images", many = @Many(
                    select = "org.example.dao.ItemDao.getImagesByItemId"
            ))
    })
    Item getItemByItemId(Integer itemId);

    @Select("select * from dewu.shoes_info where item_id = #{itemId}")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "master_item_number", property = "masterItemNumber"),
            @Result(column = "release_price", property = "releasePrice"),
            @Result(column = "release_date", property = "releaseDate"),
            @Result(column = "upper_material", property = "upperMaterial"),
            @Result(column = "sole_material", property = "soleMaterial"),
            @Result(column = "toe_style", property = "toeStyle"),
            @Result(column = "heel_type", property = "heelType"),
            @Result(column = "upper_height", property = "upperHeight"),
            @Result(column = "main_color", property = "mainColor"),
            @Result(column = "secondary_color", property = "secondaryColor"),
            @Result(column = "packing_list", property = "packingList"),
    })
    ShoeInfo findShoeInfoByItemId(Integer itemId);

    @Select("select * from dewu.garment_info where item_id = #{itemId}")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "master_item_number", property = "masterItemNumber"),
            @Result(column = "release_price", property = "releasePrice"),
            @Result(column = "release_date", property = "releaseDate"),
            @Result(column = "clothing_length", property = "clothingLength"),
            @Result(column = "sleeve_length", property = "sleeveLength"),
    })
    GarmentInfo findGarmentInfoByItemId(Integer itemId);

    @Select("select * from dewu.item_sale where item_sale_id = #{itemSaleId};")
    @Results({
            @Result(column = "item_sale_state", property = "itemSaleState"),
            @Result(column = "item_sale_id", property = "itemSaleId"),
            @Result(column = "item_size_id", property = "itemSizeId"),
    })
    ItemSale getItemSaleById(Integer itemSaleId);
}
