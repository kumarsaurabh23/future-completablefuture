package com.example;

import java.util.Random;
import java.util.concurrent.Callable;

public class FetchOrder implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("inside fetch order");
        Thread.sleep(200);
        return new Random().nextInt(5);
    }
}
