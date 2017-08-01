package net.zndo.training.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.zndo.training.data.entity.User2;

public interface User2Repository extends JpaRepository<User2, Long> {

	User2 findUserByUsername(String name);
	
	User2 findUserByUsernameAndEmail(String name, String email);
	
	@Query("select u from User2 u where u.username=:username")
	User2 findUser(@Param("username") String username);
	
}
