package net.zndo.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.zndo.training.data.entity.User;
import net.zndo.training.data.util.DataConverter;
import net.zndo.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User user(Integer id) {

		Map<String, Object> map = jdbcTemplate.queryForMap("select * from test.`user` where id = ?",
				new Object[] { id });
		return DataConverter.map2User(map);
	}

	@Override
	public List<User> userList() {
		List<Map<String, Object>> lstMap = jdbcTemplate.queryForList("select * from test.`user`");
		return DataConverter.mapList2UserList(lstMap);
	}

	@Override
	public int create(User user) {
		return jdbcTemplate.update("insert into test.`user`(username, password, email) values(?, ?, ?)",
				user.getUsername(), user.getPassword(), user.getEmail());
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update("update test.`user` set username=?, password=?, email=? where id=?",
				user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
	}

	@Override
	public int remove(Integer id) {
		return jdbcTemplate.update("delete from test.`user` where id = ?", id);
	}

}
