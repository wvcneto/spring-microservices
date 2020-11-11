package com.wvcneto.hr.payroll.resources;

import com.wvcneto.hr.payroll.entities.Payment;
import com.wvcneto.hr.payroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/{worker_id}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long worker_id, @PathVariable Integer days) {
        Payment payment = paymentService.getPayment(worker_id, days);

        return ResponseEntity.ok().body(payment);
    }

}
