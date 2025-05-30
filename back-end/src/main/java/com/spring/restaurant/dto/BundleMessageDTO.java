package com.spring.restaurant.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class BundleMessageDTO {

    @JsonProperty("message_ar")
    private String messageAr;

    @JsonProperty("message_en")
    private String messageEn;


    public BundleMessageDTO(String messageAr, String messageEn) {
        this.messageAr = messageAr;
        this.messageEn = messageEn;
    }
}
