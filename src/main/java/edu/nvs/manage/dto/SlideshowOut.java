package edu.nvs.manage.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SlideshowOut (
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        List<ImageUrlOut> slideshowList) {
}
