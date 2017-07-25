package net.zndo.training.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.zndo.training.data.entity.User2;

@Mapper
public interface User2Mapper {

	@Select("select * from user2 where username = #{username}")
	User2 findByName(@Param("username") String username);

	@Results({ @Result(property = "username", column = "username"),
			@Result(property = "password", column = "password") })
	@Select("select username, password from user2")
	List<User2> findAll();

	@Insert("insert into user2(username,password) values(#{username}, #{age})")
	int insert(@Param("username") String username, @Param("password") String password);

	@Update("update user2 set password=#{password} where username=#{username}")
	void update(User2 user2);
	
	@Delete("delete from user2 where id = #{id}")
	void delete(Long id);
	
	@Insert("insert into user2 (username, password) values(#{uesrname}, #{password})")
	int insertByUser(User2 user);
	
	@Insert("insert into user2(username, password) values(#{username, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR})")
	int insertByMap(Map<String, Object> map);
	
}
