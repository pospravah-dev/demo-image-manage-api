package edu.nvs.manage.repository;

import edu.nvs.manage.entity.Slideshow;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideshowRepository extends ReactiveCrudRepository<Slideshow, Long> {
}
