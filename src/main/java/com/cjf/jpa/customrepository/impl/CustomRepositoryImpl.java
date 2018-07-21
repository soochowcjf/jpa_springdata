package com.cjf.jpa.customrepository.impl;

import com.cjf.jpa.customrepository.CustomRepository;
import com.cjf.jpa.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author:chenjinfeng
 * @date: 2018/7/21
 * @time: 22:00
 * @desc
 */
public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 1);
        System.out.println("自定义的repository --> " + person);
    }
}
