package edu.nvs.manage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public record ImageUrlOut(
        Long id,
        String url,
        int duration,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        Timestamp additionDate
) {
}
