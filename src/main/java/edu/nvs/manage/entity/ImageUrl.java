package edu.nvs.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("sshow.image_url")
@Data
@ToString
@Builder
public class ImageUrl {
    @Id
    private Long id;
    private String url;
    @Min(Integer.MIN_VALUE)
    @Max(Integer.MAX_VALUE)
    private int duration;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp additionDate;
}
