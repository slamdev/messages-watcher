package com.github.slamdev.messageswatcher.packager.csv;

import com.github.slamdev.messageswatcher.packager.spi.Packager;

public class CsvPackager implements Packager {

    @Override
    public void createPackage() {
        System.out.println("Csv packaging");
    }
}
