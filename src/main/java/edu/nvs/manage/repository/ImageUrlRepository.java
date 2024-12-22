package edu.nvs.manage.repository;

import edu.nvs.manage.entity.ImageUrl;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ImageUrlRepository extends ReactiveCrudRepository<ImageUrl, Long> {
    Flux<ImageUrl> findAllByIdIn(List<Long> ids);

    @Query("SELECT * FROM sshow.image_url WHERE duration = :duration OR url LIKE :url")
    Flux<ImageUrl> findByDurationOrUrlContains(int duration, String url);

    @Query("SELECT iu.* FROM sshow.image_url iu " +
            "JOIN sshow.slideshow_image_url siu ON iu.id = siu.image_id " +
            "WHERE siu.slideshow_id = :slideshowId")
    Flux<ImageUrl> findImageUrlsBySlideshowId(Long slideshowId);
}
