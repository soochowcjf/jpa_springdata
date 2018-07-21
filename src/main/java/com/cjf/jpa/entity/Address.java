package com.cjf.jpa.entity;

import javax.persistence.*;

/**
 * @author:chenjinfeng
 * @date: 2018/7/21
 * @time: 11:53
 * @desc
 */
@Table(name = "jpa_address")
@Entity
public class Address {

    private Integer id;

    private String province;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
