package com.ajs.repository;

import com.ajs.dao.EmployeeDao;
import com.ajs.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EmployeeRepository {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> findAll(){
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext(new String[] {"dispatcher-servlet.xml"});
//
//        EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDao");
//        System.out.println(employeeDao);
        return employeeDao.findAll();
    }

//    public EmployeeDao getEmployeeDao() {
//        return employeeDao;
//    }
//
//    public void setEmployeeDao(EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }
}
