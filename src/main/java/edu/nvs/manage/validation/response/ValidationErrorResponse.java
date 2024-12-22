package edu.nvs.manage.validation.response;

import lombok.Data;

@Data
public class ValidationErrorResponse {
    private String field;
    private String message;

    public ValidationErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
