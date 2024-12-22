package edu.nvs.manage.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final ImageEvents message, Long id) {
        System.out.println("Publishing custom event " + message );
        ImageChangeEvent customEvent = new ImageChangeEvent(this, message, id);
        applicationEventPublisher.publishEvent(customEvent);
    }
    public enum ImageEvents{
        ADD_IMAGE,
        DELETE_IMAGE,
        ADD_SLIDESHOW,
        DELETE_SLIDESHOW,

        PROOF_OF_PLAY
    }
}