package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Instant start = Instant.now();

        FetchOrder order = new FetchOrder();
        Future<Integer> f1 = executor.submit(order);

        CheckInventory inventory = new CheckInventory(f1.get());
        System.out.println("Order number is: " + f1.get());
        Future<Boolean> f2 = executor.submit(inventory);

        PaymentAcceptor paymentAcceptor = new PaymentAcceptor(f2.get());
        System.out.println("isInventoryPresent: " + f2.get());

        Future<String> f3 = executor.submit(paymentAcceptor);
        System.out.println(f3.get());

        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("Total time: " + timeElapsed);
        executor.shutdown();
    }
}
