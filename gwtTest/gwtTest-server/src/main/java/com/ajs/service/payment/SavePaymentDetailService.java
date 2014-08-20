package com.ajs.service.payment;

import com.ajs.dao.*;
import com.ajs.domain.Customer;
import com.ajs.domain.Invoice;
import com.ajs.domain.Payment;
import com.ajs.domain.PaymentInvoiceAllocation;
import com.ajs.shared.dto.payment.PaymentDetailDto;
import com.ajs.shared.commands.payment.SavePaymentDetail;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class SavePaymentDetailService implements Handler<SavePaymentDetail> {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    PaymentInvoiceAllocationDao paymentInvoiceAllocationDao;

    public AppResponse execute(SavePaymentDetail dto) {

        AppResponse response = new AppResponse();

        PaymentDetailDto paymentDetailDto = dto.getPaymentDetailDto();

        Payment payment = null;
        if (dto.getPaymentDetailDto().getId() != null) {
            payment = paymentDao.find(dto.getPaymentDetailDto().getId());
        } else {
            payment = new Payment();
            paymentDao.save(payment);
        }

        payment.setCustomerReference(paymentDetailDto.getCustomerReference());
        payment.setDescription(paymentDetailDto.getDescription());
        payment.setPaymentDate(paymentDetailDto.getPaymentDate());
        payment.setPaymentNumber(paymentDetailDto.getPaymentNumber());
        payment.setTotalAmount(paymentDetailDto.getTotalAmount());

        if (paymentDetailDto.getCustomerId() != null) {
            Customer customer = customerDao.find(paymentDetailDto.getCustomerId());
            payment.setCustomer(customer);
        }

        List<PaymentInvoiceAllocation> paymentInvoiceAllocations = paymentInvoiceAllocationDao.findByPaymentId(payment.getId());
        BigDecimal amountAllocated = new BigDecimal("0");
        if (paymentInvoiceAllocations != null){
            for (PaymentInvoiceAllocation paymentInvoiceAllocation : paymentInvoiceAllocations){
                amountAllocated = amountAllocated.add(paymentInvoiceAllocation.getAllocatedAmount());
            }
        }

        PaymentDetailDto persistedPaymentDto = buildDto(payment);
        List<PaymentDetailDto> paymentDetailDtos = new ArrayList<PaymentDetailDto>();

        if (dto.getPaymentDetailDto().getAmountToAllocate() != null
                && dto.getPaymentDetailDto().getAmountToAllocate().compareTo(payment.getTotalAmount().subtract(amountAllocated)) > 0){
            String tooMuch = "Amount to allocate is greater than the remaining payment amount";
            response.setValidationMessages(new ArrayList<String>());
            response.getValidationMessages().add(tooMuch);

            paymentDetailDtos.add(persistedPaymentDto);
            response.setDtos(paymentDetailDtos);

            return response;
        }

        if (dto.getSelectedInvoice() != null){
            PaymentInvoiceAllocation paymentInvoiceAllocation = new PaymentInvoiceAllocation();
            paymentInvoiceAllocation.setPayment(payment);
            Invoice invoice = invoiceDao.find(dto.getSelectedInvoice());
            paymentInvoiceAllocation.setInvoice(invoice);
            paymentInvoiceAllocation.setAllocatedAmount(dto.getPaymentDetailDto().getAmountToAllocate());
            paymentInvoiceAllocationDao.save(paymentInvoiceAllocation);
        }

        // TODO CHANGE

        persistedPaymentDto.setRemainingAmount(payment.getTotalAmount().subtract(
                amountAllocated.add(dto.getPaymentDetailDto().getAmountToAllocate() == null ?
                        new BigDecimal("0") : dto.getPaymentDetailDto().getAmountToAllocate())));
        persistedPaymentDto.setAllocatedAmount(amountAllocated.add(dto.getPaymentDetailDto().getAmountToAllocate()));
        paymentDetailDtos.add(persistedPaymentDto);
        response.setDtos(paymentDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return SavePaymentDetail.class;
    }


    private PaymentDetailDto buildDto(Payment payment){
        payment = paymentDao.find(payment.getId());

        PaymentDetailDto persistedPaymentDto = new PaymentDetailDto();

        persistedPaymentDto.setCustomerReference(payment.getCustomerReference());
        persistedPaymentDto.setDescription(payment.getDescription());
        persistedPaymentDto.setPaymentNumber(payment.getPaymentNumber());
        persistedPaymentDto.setPaymentDate(payment.getPaymentDate());
        persistedPaymentDto.setTotalAmount(payment.getTotalAmount());
        if (payment.getCustomer() != null) {
            persistedPaymentDto.setCustomerId(payment.getCustomer().getId());
        }
        persistedPaymentDto.setId(payment.getId());

        return persistedPaymentDto;
    }

}

