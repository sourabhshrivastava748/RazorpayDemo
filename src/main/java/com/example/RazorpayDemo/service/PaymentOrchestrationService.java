package com.example.RazorpayDemo.service;

import com.example.RazorpayDemo.model.CheckoutOptions;
import com.example.RazorpayDemo.model.PaymentDetails;
import com.example.RazorpayDemo.model.Response;

import com.google.gson.Gson;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PaymentOrchestrationService {

    private RazorpayClient razorpayClient;
    private static final String SECRET_ID = "rzp_test_vwXX1ElgUW99j7";
    private static final String SECRET_KEY = "2GFKZmY070Gp7hS6i5IGhNtx";

    private static Gson gson = new Gson();

    public ResponseEntity<String> initiatePayment(PaymentDetails paymentDetails) {
        try {
            // Initialize razorpay client
            razorpayClient = new RazorpayClient(SECRET_ID, SECRET_KEY);

            // Create an order
            int amountInPaise = convertRupeeToPaise(paymentDetails.getAmount());
            String buyerName = paymentDetails.getBuyerName();
            Order order = createOrder(amountInPaise);

            // Get checkout options
            CheckoutOptions checkoutOptions = getCheckoutOptions(order.get("id"), amountInPaise, buyerName);

            return new ResponseEntity<>(gson.toJson(getResponse(200, checkoutOptions)), HttpStatus.OK);

        } catch (RazorpayException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>(gson.toJson(getResponse(500, new CheckoutOptions())), HttpStatus.EXPECTATION_FAILED);
    }

    private Order createOrder(int amount) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_receipt_test");

        return razorpayClient.orders.create(orderRequest);
    }

    private CheckoutOptions getCheckoutOptions(String orderId, int amount, String buyerName) {
        CheckoutOptions checkoutOptions = new CheckoutOptions();
        checkoutOptions.setAmount(amount);
        checkoutOptions.setCurrency("INR");
        checkoutOptions.setKey(SECRET_ID);
        checkoutOptions.setName("Test Merchant Name");
        checkoutOptions.setOrderId(orderId);
        checkoutOptions.setDescription("Test Transaction");
        checkoutOptions.setBuyerName(buyerName);

        return checkoutOptions;
    }

    private Response getResponse(int statusCode, CheckoutOptions checkoutOptions) {
        return new Response(statusCode, checkoutOptions);
    }

    private int convertRupeeToPaise(int amountInRupee) {
        return (amountInRupee * 100);
    }
}
