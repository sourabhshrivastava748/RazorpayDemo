package com.example.RazorpayDemo.controller;

import com.example.RazorpayDemo.model.PaymentDetails;
import com.example.RazorpayDemo.service.PaymentOrchestrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PaymentGatewayController {

    private PaymentOrchestrationService paymentOrchestrationService;

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/modal")
    public String modal() {
        return "modal";
    }

    @RequestMapping(value = "/payment/initiate", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentDetails paymentDetails) {
        paymentOrchestrationService = new PaymentOrchestrationService();
        return paymentOrchestrationService.initiatePayment(paymentDetails);
    }

}
