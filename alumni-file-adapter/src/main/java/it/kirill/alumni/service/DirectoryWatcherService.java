package it.kirill.alumni.service;

import io.methvin.watcher.DirectoryChangeEvent;
import io.methvin.watcher.DirectoryWatcher;
import it.kirill.alumni.messaging.RequestProducer;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class DirectoryWatcherService {

    private final DirectoryWatcher watcher;

    public DirectoryWatcherService(Path directoryToWatch, RequestProducer requestProducer) throws IOException {
        checkIfAbsent(directoryToWatch);
        this.watcher = DirectoryWatcher.builder()
                .path(directoryToWatch)
                .listener(event -> {
                    if (event.eventType() == DirectoryChangeEvent.EventType.CREATE) {
                        log.info("got file {}", event.path().toString());
                        String alumniDtos = Files.readString(event.path());
                        requestProducer.send(alumniDtos);
                        new File(event.path().toUri()).delete(); //todo check result
                    }
                })
                .build();
    }

    private void checkIfAbsent(Path directoryToWatch) {
        File directory = new File(directoryToWatch.toUri());
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @PostConstruct
    public CompletableFuture<Void> watch() {
        return watcher.watchAsync();
    }

    @PreDestroy
    public void stopWatching() throws IOException {
        watcher.close();
    }
}