package com.dushop.admin.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import javax.annotation.Resource;

import com.dushop.common.entity.Role;
import com.dushop.common.entity.User;



/*
 *@BelongsProject: DuShopProject
 *@BelongsPackage: com.dushop.admin.user
 *@Author: Jiang Chufeng
 *@CreateTime: 2022-07-10  13:43
 *@Description: Test users with 1,2,3 or 4 different roles.
 *@Version: 1.0
 */

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Resource  // @Aotowired is not working
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userNamJCF = new User("jiangchufengjcf@gmail.com", "123456", "Jiang", "Chufeng");
        userNamJCF.addRole(roleAdmin);

        User savedUser = repo.save(userNamJCF);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User userLH = new User("liuhai@gmail.com", "123456", "Liu", "Hai");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userLH.addRole(roleEditor);
        userLH.addRole(roleAssistant);

        User savedUser = repo.save(userLH);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User userJCF = repo.findById(1).get();
        System.out.println(userJCF);
        assertThat(userJCF).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userJCF = repo.findById(1).get();
        userJCF.setEnabled(true);
        userJCF.setEmail("cxj164@student.bham.ac.uk");
        System.out.println(userJCF);
        repo.save(userJCF);
    }

    @Test
    public void testUpdateUserRoles() {
        User userLH = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        userLH.getRoles().remove(roleEditor);
        userLH.addRole(roleSalesperson);
        System.out.println(userLH);
        repo.save(userLH);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 2;
        repo.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "kkk@163.com";
        User user = repo.getUserByEmail(email);
        System.out.println(user);

        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById() {
        Integer id = 3;
        Long countById = repo.countById(id);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

}
