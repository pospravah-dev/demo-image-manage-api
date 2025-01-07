package edu.nvs.manage.events;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ImageEventsDto {

    ImageEvents event;
    Long id;

    public ImageEventsDto(ImageEvents eventType, Long id) {
        this.event = eventType;
        this.id = id;
    }

    public enum ImageEvents{
        ADD_IMAGE,
        DELETE_IMAGE,
        ADD_SLIDESHOW,
        DELETE_SLIDESHOW,
        PROOF_OF_PLAY
    }
}
