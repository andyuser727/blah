package com.ajs.service.payment;

import com.ajs.dao.PaymentDao;
import com.ajs.dao.PaymentInvoiceAllocationDao;
import com.ajs.domain.Payment;
import com.ajs.domain.PaymentInvoiceAllocation;
import com.ajs.shared.dto.payment.PaymentDetailDto;
import com.ajs.shared.commands.payment.LoadPaymentDetail;
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
public class LoadPaymentDetailService implements Handler<LoadPaymentDetail> {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentInvoiceAllocationDao paymentInvoiceAllocationDao;

    public AppResponse execute(LoadPaymentDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        Payment payment = paymentDao.find(dto.getPaymentId());
        PaymentDetailDto paymentDetailDto = new PaymentDetailDto();
        paymentDetailDto.setCustomerReference(payment.getCustomerReference());
        paymentDetailDto.setDescription(payment.getDescription());
        paymentDetailDto.setPaymentNumber(payment.getPaymentNumber());
        paymentDetailDto.setPaymentDate(payment.getPaymentDate());
        paymentDetailDto.setTotalAmount(payment.getTotalAmount());
        if (payment.getCustomer() != null) {
            paymentDetailDto.setCustomerId(payment.getCustomer().getId());
        }
        paymentDetailDto.setId(payment.getId());

        // TODO CHANGE

        List<PaymentInvoiceAllocation> paymentInvoiceAllocations = paymentInvoiceAllocationDao.findByPaymentId(payment.getId());
        BigDecimal amountAllocated = new BigDecimal("0");
        if (paymentInvoiceAllocations != null){

            for (PaymentInvoiceAllocation paymentInvoiceAllocation : paymentInvoiceAllocations){
                amountAllocated = amountAllocated.add(paymentInvoiceAllocation.getAllocatedAmount());

            }
        }

        paymentDetailDto.setRemainingAmount(payment.getTotalAmount().subtract(amountAllocated));
        paymentDetailDto.setAllocatedAmount(amountAllocated);

        List<PaymentDetailDto> paymentDetailDtos = new ArrayList<PaymentDetailDto>();
        paymentDetailDtos.add(paymentDetailDto);
        response.setDtos(paymentDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadPaymentDetail.class;
    }


}

