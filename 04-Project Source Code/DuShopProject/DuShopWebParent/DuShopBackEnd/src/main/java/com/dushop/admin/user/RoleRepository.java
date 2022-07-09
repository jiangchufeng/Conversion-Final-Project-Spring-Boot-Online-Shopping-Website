package com.dushop.admin.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.dushop.common.entity.Role;

/*
 * @BelongsProject: DuShopProject
 * @BelongsPackage: com.dushop.common.entity
 * @Author: Jiang Chufeng
 * @CreateTime: 2022-07-9  19:30
 * @Description: TODO
 * @Version: 1.0
 */
@Repository
public interface RoleRepository extends  CrudRepository<Role, Integer>{
}
