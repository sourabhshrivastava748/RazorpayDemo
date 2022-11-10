package com.example.RazorpayDemo.model;

public class Response {

    private int statusCode;
    private CheckoutOptions checkoutOptions;

    public Response(int statusCode, CheckoutOptions checkoutOptions) {
        this.statusCode = statusCode;
        this.checkoutOptions = checkoutOptions;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public CheckoutOptions getCheckoutOptions() {
        return checkoutOptions;
    }

    public void setCheckoutOptions(CheckoutOptions checkoutOptions) {
        this.checkoutOptions = checkoutOptions;
    }
}
