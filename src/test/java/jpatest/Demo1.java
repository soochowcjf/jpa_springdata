package jpatest;

import com.cjf.jpa.Service.PersonService;
import com.cjf.jpa.entity.Person;
import com.cjf.jpa.repository.PersonRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * @author:chenjinfeng
 * @date: 2018/7/21
 * @time: 0:45
 * @desc
 */
public class Demo1 {

    private ApplicationContext ac;
    private PersonRepository personRepository;
    private PersonService personService;

    {
        ac =new ClassPathXmlApplicationContext("applicationContext.xml");
        personRepository = ac.getBean(PersonRepository.class);
        personService = ac.getBean(PersonService.class);
    }

    @Test
    public void getPersionById() {
        System.out.println(personRepository.getClass().getName());
        Person person = personRepository.getPersonById(1);
        System.out.println(person);
    }

    @Test
    public void testKeyAndWord() {
        List<Person> list = personRepository.getByLastNameStartingWithAndBirthLessThanEqual("chen", new Date());
        System.out.println(list);

        List<Person> list1 = personRepository.getByEmailInOrBirthBefore(Arrays.asList("123@qq.com", "234@qq.com"), new Date());
        System.out.println(list1);
    }

    /**
     * 级联查询
     */
    @Test
    public void testKeyAndWord2() {
        List<Person> list = personRepository.getByAddressIdIsLessThanEqual(1);
        System.out.println(list);

        List<Person> list1 = personRepository.getByAddress_IdIsLessThanEqual(1);
        System.out.println(list1);
    }

    /**
     * 自定义query查询
     */
    @Test
    public void testQuery() {
        Person person = personRepository.getMaxId();
        System.out.println(person);
    }

    @Test
    public void testQueryParam1() {
        Person person = personRepository.getPersonByLastAndEmail("chenjinfeng", "123@qq.com");
        System.out.println(person);
    }

    @Test
    public void testQueryParam2() {
        List<Person> list = personRepository.getListByLastNameAndEmail("123@qq.com", "chenjinfeng");
        System.out.println(list.size());
    }

    @Test
    public void testQueryLike1() {
        List<Person> list = personRepository.testQueryAnnotationLikeParam("chen", "12");
        System.out.println(list.size());
    }

    @Test
    public void testQueryLike2() {
        List<Person> list = personRepository.testQueryAnnotationLikeParam2("12", "chenjinfeng");
        for (Person person : list) {
            System.out.println(person);
        }
    }

    @Test
    public void testNativeQuery() {
        Integer totalCount = personRepository.getTotalCount();
        System.out.println(totalCount);
    }

    @Test
    public void testUpdateById() {
        personService.updateEmailById(1,"666@qq.com");
    }

    /**
     * 测试CrudRepository中的方法
     */
    @Test
    public void testSaveAll() {
        List<Person> persons = new ArrayList<>();
        for(int i = 'a'; i <= 'z'; i++){
            Person person = new Person();
            person.setAddressId(i + 1);
            person.setBirth(new Date());
            person.setEmail((char)i + "" + (char)i + "@qq.com");
            person.setLastName((char)i + "" + (char)i);

            persons.add(person);
        }
        personService.saveAll(persons);
    }

    /**
     * 测试分页和排序方法
     */
    @Test
    public void testPagingAndSorting() {
        //pageNo 从 0 开始.
        int pageNo = 5 - 1;
        int pageSize = 5;
        //Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
        //排序相关的. Sort 封装了排序的信息
        //Order 是具体针对于某一个属性进行升序还是降序.
        Sort.Order order1 = Sort.Order.desc("id");
        Sort.Order order2 = Sort.Order.asc("email");
        Sort sort = Sort.by(order1, order2);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = personService.findAll(pageRequest);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testSaveAndFlush() {
        Person person = new Person();
        person.setLastName("yuwenmiao");
        person.setBirth(new Date());
        person.setEmail("444@qq.com");
        person.setId(27);

        Person person1 = personService.saveAndFlush(person);
        System.out.println(person);
        System.out.println(person == person1);
        System.out.println(person1);
    }

    /**
     * 测试 JpaSpecificationExecutor 中方法的使用
     */
    @Test
    public void testFindBySpecification() {
        Person person = personService.findByEmail("444@qq.com");
        System.out.println(person);
    }

    /**
     * 测试自定义的repository接口中的方法
     */
    @Test
    public void testCustomRepository() {
        personService.test();
    }
}
