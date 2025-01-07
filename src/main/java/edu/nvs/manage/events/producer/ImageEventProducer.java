package edu.nvs.manage.events.producer;

import edu.nvs.manage.events.ImageEventsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageEventProducer {

    private final StreamBridge streamBridge;

    public void sendImageEvent(ImageEventsDto imageEvent) {
        streamBridge.send("processImage-out-0", MessageBuilder.withPayload(imageEvent).build());
        log.info("Sent image event -> processImage-out-0: " + imageEvent.toString());
    }
}
