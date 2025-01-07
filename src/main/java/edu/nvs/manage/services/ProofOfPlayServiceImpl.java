package edu.nvs.manage.services;

import edu.nvs.manage.entity.ProofOfPlay;
import edu.nvs.manage.events.ImageEventsDto;
import edu.nvs.manage.events.producer.ImageEventProducer;
import edu.nvs.manage.repository.ProofOfPlayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import static edu.nvs.manage.events.ImageEventsDto.ImageEvents.PROOF_OF_PLAY;

@Service
@RequiredArgsConstructor
public class ProofOfPlayServiceImpl implements ProofOfPlayService {

    private final ProofOfPlayRepository proofOfPlayRepository;
    private final ImageEventProducer ImageEventProducer;

    @Override
    public Mono<ProofOfPlay> recordProofOfPlay(Long slideshowId, Long id) {
        return proofOfPlayRepository.save(ProofOfPlay.builder()
                .slideshowId(slideshowId)
                .imageId(id)
                .build())
                .doOnSuccess( it -> ImageEventProducer.sendImageEvent(new ImageEventsDto(PROOF_OF_PLAY, id)));
    }
}
