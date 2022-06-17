package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Instant start = Instant.now();

        Supplier<Integer> order = () -> {
            System.out.println("inside fetch order");
            sleep(200);
            return new Random().nextInt(5);
        };

        Function<Integer, Boolean> inventory = (orderNumber) -> {
            System.out.println("inside check inventory");
            sleep(200);
            if(orderNumber.intValue() % 2 == 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        };

        Consumer<Boolean> paymentAcceptor = (isInventoryPresent) -> {
            System.out.println("inside payment acceptor");
            sleep(200);
            if(isInventoryPresent) {
                System.out.println("payment accepted");
            } else {
                System.out.println("payment not accepted");
            }
        };

        CompletableFuture.supplyAsync(order, executor).thenApply(inventory).thenAccept(paymentAcceptor);
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("Total time: " + timeElapsed);
        sleep(2000);
        executor.shutdown();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
