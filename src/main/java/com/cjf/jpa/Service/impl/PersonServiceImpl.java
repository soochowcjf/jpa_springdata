package com.cjf.jpa.Service.impl;

import com.cjf.jpa.Service.PersonService;
import com.cjf.jpa.entity.Person;
import com.cjf.jpa.repository.PersonRepository;
import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author:chenjinfeng
 * @date: 2018/7/21
 * @time: 14:15
 * @desc
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public void updateEmailById(Integer id, String email) {
        personRepository.updateEmailById(id, email);
    }

    @Transactional
    @Override
    public void saveAll(List<Person> list) {
        personRepository.saveAll(list);
    }

    @Override
    public Page<Person> findAll(PageRequest pageRequest) {
        return personRepository.findAll(pageRequest);
    }

    @Override
    public Person saveAndFlush(Person person) {
        return personRepository.saveAndFlush(person);
    }

    @Override
    public Person findByEmail(String email) {

//        Specification<Person> specification = new Specification() {
//            @Override
//            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
//                Predicate predicate = cb.conjunction();
//                if(email != null && !email.isEmpty()){
//                    predicate.getExpressions().add(cb.equal(root.get("email"),email));
//                }
//                return predicate;
//            }
//        };
        Specification<Person> specification = (root, criteriaQuery, cb) -> {
            Predicate predicate = cb.conjunction();
            if (email != null && !email.isEmpty()) {
                predicate.getExpressions().add(cb.equal(root.get("email"), email));
            }
            return predicate;
        };
        Optional<Person> one = personRepository.findOne(specification);
        //判断是否为空
        if (one.isPresent()) {
            return one.get();
        }
        return null;
    }

    @Override
    public void test() {
        personRepository.test();
    }
}
