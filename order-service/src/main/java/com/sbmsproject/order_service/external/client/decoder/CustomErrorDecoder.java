package com.sbmsproject.order_service.external.client.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbmsproject.order_service.exception.CustomExcpetion;
import com.sbmsproject.order_service.external.client.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        log.info("{};", response.request().url());
        log.info("{};", response.request().headers());
        try {
            ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomExcpetion(errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
