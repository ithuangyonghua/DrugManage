package com.drugmanager.service;

import java.util.List;
import java.util.Map;

import com.drugmanager.bean.Role;

public interface RoleService {

	List<Role> pageQuery(Map<String, Object> map);

	int pageQueryCount(Map<String,Object> map );

	Role queryRoleById(String id);

	void updateRole(Role role);

	void insertRole(Role role);

	void deleteRoleById(String id);

	void deleteRoleBouth(Map<String, Object> map);

	List<Role> queryAll();

	void doAssign(Map<String, Object> map);

	

}
