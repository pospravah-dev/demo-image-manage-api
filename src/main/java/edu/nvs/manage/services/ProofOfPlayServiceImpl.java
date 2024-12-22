package edu.nvs.manage.services;

import edu.nvs.manage.entity.ProofOfPlay;
import edu.nvs.manage.events.CustomSpringEventPublisher;
import edu.nvs.manage.repository.ProofOfPlayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.ADD_SLIDESHOW;
import static edu.nvs.manage.events.CustomSpringEventPublisher.ImageEvents.PROOF_OF_PLAY;

@Service
@RequiredArgsConstructor
public class ProofOfPlayServiceImpl implements ProofOfPlayService {

    private final ProofOfPlayRepository proofOfPlayRepository;
    private final CustomSpringEventPublisher eventPublisher;

    @Override
    public Mono<ProofOfPlay> recordProofOfPlay(Long slideshowId, Long imageId) {
        return proofOfPlayRepository.save(ProofOfPlay.builder()
                .slideshowId(slideshowId)
                .imageId(imageId)
                .build())
                .doOnSuccess(id -> eventPublisher.publishCustomEvent(PROOF_OF_PLAY, imageId));
    }
}
