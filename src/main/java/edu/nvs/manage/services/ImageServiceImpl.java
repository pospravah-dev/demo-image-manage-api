package edu.nvs.manage.services;

import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.events.CustomSpringEventPublisher;
import edu.nvs.manage.validation.exceptions.ResourceNotFoundException;
import edu.nvs.manage.repository.ImageUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.ADD_IMAGE;
import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.DELETE_IMAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageUrlRepository imageUrlRepository;
    private final CustomSpringEventPublisher eventPublisher;

    @Override
    public Mono<Long> addImageUrl(AddImageUrl imageUrl) {
            return imageUrlRepository.save(
                        ImageUrl.builder().url(imageUrl.url()).duration(imageUrl.duration()).build()
                    )
                    .map(ImageUrl::getId)
                    .doOnSuccess( id -> eventPublisher.publishCustomEvent(ADD_IMAGE, id))
                    .onErrorMap(e -> new RuntimeException("Error saving image", e));
    }

    @Override
    public Mono<Void> deleteImageById(Long id) {
        return imageUrlRepository.existsById(id)
                .flatMap(exist -> {
                    if (exist) {
                        eventPublisher.publishCustomEvent(DELETE_IMAGE, id);
                        return imageUrlRepository.deleteById(id);
                    } else {
                        return Mono.error(new ResourceNotFoundException("No image is found for id: " + id));
                    }
                }).then();
    }

    @Override
    public Flux<ImageUrl> searchImages(String keyword, Integer duration) {
        return imageUrlRepository.findByDurationOrUrlContains(duration, keyword)
                .switchIfEmpty(Flux.error(new ResourceNotFoundException("No images are found")));
    }
}
