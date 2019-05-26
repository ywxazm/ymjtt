package com.ymjtt.sso.dao;

import com.ymjtt.sso.xdo.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther ywx
 * @date 2019/4/30 17:40
 **/
@Mapper
public interface UserMapper {

    UserDo getDO(@Param("userNick") String userNick);

    @Update("UPDATE user u SET u.loginCounts = u.loginCounts + 1 WHERE u.user_id = #{userId}")
    boolean loginIncr(@Param("userId") String userId);

    @Insert("INSERT INTO user values(null, #{userName}, #{pwd}, #{salt}, #{image}, #{email}, #{phone}, 1, 0, timestamp(now()), 'ywx', timestamp(now()), 1, timestamp(now()), 'ywx', 1)")
    boolean save(UserDo userDo);
}
