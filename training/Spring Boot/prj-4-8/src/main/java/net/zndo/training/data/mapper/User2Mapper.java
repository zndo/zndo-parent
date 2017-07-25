package net.zndo.training.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.zndo.training.data.entity.User2;

@Mapper
public interface User2Mapper {
	
	@Select("select * from user2 where username = #{username}")
	User2 findByName(@Param("username") String username);
	
	@Insert("insert into user2(username,password) values(#{username}, #{age})")
	int insert(@Param("username") String username, @Param ("password") String password);

}
