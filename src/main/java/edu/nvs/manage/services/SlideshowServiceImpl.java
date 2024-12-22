package edu.nvs.manage.services;

import edu.nvs.manage.dto.AddSlideshow;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.entity.Slideshow;
import edu.nvs.manage.events.CustomSpringEventPublisher;
import edu.nvs.manage.validation.exceptions.ResourceNotFoundException;
import edu.nvs.manage.repository.ImageUrlRepository;
import edu.nvs.manage.repository.SlideshowImageUrlRepository;
import edu.nvs.manage.repository.SlideshowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.stream.Collectors;

import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.ADD_SLIDESHOW;
import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.DELETE_SLIDESHOW;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlideshowServiceImpl implements SlideshowService {
    private final SlideshowRepository slideshowRepository;
    private final ImageUrlRepository imageUrlRepository;
    private final SlideshowImageUrlRepository slideshowImageUrlRepository;
    private final CustomSpringEventPublisher eventPublisher;

    @Override
    public Mono<Long> addSlideshow(AddSlideshow addSlideshow) {
        return createSlideshowAndFillImages(addSlideshow)
                .doOnSuccess(id -> eventPublisher.publishCustomEvent(ADD_SLIDESHOW, id));
    }

    @Transactional
    private Mono<Long> createSlideshowAndFillImages(AddSlideshow addSlideshow) {
        Slideshow slideshow = new Slideshow();
        BeanUtils.copyProperties(addSlideshow, slideshow);
//        var slideshow = Slideshow.builder()
//                .slideshowList(addSlideshow.imageList().stream().map(new ImageUrl))
//                .description(addSlideshow.description())
//                .title(addSlideshow.title())
//                .build();
        return slideshowRepository.save(slideshow)
                .flatMap(slideshow1 ->
                        Mono.when(addSlideshow.imageList().stream()
                                        .map(image -> insertSlideshowToImageRelation(slideshow1.getId(), image.id())
                                                .subscribeOn(Schedulers.boundedElastic()))
                                        .collect(Collectors.toList()))
                                .thenReturn(slideshow1))
                .map(Slideshow::getId);
    }

    private Mono<Void> insertSlideshowToImageRelation(Long slideshowId, Long imageId) {
        return imageUrlRepository.existsById(imageId)
                .flatMap(exists -> {
                    if (exists) {
                        return slideshowImageUrlRepository.insertSlideshowImageUrl(slideshowId, imageId)
                                .onErrorMap(e -> new RuntimeException("Error saving slideshow", e))
                                .then(Mono.empty());
                    } else {
                        log.error("No image id {} found to add slideshow id: {}.", imageId, slideshowId);
                        return Mono.error(new ResourceNotFoundException("No image id " + imageId + " found to add to slideshow id: " + slideshowId));
                    }
                });
    }

    @Override
    public Mono<Void> deleteSlideshowById(Long id) {
        return slideshowRepository.existsById(id)
                        .flatMap( exist -> {
                            if(exist){
                                return slideshowImageUrlRepository.deleteAllBySlideshowId(id)
                                        .then(slideshowRepository.deleteById(id))
                                        .doOnSuccess(it -> eventPublisher.publishCustomEvent(DELETE_SLIDESHOW, id))
                                        .then(Mono.empty());
                            } else {
                                return Mono.error(new ResourceNotFoundException("No slideshow found: "+ id + "."));
                            }
                        }
                );
    }

    @Override
    public Flux<ImageUrl> getSlideshowOrder(Long id) {
        return getSlideshowById(id)
                .flatMapMany(this::getImageUrlsBySlideshow)
                .switchIfEmpty(Flux.error(new ResourceNotFoundException("Slideshow not found with id " + id)));
    }

    private Mono<Slideshow> getSlideshowById(Long id) {
        return slideshowRepository.existsById(id)
                .flatMap(exist -> {
                    if(exist) {
                        return slideshowRepository.findById(id)
                                .flatMap(slideshow -> imageUrlRepository.findImageUrlsBySlideshowId(id).collectList()
                                        .doOnNext(slideshow::setSlideshowList)
                                        .thenReturn(slideshow));
                    } else {
                        return Mono.error(new ResourceNotFoundException("No images is found by slideshow id: " + id));
                    }
                });
    }

    private Flux<ImageUrl> getImageUrlsBySlideshow(Slideshow slideshow) {
        return imageUrlRepository.findAllByIdIn(
                slideshow.getSlideshowList().stream()
                        .map(ImageUrl::getId)
                        .collect(Collectors.toList()))
                .sort((o1, o2) -> o1.getAdditionDate().compareTo(o2.getAdditionDate()));
    }
}
