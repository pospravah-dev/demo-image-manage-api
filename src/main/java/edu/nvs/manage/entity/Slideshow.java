package edu.nvs.manage.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("sshow.slideshow")
@Data // Getters and setters
@Builder
public class Slideshow {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    @Transient
    private List<ImageUrl> slideshowList; // no persist since it's linked by FOREIGN keys
    public Slideshow() {
    }
    public Slideshow(Long id, String title, String description, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.slideshowList = new ArrayList<>();
    }

    public Slideshow(Long id, String title, String description, LocalDateTime createdAt, List<ImageUrl> slideshowList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.slideshowList = slideshowList;
    }


}
