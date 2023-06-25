package org.example.dao;

import org.apache.ibatis.annotations.*;
import org.example.entity.Trends;

import java.util.List;

@Mapper
public interface TrendsDao {
    @Select("select * from dewu.trends where user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "trends_id", property = "trendsId"),
            @Result(column = "trends_id", property = "images", many = @Many(
                    select = "org.example.dao.TrendsDao.getTrendsImages"
            )),
    })
    List<Trends> getTrendsByUserId(Integer userId);

    @Select("select * from dewu.trends where trends_id = #{trendsId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "trends_id", property = "trendsId"),
            @Result(column = "trends_id", property = "images", many = @Many(
                    select = "org.example.dao.TrendsDao.getTrendsImages"
            )),
    })
    Trends getTrendsById(Integer trendsId);

    @Select("select * from dewu.trends")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "trends_id", property = "trendsId"),
            @Result(column = "trends_id", property = "images", many = @Many(
                    select = "org.example.dao.TrendsDao.getTrendsImages"
            )),
    })
    List<Trends> getAllTrends();

    @Select("select image from dewu.trends_image where trends_id = #{trendsId}")
    List<String> getTrendsImages(Integer trendsId);

    @Select("select item_id from dewu.trends_item where trends_id = #{trendsId}")
    @Results({
            @Result(column = "item_id", property = "itemId")
    })
    List<Integer> getTrendsItemIds(Integer trendsId);

    @Insert("insert into dewu.trends(user_id, title, description) VALUES (#{userId}, #{title}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "trendsId")
    void addTrends(Trends trends);

    @Insert("insert into dewu.trends_image(image, trends_id) VALUES (#{image}, #{trendsId})")
    void addTrendsImage(String image, Integer trendsId);

    @Insert("insert into dewu.trends_item(trends_id, item_id) VALUES (#{trendsId}, #{itemId})")
    void addTrendsItem(Integer trendsId, Integer itemId);

    @Delete("delete from dewu.trends where trends_id = #{trendsId}")
    Boolean deleteTrendsByTrendsId(Integer trendsId);
}
