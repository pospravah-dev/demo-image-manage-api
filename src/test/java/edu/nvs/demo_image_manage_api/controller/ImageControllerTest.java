package edu.nvs.demo_image_manage_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nvs.manage.DemoImageManageApiApplication;
import edu.nvs.manage.controller.ImageController;
import edu.nvs.manage.dto.AddImageUrl;
import edu.nvs.manage.dto.ImageUrlOut;
import edu.nvs.manage.dto.ImageResource;
import edu.nvs.manage.entity.ImageUrl;
import edu.nvs.manage.services.ImageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DemoImageManageApiApplication.class, ImageController.class, ImageService.class})
public class ImageControllerTest extends TestBaseConfig {

    private static ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ImageService imageService;

    @BeforeAll
    public static void setUp() {
        getPostgreSQLContainer().start();
    }

    @AfterAll
    public static void tearDown() {
        getPostgreSQLContainer().stop();
    }

    @Test
    void addImageUrl() {
        AddImageUrl addImageUrl = new AddImageUrl( 1L, "http://example.com/image789.jpg", 10);
        given(imageService.addImageUrl(any(AddImageUrl.class))).willReturn(Mono.just(1L));

        webTestClient.post().uri("/api/images/addImage")
                .bodyValue(addImageUrl)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ImageResource.class)
                .returnResult().getResponseBody().id().equals(1L);
    }

    @Test
    void addImageUrlFailed() {
        AddImageUrl addImageUrl = new AddImageUrl( 1L, "httz://example.com/image789.jpg", 10);
        given(imageService.addImageUrl(any(AddImageUrl.class))).willReturn(Mono.just(1L));

        webTestClient.post().uri("/api/images/addImage")
                .bodyValue(addImageUrl)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void deleteImage() {
        given(imageService.deleteImageById(eq(1L))).willReturn(Mono.empty());

        webTestClient.delete().uri("/api/images/deleteImage/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void searchImages() {
        var ct1 = new Timestamp(System.currentTimeMillis());
        ImageUrl imageUrl1 = ImageUrl.builder().id(1L).url("http://example.com/image1.jpg").duration(10).additionDate(ct1).build();
        ImageUrl imageUrl2 = ImageUrl.builder().id(2L).url("http://example.com/image2.jpg").duration(20).additionDate(ct1).build();
        ImageUrlOut imageUrlOut1 = new ImageUrlOut(1L, "http://example.com/image1.jpg", 10, ct1);
        ImageUrlOut imageUrlOut2 = new ImageUrlOut(2L, "http://example.com/image2.jpg", 20, ct1);

        given(imageService.searchImages("example", 10)).willReturn(Flux.just(imageUrl1, imageUrl2));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                .path("/api/images/search")
                .queryParam("keyword", "example")
                .queryParam("duration", 10)
                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ImageUrlOut.class)
                .hasSize(2)
                .contains(imageUrlOut1);
    }
}
