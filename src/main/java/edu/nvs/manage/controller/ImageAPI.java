package edu.nvs.manage.controller;

import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.dto.ImageResource;
import edu.nvs.manage.entity.ImageUrl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageAPI {
    Mono<ResponseEntity<ImageResource>> addImageUrl(@Valid @RequestBody AddImageUrl imageUrl);


    Mono<ResponseEntity<Void>> deleteImage(@PathVariable Long id);

    Flux<ImageUrl> searchImages(@RequestParam String keyword, @RequestParam Integer duration);
}
