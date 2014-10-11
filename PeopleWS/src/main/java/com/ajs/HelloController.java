package com.ajs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class HelloController {

    class SiteDetails {

        private String id;
        private String name;
        private String lat;
        private String lng;
        private String address;
        private String address2;
        private String city;
        private String state;
        private String postal;
        private String phone;
        private String web;
        private String hours1;
        private String hours2;
        private String hours3;
        private String distanceMsg = "";
        private String joinTrial = "http://www.google.com";

        /**
         * Gets the id for the site
         *
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the id for the site
         *
         * @param id
         */
        public void setId(final String id) {
            this.id = id;
        }

        /**
         * Gets the name for the site
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name for the site
         *
         * @param name
         */
        public void setName(final String name) {
            this.name = name;
        }

        /**
         * Gets the latitude for the site
         *
         * @return latitude
         */
        public String getLat() {
            return lat;
        }

        /**
         * Sets the latidude for the site
         *
         * @param lat
         */
        public void setLat(final String lat) {
            this.lat = lat;
        }

        /**
         * Gets the longitude for the site
         *
         * @return lng
         */
        public String getLng() {
            return lng;
        }

        /**
         * Sets the longitude for the site
         *
         * @param lng
         */
        public void setLng(final String lng) {
            this.lng = lng;
        }

        /**
         * Gets the address for the site
         *
         * @return address
         */
        public String getAddress() {
            return address;
        }

        /**
         * Sets the address for the site
         *
         * @param address
         */
        public void setAddress(final String address) {
            this.address = address;
        }

        /**
         * Gets the 2nd address for the site
         *
         * @return address
         */
        public String getAddress2() {
            return address2;
        }

        /**
         * Sets the address for the site
         *
         * @param address2
         */
        public void setAddress2(final String address2) {
            this.address2 = address2;
        }

        /**
         * Gets the city for the site
         *
         * @return city
         */
        public String getCity() {
            return city;
        }

        /**
         * Sets the city for the site
         *
         * @param city
         */
        public void setCity(final String city) {
            this.city = city;
        }

        /**
         * Gets the state for the site
         *
         * @return state
         */
        public String getState() {
            return state;
        }

        /**
         * Sets the state for the site
         *
         * @param state
         */
        public void setState(final String state) {
            this.state = state;
        }

        /**
         * Gets the postal code for the site
         *
         * @return postal
         */
        public String getPostal() {
            return postal;
        }

        /**
         * Sets the post code for the site
         *
         * @param postal
         */
        public void setPostal(final String postal) {
            this.postal = postal;
        }


        /**
         * Gets the phone number for the site
         *
         * @return phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * Sets the phone number for the site
         *
         * @param phone
         */
        public void setPhone(final String phone) {
            this.phone = phone;
        }


        /**
         * Gets the web address for the site
         *
         * @return web
         */
        public String getWeb() {
            return web;
        }

        /**
         * Sets the web address for the site
         *
         * @param web
         */
        public void setWeb(final String web) {
            this.web = web;
        }

        /**
         * Gets the working hours for the site
         *
         * @return hours1
         */
        public String getHours1() {
            return hours1;
        }

        /**
         * Sets the working hours for the site
         *
         * @param hours1
         */
        public void setHours1(final String hours1) {
            this.hours1 = hours1;
        }

        /**
         * Gets the working hours for the site
         *
         * @return hours2
         */
        public String getHours2() {
            return hours2;
        }

        /**
         * Sets the working hours for the site
         *
         * @param hours2
         */
        public void setHours2(final String hours2) {
            this.hours2 = hours2;
        }

        /**
         * Gets the working hours for the site
         *
         * @return hours3
         */
        public String getHours3() {
            return hours3;
        }

        /**
         * Sets the working hours for the site
         *
         * @param hours3
         */
        public void setHours3(final String hours3) {
            this.hours3 = hours3;
        }

        public String getDistanceMsg() {
            return distanceMsg;
        }

        public void setDistanceMsg(final String distanceMsg) {
            this.distanceMsg = distanceMsg;
        }

        public String getJoinTrial() {
            return joinTrial;
        }

        public void setJoinTrial(final String joinTrial) {
            this.joinTrial = joinTrial;
        }
    }

    @Autowired
    InvoiceDao invoiceDao;

    @RequestMapping(value = "/welcome.app", method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("rows", invoiceDao.findAll());

        return new ModelAndView("hello", modelMap);

    }

    @RequestMapping(value = "/boobiwaa.app", method = RequestMethod.GET)
    public ModelAndView getBoobiWaa(ModelMap model) {

        model.put("boobi", "I am a boobi waa");

        return new ModelAndView("boobi", model);

    }

    @RequestMapping(value = "/sitesForTrial.app", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody List<SiteDetails> getSites() {

        List<SiteDetails> list = new ArrayList<SiteDetails>();


        SiteDetails siteDetails = new SiteDetails();
        siteDetails.setId("1");
        siteDetails.setName("Guy's Hospital");
        siteDetails.setLat("51.50346");
        siteDetails.setLng("-0.08687");
        siteDetails.setAddress2("Westminster");
        siteDetails.setCity("London");
        siteDetails.setPostal("SE1 9RT");
        list.add(siteDetails);

        SiteDetails siteDetails2 = new SiteDetails();
        siteDetails2.setId("2");
        siteDetails2.setName("Royal Free Hospital");
        siteDetails2.setLat("51.560131");
        siteDetails2.setLng("-0.186467");
        siteDetails2.setAddress2("Pond St");
        siteDetails2.setCity("London");
        siteDetails2.setPostal("NW3 2QG");
        list.add(siteDetails2);

        SiteDetails siteDetails3 = new SiteDetails();
        siteDetails3.setId("3");
        siteDetails3.setName("Royal Berkshire Hospital");
        siteDetails3.setLat("51.450833");
        siteDetails3.setLng("-0.959444");
        siteDetails3.setAddress2("London Rd");
        siteDetails3.setCity("Reading");
        siteDetails3.setPostal("RG1 5AN");
        list.add(siteDetails3);

        SiteDetails siteDetails4 = new SiteDetails();
        siteDetails4.setId("4");
        siteDetails4.setName("Heatherwood Hospital");
        siteDetails4.setLat("51.4108");
        siteDetails4.setLng("-0.686782");
        siteDetails4.setAddress2("London Rd");
        siteDetails4.setCity("Ascot");
        siteDetails4.setPostal("SL5 8AA");
        list.add(siteDetails4);

        return list;


    }

    @RequestMapping(value = "/map.app", method = RequestMethod.GET)
    public ModelAndView getMapView(ModelMap model) {
        return new ModelAndView("map", model);

    }

    @RequestMapping(value = "/invoicePost.app", method = RequestMethod.POST, headers = "Accept=application/json")
    public
    @ResponseBody
    List<Invoice> getEnquiriesBySearchAjax(@RequestBody Person person) {
//        return invoiceDao.findAll();

//        List<Shop> shops = new ArrayList<Shop>();
//        Shop shop = new Shop();
//        shop.setName("test");
//        shop.setId(1000);
//        shop.setStaffName(new String[]{"mkyong1", "mkyong2"});
//
//        Shop shop2 = new Shop();
//        shop2.setName("test2");
//        shop2.setId(1001);
//        shop2.setStaffName(new String[]{"mkyong1", "mkyong2"});
//
//        shops.add(shop);
//        shops.add(shop2);
//        return shops;

        List<Invoice> invoices = invoiceDao.findAll();
//        List<Invoice> invoices = new ArrayList<Invoice>();

//        Invoice invoice = (Invoice) invoiceDao.findById(5034L);
//        Invoice invoice2 = (Invoice) invoiceDao.findById(5033L);
//        Invoice invoice3 = (Invoice) invoiceDao.findById(5035L);
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceNumber("12312");
//        Invoice invoice2 = new Invoice();
//        invoice2.setInvoiceNumber("12312");
//        invoices.add(invoice);
//        invoices.add(invoice2);

//        invoices.add(invoice);
//        invoices.add(invoice2);
//        invoices.add(invoice3);

//        invoice3.setPaymentInvoiceAllocations(null);

//        invoice3.setCustomer(null);

        return invoices;

    }


    @RequestMapping(value = "/invoice.app", method = RequestMethod.GET, headers = "Accept=application/json")
    public
    @ResponseBody
    List<Invoice> getEnquiriesBySearchAjax() {
//        return invoiceDao.findAll();

//        List<Shop> shops = new ArrayList<Shop>();
//        Shop shop = new Shop();
//        shop.setName("test");
//        shop.setId(1000);
//        shop.setStaffName(new String[]{"mkyong1", "mkyong2"});
//
//        Shop shop2 = new Shop();
//        shop2.setName("test2");
//        shop2.setId(1001);
//        shop2.setStaffName(new String[]{"mkyong1", "mkyong2"});
//
//        shops.add(shop);
//        shops.add(shop2);
//        return shops;

        List<Invoice> invoices = invoiceDao.findAll();
//        List<Invoice> invoices = new ArrayList<Invoice>();

//        Invoice invoice = (Invoice) invoiceDao.findById(5034L);
//        Invoice invoice2 = (Invoice) invoiceDao.findById(5033L);
//        Invoice invoice3 = (Invoice) invoiceDao.findById(5035L);
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceNumber("12312");
//        Invoice invoice2 = new Invoice();
//        invoice2.setInvoiceNumber("12312");
//        invoices.add(invoice);
//        invoices.add(invoice2);

//        invoices.add(invoice);
//        invoices.add(invoice2);
//        invoices.add(invoice3);

//        invoice3.setPaymentInvoiceAllocations(null);

//        invoice3.setCustomer(null);

        return invoices;

    }

}

class Shop {

    String name;
    String staffName[];

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStaffName() {
        return staffName;
    }

    public void setStaffName(String[] staffName) {
        this.staffName = staffName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //getter and setter methods

}


class Person {
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}