package br.senac.loja.controladores;

import java.util.List;

public record ApiError (
        Integer code,

        String status,

        List<String> errors

) {
}