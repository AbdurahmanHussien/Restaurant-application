package com.spring.restaurant.service;
import com.spring.restaurant.dto.BundleMessageDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
@Getter
@Setter
public class BundleTranslatorService {

    private static ResourceBundleMessageSource messageSource;

    public BundleTranslatorService (ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public   BundleMessageDTO getBundleMessages(String key) {
        return new BundleMessageDTO(
                messageSource.getMessage(key,null , new Locale("ar")),
                messageSource.getMessage(key,null , new Locale("en"))

        );
    }



}
