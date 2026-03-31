package com.messaging.producer.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "origins")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
}
