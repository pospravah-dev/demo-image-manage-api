package edu.nvs.manage.repository;

import edu.nvs.manage.entity.SlideshowImageUrl;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SlideshowImageUrlRepository extends ReactiveCrudRepository<SlideshowImageUrl, Long> {

    @Query("INSERT INTO sshow.slideshow_image_url (slideshow_id, image_id) VALUES (:slideshowId, :imageId)")
    Mono<Long> insertSlideshowImageUrl(Long slideshowId, Long imageId);

    @Query("DELETE FROM sshow.slideshow_image_url WHERE slideshow_id = :slideshowId")
    Mono<Long> deleteAllBySlideshowId(Long slideshowId);
}
