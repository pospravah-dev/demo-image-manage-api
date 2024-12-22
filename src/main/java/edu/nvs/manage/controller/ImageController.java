package edu.nvs.manage.controller;

import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.dto.ImageResource;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.services.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Validated
public class ImageController implements ImageAPI {
    private final ImageService imageService;

    @Override
    @PostMapping("/addImage")
    public Mono<ResponseEntity<ImageResource>> addImageUrl(@Valid @RequestBody AddImageUrl imageUrl) {
        return imageService.addImageUrl(imageUrl).map(e -> status(HttpStatus.CREATED).body(new ImageResource(e)));
    }

    @Override
    @DeleteMapping("/deleteImage/{id}")
    public Mono<ResponseEntity<Void>> deleteImage(@PathVariable Long id) {
        return imageService.deleteImageById(id).map(e -> status(HttpStatus.OK).body(e));
    }

    @Override
    @GetMapping("/search")
    public Flux<ImageUrl> searchImages(@RequestParam String keyword, @RequestParam Integer duration) {
        return imageService.searchImages(keyword, duration);
    }
}
