package edu.nvs.manage.repository;

import edu.nvs.manage.entity.ProofOfPlay;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofOfPlayRepository extends ReactiveCrudRepository<ProofOfPlay, Long> {
}