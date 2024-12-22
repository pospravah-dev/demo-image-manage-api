package edu.nvs.manage.events;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationEventListener {

    @EventListener
    public void handleImageChangeEvent(ImageChangeEvent event) {
        // Logic to handle the proof-of-play notification
        log.info("APPLICATION IMAGE LISTENER TRIGGERED - {}", event.toString());
    }
}
