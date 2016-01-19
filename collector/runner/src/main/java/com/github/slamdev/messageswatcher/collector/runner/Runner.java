package com.github.slamdev.messageswatcher.collector.runner;

import com.github.slamdev.messageswatcher.collector.capturer.spi.Collector;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;
import java.util.Set;

public class Runner {

    @Inject
    public Runner(Set<Collector> collectors) {
        collectors.forEach(Collector::collect);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new RunnerModule());
        injector.getInstance(Runner.class).run();
    }

    public void run() {
        System.out.println("running");
    }
}
