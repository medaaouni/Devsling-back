package com.assignement.car_sales_garage.domain.dtos;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiErrorResponse {

    private String message;
    private int status;
    private List<FieldError> errors;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }
}
