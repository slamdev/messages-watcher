package com.github.slamdev.messageswatcher.collector.runner;

import com.github.slamdev.messageswatcher.collector.capturer.spi.CollectorModule;
import com.google.inject.AbstractModule;

import java.util.ServiceLoader;

public class RunnerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Runner.class);
        ServiceLoader<CollectorModule> collectors = ServiceLoader.load(CollectorModule.class);
        collectors.forEach(this::install);
    }
}
