package com.ajs;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 19/02/2014
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api")
public class InvoiceController {

//    InvoiceDao invoiceDao;
//
//    @Autowired
//    public InvoiceController(InvoiceDao invoiceDao) {
//        this.invoiceDao = invoiceDao;
//    }
//
//
//
//    @RequestMapping("invoice/{id}")
//    @ResponseBody
//    public Invoice getById(@PathVariable Long id, @ModelAttribute Invoice invoice) {
//        return (Invoice)invoiceDao.findById(id);
//    }
//
//    // same as above method, just showing different URL mapping
//    @RequestMapping(value="invoice", params="id")
//    @ResponseBody
//    public Invoice getByIdFromParam(@RequestParam Long id) {
//        return (Invoice)invoiceDao.findById(id);
//    }
//
//    // handles invoice form submit
//    @RequestMapping(value="invoice", method=RequestMethod.POST)
//    @ResponseBody
//    public String saveInvoice(Invoice invoice) {
//        invoiceDao.save(invoice);
//        return "Saved invoice: " + invoice.toString();
//    }
}