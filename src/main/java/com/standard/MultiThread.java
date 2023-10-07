//package com.standard;
//
//public class MultiThread {
//
//    public static void main(String[] args) {
//        SharedResource sharedResource = new SharedResource();
//
//        Thread producerThread = new Thread(() -> {
//            try {
//                sharedResource.produce();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread consumerThread = new Thread(() -> {
//            try {
//                sharedResource.consume();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        producerThread.start();
//        consumerThread.start();
//    }
//}
//
//class SharedResource {
//    private boolean dataProduced = false;
//
//    public synchronized void produce() throws InterruptedException {
//        while (dataProduced) {
//            wait(); // Chờ cho đến khi có sự gọi notify()
//        }
//        System.out.println("Producing data...");
//        dataProduced = true;
//        notify(); // Thông báo cho luồng khác
//    }
//
//    public synchronized void consume() throws InterruptedException {
//        while (!dataProduced) {
//            wait(); // Chờ cho đến khi có sự gọi notify()
//        }
//        System.out.println("Consuming data...");
//        dataProduced = false;
//        notify(); // Thông báo cho luồng khác
//    }
//
//}