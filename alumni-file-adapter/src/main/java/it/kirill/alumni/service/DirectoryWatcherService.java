package it.kirill.alumni.service;

import io.methvin.watcher.DirectoryChangeEvent;
import io.methvin.watcher.DirectoryWatcher;
import it.kirill.alumni.messaging.RequestProducer;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
                        Path pathToFile = event.path();
                        if (pathToFile.toString().matches("^.*\\.json$")) {
                            log.info("Got file: {}", pathToFile);

                            File file = new File(pathToFile.toUri());
                            String alumniDtos = Files.readString(file.toPath(), StandardCharsets.UTF_8);

                            requestProducer.send(alumniDtos);

                            file.renameTo(new File(file.toPath() + ".done"));
                        } else {
                            log.error("Got incorrect file in import directory: {}", pathToFile);
                        }
                    }
                })
                .build();
    }


    private void checkIfAbsent(Path directoryToWatch) throws IOException {
        File directory = new File(directoryToWatch.toUri());
        if (!directory.exists()) {
            Files.createDirectory(directoryToWatch);
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