package com.dushop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.dushop.common.entity.Role;

/*
 * @BelongsProject: DuShopProject
 * @BelongsPackage: com.dushop.common.entity
 * @Author: Jiang Chufeng
 * @CreateTime: 2022-07-10  00:16
 * @Description: TODO
 * @Version: 1.0
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "manage everything");
		Role savedRole = repo.save(roleAdmin);
		
		assertThat(savedRole.getId()).isGreaterThan(0);
	}


	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("Salesperson", "manage product price, "
				+ "customers, shipping, orders and sales report");
		
		Role roleShipper = new Role("Shipper", "view products, view orders "
				+ "and update order status");
		
		//Role roleAssistant = new Role("Assistant", "manage questions and reviews");
		//Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
		
		//repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
		repo.saveAll(List.of(roleSalesperson, roleShipper));
	}
}
