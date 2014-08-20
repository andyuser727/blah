package com.ajs;

import com.ajs.domain.Employee;
import org.hibernate.SessionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;

@ContextConfiguration( locations = {"classpath:test-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UnitTest extends AbstractTransactionalTestNGSpringContextTests {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Resource(name = "h2DataSource")
    private DataSource dataSource;

    @Resource
    GetTestString getTestString;

    @Test
    public void testPrintHelloWorld2() {

        sessionFactory.getCurrentSession().save(new Employee());
//        sessionFactory.getCurrentSession().load();
        System.out.println("jhgvghv");
        assert(true);

    }

}