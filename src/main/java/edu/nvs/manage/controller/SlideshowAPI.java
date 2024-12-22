package edu.nvs.manage.controller;

import edu.nvs.manage.dto.AddSlideshow;
import edu.nvs.manage.dto.SlideshowResource;
import edu.nvs.manage.entity.ImageUrl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SlideshowAPI {
    @PostMapping("/addSlideshow")
    Mono<ResponseEntity<SlideshowResource>> addSlideshow(@Valid @RequestBody AddSlideshow slideshow);

    @DeleteMapping("/deleteSlideshow/{id}")
    Mono<Void> deleteSlideshow(@PathVariable Long id);

    @GetMapping("/{id}/slideshowOrder")
    Mono<ResponseEntity<Flux<ImageUrl>>> getSlideshowOrder(@PathVariable Long id);

    @GetMapping("/{slideshowId}/proof-of-play/{imageId}")
    Mono<Void> recordProofOfPlay(@PathVariable Long slideshowId, @PathVariable Long imageId);
}
