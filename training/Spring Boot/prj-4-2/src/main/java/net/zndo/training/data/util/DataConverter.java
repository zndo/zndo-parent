package net.zndo.training.data.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.zndo.training.data.entity.User;

public class DataConverter {

	public static User map2User(Map<String, Object> map) {
		User user = new User();
		user.setId((Integer) map.get("id"));
		user.setUsername((String) map.get("username"));
		user.setPassword((String) map.get("password"));
		user.setEmail((String) map.get("email"));
		user.setCreate_time((Date) map.get("create_time"));

		return user;
	}

	public static List<User> mapList2UserList(List<Map<String, Object>> userList) {
		List<User> lstUser = new ArrayList<User>();

		for (Map<String, Object> map : userList) {
			User user = map2User(map);

			if (user != null) {
				lstUser.add(user);
			}
		}
		return lstUser;
	}

}
