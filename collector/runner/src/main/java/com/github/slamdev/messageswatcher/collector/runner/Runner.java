package com.github.slamdev.messageswatcher.collector.runner;

import com.github.slamdev.messageswatcher.collector.capturer.skype.SkypeCollector;
import com.github.slamdev.messageswatcher.collector.capturer.spi.Collector;
import com.github.slamdev.messageswatcher.collector.capturer.viber.ViberCollector;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Runner {

    @Inject
    @Any
    private Instance<Collector> collectors;

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        LOGGER.info(new SkypeCollector().toString());
//        StartMain.main(args);
        Weld weld = new Weld();
        weld.addPackage(true, SkypeCollector.class);
        weld.addPackage(true, ViberCollector.class);
        weld.addPackage(true, Collector.class);
        weld.initialize();
        weld.shutdown();
    }

    public void run(@Observes ContainerInitialized event) {
        collectors.forEach(e -> LOGGER.info(e.toString()));
    }
}