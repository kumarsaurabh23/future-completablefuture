package com.example;

import java.util.concurrent.Callable;

public class CheckInventory implements Callable<Boolean> {

    private Integer orderNumber;
    public CheckInventory(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("inside check inventory");
        Thread.sleep(200);
        if(orderNumber.intValue() % 2 == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
