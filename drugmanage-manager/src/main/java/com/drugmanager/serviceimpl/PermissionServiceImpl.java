package com.drugmanager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugmanager.bean.Permission;
import com.drugmanager.bean.User;
import com.drugmanager.dao.PermissionDao;
import com.drugmanager.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	PermissionDao permissionDao;

	public Permission queryRoot() {
		// TODO Auto-generated method stub
		return permissionDao.queryRoot();
	}

	public List<Permission> queryChildren(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.queryChildren(id);
	}

	public List<Permission> queryAll() {
		// TODO Auto-generated method stub
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.insertPermission(permission);
	}

	public Permission queryPermissionById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionById(id);
	}

	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.updatePermission(permission);
	}

	public void deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.deletePermission(permission);
	}

	public List<Integer> queryPermissionidByRid(Integer rid) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionidByRid(rid);
	}

	public List<Permission> queryPermissionByUser(User dbuser) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionByUser(dbuser);
	}

}
