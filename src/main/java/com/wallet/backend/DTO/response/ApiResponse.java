package com.wallet.backend.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String codError;
    private String messageError;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "00", "Operación exitosa", data);
    }

    public static <T> ApiResponse<T> ok(String mensaje, T data) {
        return new ApiResponse<>(true, "00", mensaje, data);
    }

    public static <T> ApiResponse<T> error(String codError, String mensaje) {
        return new ApiResponse<>(false, codError, mensaje, null);
    }
}