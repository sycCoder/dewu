package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.ItemSale;

@Mapper
public interface ItemSaleDao {
    @Select("select item_sale_id, item_size_id, price from dewu.item_sale where item_size_id = #{itemSizeId} and item_sale_state = #{itemSaleState} order by price limit 1;")
    @Results({
            @Result(column = "item_sale_id", property = "itemSaleId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_size_id", property = "itemSizeId"),
            @Result(column = "item_sale_state", property = "itemSaleState")
    })
    ItemSale getLowestItemSaleByItemSizeId(Integer itemSizeId, Integer itemSaleState);

    @Update("update dewu.item_sale set item_sale_state = #{itemSaleState} where item_sale_id = #{itemSaleId}")
    Boolean changeItemSaleState(Integer itemSaleId, Integer itemSaleState);
}
