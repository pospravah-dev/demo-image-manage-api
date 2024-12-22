package edu.nvs.manage.services;

import edu.nvs.manage.entity.ProofOfPlay;
import reactor.core.publisher.Mono;

public interface ProofOfPlayService {
    Mono<ProofOfPlay> recordProofOfPlay(Long slideshowId, Long imageId);
}
