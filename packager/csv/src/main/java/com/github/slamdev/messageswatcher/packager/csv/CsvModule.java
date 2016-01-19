package com.github.slamdev.messageswatcher.packager.csv;

import com.github.slamdev.messageswatcher.packager.spi.Packager;
import com.github.slamdev.messageswatcher.packager.spi.PackagerModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class CsvModule extends AbstractModule implements PackagerModule {

    @Override
    protected void configure() {
        Multibinder<Packager> binder = Multibinder.newSetBinder(binder(), Packager.class);
        binder.addBinding().to(CsvPackager.class);
    }
}
