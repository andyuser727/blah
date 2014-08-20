package com.ajs;

import com.ajs.dao.*;
import com.ajs.domain.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration( locations = {"classpath:test-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UnitTest extends AbstractTransactionalTestNGSpringContextTests {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    @Resource(name = "invoiceDao")
    private InvoiceDao invoiceDao;

    @Resource(name = "itemDao")
    private ItemDao itemDao;

    @Resource(name = "invoiceCacheTestDao")
    private InvoiceCacheTestDao invoiceCacheTestDao;

    @Resource(name = "invoiceItemRlshipDao")
    private InvoiceItemRlshipDao invoiceItemRlshipDao;


    @BeforeMethod
    @Transactional
    public void initailLoad(){
        Invoice invoiceOne = new Invoice();
        invoiceOne.setAmount(new BigDecimal("100"));
        invoiceOne.setCustomer(new Customer("Andy", "Smith"));
        invoiceOne.setInvoiceNumber("1");

        invoiceDao.save(invoiceOne);

        Quote quoteOne = new Quote();
        quoteOne.setCustomer(invoiceOne.getCustomer());

        Item itemOne = new Item();
        itemOne.setAmount(new BigDecimal("100"));
        itemOne.setName("itemOne");

        itemDao.save(itemOne);

        InvoiceItemRlship invoiceItemRlshipOne = new InvoiceItemRlship();
        invoiceItemRlshipDao.save(invoiceItemRlshipOne);
        invoiceItemRlshipOne.setInvoice(invoiceOne);
        invoiceItemRlshipOne.setItem(itemOne);




//        Set<InvoiceItemRlship> invoiceItemRlships = new HashSet<InvoiceItemRlship>();
//        invoiceItemRlships.add(invoiceItemRlshipOne);
//
//        invoiceOne.setInvoiceItemRlships(invoiceItemRlships);
//
//        itemOne.setInvoiceItemRlships(invoiceItemRlships);
//
//        sessionFactory.getCurrentSession().flush();

        List<Invoice> invoices = invoiceDao.findAll();

        List<InvoiceItemRlship> invoiceItemRlships2 = invoiceItemRlshipDao.findAll();
        int a = 1;

    }

    @Test
    @Transactional
//    @Rollback(false)
    public void test1() {

        Employee employeeToSave = new Employee();
        employeeToSave.setFirstName("BLAH");
        employeeDao.save(employeeToSave);
        List<Employee> employeeList = employeeDao.findAll();
        for (Employee employee : employeeList){
            System.out.println(employee.getFirstName());
        }
        assert(employeeList.size() == 1);

    }

    @Test
    @Transactional
    public void findInvoiceById(){

        List<Invoice> invoices = invoiceDao.findAll();
        int a = 1;
    }

    @Test
    @Transactional
    public void findInvoicesForCustomer() {

        List<InvoiceItemRlship> invoiceItemRlships1 = invoiceItemRlshipDao.findAll();

        Query query = sessionFactory.getCurrentSession().getNamedQuery("findInvoicesForCustomer");
        query.setString("surname", "Smith");
        List<Invoice> invoices = query.list();
        for (Invoice invoice : invoices){
            Set<InvoiceItemRlship> invoiceItemRlships = invoice.getInvoiceItemRlships();
            System.out.println("Found Invoice for Smith " + invoice.getInvoiceNumber());
        }
    }

    @Test
    @Transactional
    public void findInvoicesForItemName() {

        Query query = sessionFactory.getCurrentSession().getNamedQuery("findInvoicesForItemName");
        query.setString("itemName", "itemOne");
        List<Invoice> invoices = query.list();
        for (Invoice invoice : invoices){
            Set<InvoiceItemRlship> invoiceItemRlships = invoice.getInvoiceItemRlships();
            System.out.println("Found Invoice for Item Name " + invoice.getInvoiceNumber());
        }

    }


    @Test
    public void testCache() {
        tx1();
        tx2();
        // Watch the output with show sql true

    }

    @Test
    @Transactional
    public void testHQL(){
        List<Invoice> invoices = invoiceDao.loadOutstandingInvoices();
        int a = 1;

    }

    @Transactional
    public void txTestInsert() {
        invoiceCacheTestDao.save(new InvoiceCacheTest());
    }

    @Transactional
    public void tx1() {
        List<InvoiceCacheTest> invoices = invoiceCacheTestDao.findAll();
    }

    @Transactional
    public void tx2() {
        List<InvoiceCacheTest> invoices = invoiceCacheTestDao.findAll();
    }

    @Test
    @Transactional
    public void test2() {

        Employee employeeToSave = new Employee();
        employeeToSave.setFirstName("BLAH");
        employeeDao.save(employeeToSave);
        List<Employee> employeeList = employeeDao.findAll();
        for (Employee employee : employeeList){
            System.out.println(employee.getFirstName());
        }
        assert(employeeList.size() == 1);

    }

    @Test
    @Transactional
    public void test3() {

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("BLAH");
        Item item = new Item();

        InvoiceItemRlship invoiceItemRlship = new InvoiceItemRlship();
        invoiceItemRlship.setInvoice(invoice);
        invoiceItemRlship.setItem(item);

        InvoiceItemRlship invoiceItemRlship2 = new InvoiceItemRlship();
        invoiceItemRlship2.setInvoice(invoice);
        invoiceItemRlship2.setItem(item);

        invoiceItemRlshipDao.save(invoiceItemRlship2);
        invoiceItemRlshipDao.save(invoiceItemRlship);

        invoiceItemRlshipDao.delete(invoiceItemRlship);

        List<InvoiceItemRlship> invoiceItemRlships = invoiceItemRlshipDao.findAll();

        List<Invoice> invoices = invoiceDao.findAll();
    }

}