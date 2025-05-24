package com.spring.restaurant.response;
import com.spring.restaurant.dto.BundleMessageDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError  {

    private int status;
    private String error;
    private BundleMessageDTO messages;
    private LocalDateTime time;


    public ApiError (int status, String error, BundleMessageDTO messages ) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.time = LocalDateTime.now();
    }
}