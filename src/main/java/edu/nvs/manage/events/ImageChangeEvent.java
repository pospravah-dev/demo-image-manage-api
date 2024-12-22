package edu.nvs.manage.events;

import org.springframework.context.ApplicationEvent;

public class ImageChangeEvent extends ApplicationEvent {
    private CustomSpringEventPublisher.ImageEvents message;
    private Long id;


    public ImageChangeEvent(Object source, CustomSpringEventPublisher.ImageEvents message, Long id) {
        super(source);
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message.name();
    }

    public String toString() {
        return "ImageChangeEvent(message=" + this.getMessage() + ", id=" + this.id + ")";
    }
}