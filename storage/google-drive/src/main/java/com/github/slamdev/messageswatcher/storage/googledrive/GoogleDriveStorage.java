package com.github.slamdev.messageswatcher.storage.googledrive;

import com.github.slamdev.messageswatcher.storage.spi.Storage;

public class GoogleDriveStorage implements Storage {

    @Override
    public void store() {
        System.out.println("GoogleDrive storage");
    }
}
