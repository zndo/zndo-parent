package net.zndo.training.data.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import net.zndo.training.data.entity.User;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(username, password, nickname, roles) values(#{username}, #{password}, #{nickname}, #{roles})")
    int insert(User userEntity);

    @Select("select * from user where username = #{username}")
    User selectByUsername(@Param("username") String username);

}
