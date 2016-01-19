package com.github.slamdev.messageswatcher.collector.capturer.viber;

import com.github.slamdev.messageswatcher.collector.capturer.spi.Collector;
import com.github.slamdev.messageswatcher.collector.capturer.spi.CollectorModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ViberModule extends AbstractModule implements CollectorModule {

    @Override
    protected void configure() {
        Multibinder<Collector> binder = Multibinder.newSetBinder(binder(), Collector.class);
        binder.addBinding().to(ViberCollector.class);
    }
}
