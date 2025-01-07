package edu.nvs.manage.controller;

import edu.nvs.manage.dto.AddSlideshow;
import edu.nvs.manage.dto.SlideshowResource;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.services.ProofOfPlayService;
import edu.nvs.manage.services.SlideshowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class SlideshowController implements SlideshowAPI {
    private final SlideshowService slideshowService;
    private final ProofOfPlayService proofOfPlayService;

    @Override
    @PostMapping("/addSlideshow")
    public Mono<ResponseEntity<SlideshowResource>> addSlideshow(@Valid @RequestBody AddSlideshow slideshow) {
        return slideshowService.addSlideshow(slideshow).map(e -> status(HttpStatus.CREATED).body(new SlideshowResource(e)));
    }

    @Override
    @DeleteMapping("/deleteSlideshow/{id}")
    public Mono<Void> deleteSlideshow(@PathVariable Long id) {
        return slideshowService.deleteSlideshowById(id);
    }

    @Override
    @GetMapping("/slideShow/{id}/slideshowOrder")
    public Mono<ResponseEntity<Flux<ImageUrl>>> getSlideshowOrder(@PathVariable Long id) {
        return Mono.just(ResponseEntity.ok(slideshowService.getSlideshowOrder(id)));
    }

    @Override
    @PostMapping("/slideShow/{slideshowId}/proof-of-play/{imageId}")
    public Mono<Void> recordProofOfPlay(@PathVariable Long slideshowId, @PathVariable Long imageId) {
        return proofOfPlayService.recordProofOfPlay(slideshowId, imageId).then();
    }
}
