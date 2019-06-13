package com.banzhuan.accountservice;

import com.banzhuan.accountservice.entity.Resource;
import com.banzhuan.accountservice.entity.Role;
import com.banzhuan.accountservice.service.AccountService;

import com.banzhuan.accountservice.service.ResourceService;
import com.banzhuan.accountservice.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceApplicationTests {

	@Autowired
	AccountService accountService;
	@Autowired
	ResourceService resourceService;
	@Autowired
	RoleService roleService;


	@Test
	public void contextLoads() {

	}

	@Test
	public void testResourceService(){
		Resource resource = new Resource();
		resource.setUrl("/test11222");
		resource.setId(2);
		resourceService.updateResource(resource);
		List<Resource> list = resourceService.getAllResource();
		for(Resource resource1: list){
			System.out.println(resource1.getId()+resource1.getUrl()+resource1.getDescription());
		}
	}

	@Test
	public void testRoleService(){
		Role role = new Role();
		role.setName("admin111");
		roleService.saveRole(role);
		List<Role> roles = roleService.getAllRole();
		for(Role role1: roles){
			System.out.println(role1.getId()+" "+role1.getName()+role1.getResources());
		}
	}




}
