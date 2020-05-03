package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import com.drugmanager.bean.User;

public interface UserService {

	List<User> queryAll();

	User queryForLogin(User user);

	List<User> pageQueryData(Map<String,Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertUser(User user);

	User queryUserById(String id);

	void updateUser(User user);

	void deleteUser(String id);

	void deleteBoathUser(Map<String,Object> map);

	void insertAssign(Map<String, Object> map);

	void deleteAssign(Map<String, Object> map);

	List<Integer> assignRoleById(String id);

}
