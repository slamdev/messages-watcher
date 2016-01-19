package com.github.slamdev.messageswatcher.storage.dropbox;

import com.github.slamdev.messageswatcher.storage.spi.Storage;
import com.github.slamdev.messageswatcher.storage.spi.StorageModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class DropboxModule extends AbstractModule implements StorageModule {

    @Override
    protected void configure() {
        Multibinder<Storage> binder = Multibinder.newSetBinder(binder(), Storage.class);
        binder.addBinding().to(DropboxStorage.class);
    }
}
