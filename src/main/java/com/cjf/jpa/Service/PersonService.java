package com.cjf.jpa.Service;

import com.cjf.jpa.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author:chenjinfeng
 * @date: 2018/7/21
 * @time: 14:14
 * @desc
 */
public interface PersonService {

    /**
     * 原生sql
     */
    void updateEmailById(Integer id, String email);

    /**
     * crudrepositroy中的方法
     */
    void saveAll(List<Person> list);

    /**
     * PagingAndSortingRepository中的分页排序方法
     */
    Page<Person> findAll(PageRequest pageRequest);

    /**
     * JpaRepository中的saveAndFlush方法
     */
    Person saveAndFlush(Person person);

    /**
     * JpaSpecificationExecutor中的findOne方法
     */
    Person findByEmail(String email);

    /**
     * 自定义的repository中的方法
     */
    void test();
}
