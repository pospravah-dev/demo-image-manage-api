package edu.nvs.manage.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Table("sshow.slideshow_image_url")
@Data
@ToString
@Builder
public class SlideshowImageUrl {

    private Long slideshowId;
    private Long imageId;
}
