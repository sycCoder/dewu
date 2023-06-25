package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.ItemSize;

import java.util.List;

@Mapper
public interface ItemSizeDao {
    @Select("select * from dewu.item_size where item_id = #{itemId}")
    @Results({
            @Result(column = "item_size_id", property = "itemSizeId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "size_id", property = "size", one = @One(
                    select = "org.example.dao.ItemSizeDao.getSizeBySizeId"
            ))
    })
    List<ItemSize> getAllItemSizeByItemId(Integer itemId);


    @Select("select size from dewu.size where size_id = #{sizeId}")
    String getSizeBySizeId(Integer sizeId);

}
