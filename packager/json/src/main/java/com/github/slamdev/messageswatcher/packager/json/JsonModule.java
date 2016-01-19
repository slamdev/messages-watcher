package com.github.slamdev.messageswatcher.packager.json;

import com.github.slamdev.messageswatcher.packager.spi.Packager;
import com.github.slamdev.messageswatcher.packager.spi.PackagerModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class JsonModule extends AbstractModule implements PackagerModule {

    @Override
    protected void configure() {
        Multibinder<Packager> binder = Multibinder.newSetBinder(binder(), Packager.class);
        binder.addBinding().to(JsonPackager.class);
    }
}
