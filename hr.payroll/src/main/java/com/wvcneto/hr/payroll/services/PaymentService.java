package com.wvcneto.hr.payroll.services;

import com.wvcneto.hr.payroll.entities.Payment;
import com.wvcneto.hr.payroll.entities.Worker;
import com.wvcneto.hr.payroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {

        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
