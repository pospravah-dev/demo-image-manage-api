package edu.nvs.demo_image_manage_api.controller;

import edu.nvs.manage.DemoImageManageApiApplication;
import edu.nvs.manage.controller.SlideshowController;
import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.dto.AddSlideshow;
import edu.nvs.manage.dto.ImageUrlOut;
import edu.nvs.manage.dto.SlideshowResource;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.services.ProofOfPlayService;
import edu.nvs.manage.services.SlideshowService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.sql.Timestamp;

@SpringBootTest(classes = {DemoImageManageApiApplication.class, SlideshowController.class, SlideshowService.class, ProofOfPlayService.class})
public class SlideshowControllerTest extends TestBaseConfig {

    @Autowired private WebTestClient webTestClient;
    @MockitoBean private SlideshowService slideshowService;
    @MockitoBean private ProofOfPlayService proofOfPlayService;

    @BeforeAll
    public static void setUp() {
        getPostgreSQLContainer().start();
    }

    @AfterAll
    public static void tearDown() {
        getPostgreSQLContainer().stop();
    }

    @Test
    void addSlideshow() {
        var ct1 = new Timestamp(System.currentTimeMillis());
        AddImageUrl imageUrl1 = new AddImageUrl(1L,"http://example.com/image1.jpg", 10);
        AddImageUrl imageUrl2 = new AddImageUrl(2L,"http://example.com/image2.jpg", 20);

        AddSlideshow addSlideshow = new AddSlideshow("Title", "Description", List.of(imageUrl1, imageUrl2));
        when(slideshowService.addSlideshow(any(AddSlideshow.class))).thenReturn(Mono.just(1L));

        webTestClient.post().uri("/api/slideshows/addSlideshow")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(addSlideshow)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SlideshowResource.class)
                .returnResult().getResponseBody().id().equals(1L);
    }

    @Test
    void addSlideshowFailed() {
        var ct1 = new Timestamp(System.currentTimeMillis());
        AddSlideshow addSlideshow = new AddSlideshow("Title", "Description", null );
        when(slideshowService.addSlideshow(any(AddSlideshow.class))).thenReturn(Mono.just(1L));

        webTestClient.post().uri("/api/slideshows/addSlideshow")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(addSlideshow)
                .exchange()
                .expectStatus().isBadRequest();
    }


    @Test
    void deleteSlideshow() {
        when(slideshowService.deleteSlideshowById(any(Long.class))).thenReturn(Mono.empty());

        webTestClient.delete().uri("/api/slideshows/deleteSlideshow/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getSlideshowOrder() {
        var ct1 = new Timestamp(System.currentTimeMillis());
        ImageUrl imageUrl1 = ImageUrl.builder().id(1L).url("http://example.com/image1.jpg").duration(10).additionDate(ct1).build();
        ImageUrl imageUrl2 = ImageUrl.builder().id(2L).url("http://example.com/image2.jpg").duration(20).additionDate(ct1).build();
        Flux<ImageUrl> imageUrlFlux = Flux.just(imageUrl1, imageUrl2);
        ImageUrlOut imageUrlOut1 = new ImageUrlOut(1L, "http://example.com/image1.jpg", 10, ct1);
        ImageUrlOut imageUrlOut2 = new ImageUrlOut(2L, "http://example.com/image2.jpg", 20, ct1);

        when(slideshowService.getSlideshowOrder(any(Long.class))).thenReturn(imageUrlFlux);

        webTestClient.get().uri("/api/slideshows/1/slideshowOrder")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ImageUrlOut.class)
                .hasSize(2)
                .contains(imageUrlOut1);
    }

    @Test
    void recordProofOfPlay() {
        when(proofOfPlayService.recordProofOfPlay(any(Long.class), any(Long.class))).thenReturn(Mono.empty());

        webTestClient.get().uri("/api/slideshows/1/proof-of-play/2")
                .exchange()
                .expectStatus().isOk();
    }
}
