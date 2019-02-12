package com.ymjtt.manager.user.mapper;

import com.ymjtt.manager.user.xdo.UserDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/9 9:51
 */
@Repository
public interface UserMapper {

    /* Base */
    List<UserDo> listDO(UserDo userDo);

    UserDo getDO(@Param("id") Long id);

    @Insert("insert into user values(null, #{userName}, #{pwd}, #{image}, #{email}, #{phone}, #{status}, #{userType}, timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    boolean saveDO(UserDo userDo);

    @Delete("delete from user where user_id = #{id}")
    boolean removeDO(@Param("id") Long id);

    boolean updateDO(UserDo userDo);


    /* Others */

}
