package com.example.publisher.controller;

import com.example.publisher.models.MapStocOptim;
import com.example.publisher.service.ProductsPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@RestController
public class PublisherController {

    private final ProductsPublisherService publisherService;

    @PostMapping
    public ResponseEntity<MapStocOptim> createMapStocOptim(@RequestBody ProductRequest request) {
        MapStocOptim product = MapStocOptim.builder()
                .id(request.id)
                .articol(request.articol)
                .grupa(request.grupa)
                .categorie(request.categorie)
                .furniz(request.furniz)
                .id_furn(request.id_furn)
                .idIntern(request.idIntern)
                .nr_zile(request.nr_zile)
                .mapStocOptimStatus(request.mapStocOptimStatus)
                .mapStocOptimAction(request.mapStocOptimAction)
                .build();

        publisherService.sendProduct(product);

        return ResponseEntity.ok(product);
    }

    public record ProductRequest(
            long id,
            String articol,
            String grupa,
            String categorie,
            String furniz,
            int id_furn,
            String idIntern,
            MapStocOptim.MapStocOptimStatus mapStocOptimStatus,
            MapStocOptim.MapStocOptimAction mapStocOptimAction,
            int nr_zile
    ) {}

}
