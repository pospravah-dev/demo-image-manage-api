package edu.nvs.manage.events.consumer;

import edu.nvs.manage.events.ImageEventsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.function.Consumer;

@Service
@Slf4j
public class ReactiveImageConsumer {

    @Bean
    public Consumer<Flux<String>> processImage() {
        return flux -> flux
                .doOnNext(imageEvent -> {
                    log.info("Received image event --> processImage-in-0: " + imageEvent.toString());
                })
                .subscribe();
    }
}
