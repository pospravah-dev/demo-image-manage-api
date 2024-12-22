package edu.nvs.manage.services;

import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.entity.ImageUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageService {
    Mono<Long> addImageUrl(AddImageUrl imageUrl);

    Mono<Void> deleteImageById(Long id);

    Flux<ImageUrl> searchImages(String keyword, Integer duration);
}
