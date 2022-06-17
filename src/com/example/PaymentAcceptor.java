package com.example;

import java.util.concurrent.Callable;

public class PaymentAcceptor implements Callable<String> {

    private Boolean isInventoryPresent;
    public PaymentAcceptor(Boolean isInventoryPresent) {
        this.isInventoryPresent = isInventoryPresent;
    }

    @Override
    public String call() throws Exception {
        System.out.println("inside payment acceptor");
        Thread.sleep(200);
        if(isInventoryPresent) {
            return "payment accepted";
        } else {
            return "payment not accepted";
        }
    }
}
