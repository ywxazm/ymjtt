package com.ymjtt.sso.dao;

import com.ymjtt.sso.domain.UserDo;
import com.ymjtt.sso.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther ywx
 * @date 2019/4/30 17:40
 **/
@Mapper
public interface UserMapper {

    UserDo getDO(UserVO userVO);

    @Update("UPDATE user u SET u.loginCounts = u.loginCounts + 1 WHERE u.user_id = #{userId}")
    void loginIncr(@Param("userId") String userId);
}
