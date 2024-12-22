package edu.nvs.manage.dto;

import edu.nvs.manage.entity.ImageUrl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddSlideshow(
        @NotNull @NotBlank(message = "Slideshow title required")
        String title,
        String description,
        @NotNull
        List<AddImageUrl> imageList) {
}
