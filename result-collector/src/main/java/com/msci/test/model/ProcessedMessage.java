package com.msci.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ProcessedMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    public ProcessedMessage(String message) {
        this.message = message;
    }
}
