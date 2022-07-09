package com.dushop.common.entity;import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/*
 * @BelongsProject: DuShopProject
 * @BelongsPackage: com.dushop.common.entity
 * @Author: Jiang Chufeng
 * @CreateTime: 2022-07-9  19:23
 * @Description: TODO
 * @Version: 1.0
 */

@MappedSuperclass
public abstract class IdBasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id; // The table role - 1st Attribute: id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
