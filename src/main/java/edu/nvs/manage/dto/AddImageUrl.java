package edu.nvs.manage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.nvs.manage.validation.custom.ValidDuration;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
public record AddImageUrl(
        Long id,
        @URL(message = "Invalid image URL format")
        @Pattern(regexp = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp|webp))$)", message = "URL does not contain a valid image format (JPEG, PNG, WEBP, etc.)")
        String url,
        @ValidDuration(message = "Duration must be between 1 and 3600 seconds")
        int duration
) {}