package com.github.slamdev.messageswatcher.collector.runner;

import com.github.slamdev.messageswatcher.collector.capturer.spi.CollectorModule;
import com.github.slamdev.messageswatcher.packager.spi.PackagerModule;
import com.github.slamdev.messageswatcher.storage.spi.StorageModule;
import com.google.inject.AbstractModule;

import java.util.ServiceLoader;

public class RunnerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Runner.class);
        ServiceLoader<CollectorModule> collectors = ServiceLoader.load(CollectorModule.class);
        collectors.forEach(this::install);
        ServiceLoader<PackagerModule> packagers = ServiceLoader.load(PackagerModule.class);
        packagers.forEach(this::install);
        ServiceLoader<StorageModule> storages = ServiceLoader.load(StorageModule.class);
        storages.forEach(this::install);
    }
}
