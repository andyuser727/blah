package com.ajs.service.payment;

import com.ajs.shared.commands.payment.LoadPaymentList;
import com.ajs.dao.PaymentDao;
import com.ajs.domain.Payment;
import com.ajs.shared.dto.payment.PaymentDetailDto;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadPaymentListService implements Handler<LoadPaymentList> {

    @Autowired
    PaymentDao paymentDao;

    public AppResponse execute(LoadPaymentList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<Payment> payments = paymentDao.findAll();

        List<PaymentDetailDto> paymentDetailDtos = new ArrayList<PaymentDetailDto>();
        if (payments != null) {
            for (Payment payment : payments) {
                PaymentDetailDto paymentDetailDto = new PaymentDetailDto();
                paymentDetailDto.setId(payment.getId());
                paymentDetailDto.setDescription(payment.getDescription());
                paymentDetailDto.setTotalAmount(payment.getTotalAmount());
                paymentDetailDto.setPaymentNumber(payment.getPaymentNumber());
                paymentDetailDto.setPaymentDate(payment.getPaymentDate());
                if (payment.getCustomer() != null) {
                    paymentDetailDto.setCustomerId(payment.getCustomer().getId());
                    paymentDetailDto.setCustomerName(payment.getCustomer().getFirstName() + " " + payment.getCustomer().getSurName());
                }
                paymentDetailDto.setCustomerReference(payment.getCustomerReference());
                paymentDetailDtos.add(paymentDetailDto);
            }
        }
        response.setDtos(paymentDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadPaymentList.class;
    }


}

