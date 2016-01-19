package com.github.slamdev.messageswatcher.packager.json;

import com.github.slamdev.messageswatcher.packager.spi.Packager;

class JsonPackager implements Packager {

    @Override
    public void createPackage() {
        System.out.println("Json packaging");
    }
}
