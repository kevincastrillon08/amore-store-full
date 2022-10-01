package com.project.sena.amorestore.controllers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    String message;
    String detail;
}
