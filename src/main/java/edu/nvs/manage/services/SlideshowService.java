package edu.nvs.manage.services;

import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.dto.AddSlideshow;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.entity.Slideshow;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SlideshowService {
    Mono<Long> addSlideshow(AddSlideshow slideshow);

    Mono<Void> deleteSlideshowById(Long id);

    Flux<ImageUrl> getSlideshowOrder(Long id);
}
