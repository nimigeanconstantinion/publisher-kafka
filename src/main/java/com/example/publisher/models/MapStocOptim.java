package com.example.publisher.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j // Adaugă asta pentru a vedea variabila 'log'
public class MapStocOptim implements Serializable {

    private long id;

    private String idIntern;

    private String articol;

    private String categorie;

    private String grupa;

    private int id_furn;

    private String furniz;

    private int nr_zile;

    private MapStocOptimStatus mapStocOptimStatus;




    @Override
    public String toString() {
        return "MapStocOptim{" +
                "id=" + id +
                ", idIntern='" + idIntern + '\'' +
                ", articol='" + articol + '\'' +
                ", categorie='" + categorie + '\'' +
                ", grupa='" + grupa + '\'' +
                ", id_furn=" + id_furn +
                ", furniz='" + furniz + '\'' +
                ", nr_zile=" + nr_zile +
                '}';
    }

    public enum MapStocOptimStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }
}
