package com.example.publisher.service;

import com.example.publisher.models.MapStocOptim;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductsPublisherService {


    private final KafkaTemplate<String, MapStocOptim> kafkaTemplate;

    @Value("${app.kafka.topics.products}")
    private String productsTopic;

    public CompletableFuture<SendResult<String, MapStocOptim>> sendProduct(MapStocOptim product) {
        log.info("lkhlhlkhlkh");
        log.info("📤 Trimit produs: {}", product.getId());

        // Folosim orderId ca key pentru partitioning consistent
        return kafkaTemplate.send(productsTopic, String.valueOf(product.getId()), product)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ Produs trimis cu succes: {} -> partition {}, offset {}",
                                product.getId(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("❌ Eroare la trimiterea produsului: {}",
                                product.getId(), ex);
                    }
                });
    }


    public SendResult<String, MapStocOptim> sendOrderSync(MapStocOptim product) {
        try {
            return kafkaTemplate.send(productsTopic, String.valueOf(product.getId()), product)
                    .get(); // Blochează până primește rezultatul
        } catch (Exception e) {
            log.error("❌ Eroare la trimitere sincronă: {}", e.getMessage());
            throw new RuntimeException("Failed to send product", e);
        }
    }
}
