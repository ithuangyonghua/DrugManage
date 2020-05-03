package com.drugmanager.service;

import java.util.List;

import com.drugmanager.bean.Permission;
import com.drugmanager.bean.User;

public interface PermissionService {

	Permission queryRoot();

	List<Permission> queryChildren(Integer id);

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryPermissionById(Integer id);

	void updatePermission(Permission permission);

	void deletePermission(Permission permission);

	List<Integer> queryPermissionidByRid(Integer rid);

	List<Permission> queryPermissionByUser(User dbuser);

	

}
