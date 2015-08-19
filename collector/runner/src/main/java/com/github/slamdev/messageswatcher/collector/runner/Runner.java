package com.github.slamdev.messageswatcher.collector.runner;

import com.github.slamdev.messageswatcher.collector.capturer.spi.Collector;

public class Runner {
    public static void main(String[] args) {
        Collector collector = null;
        System.out.print("runner" + collector);
    }
}