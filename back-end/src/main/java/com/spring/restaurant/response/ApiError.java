package com.spring.restaurant.response;
import com.spring.restaurant.dto.BundleMessageDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class ApiError  {

    private int status;
    private String field;
    private BundleMessageDTO messages;
    private LocalDateTime time;


    public ApiError (int status, String field, BundleMessageDTO messages ) {
        this.status = status;
        this.field = field;
        this.messages = messages;
        this.time = LocalDateTime.now();
    }
}