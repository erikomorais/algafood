package com.example.algafood.api.exceptionhandler;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> trataEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = ApiError.builder().dataHora(LocalDateTime.now()).mensagem(status.getReasonPhrase()).build();
        }else if (body instanceof String){
            body = ApiError.builder().dataHora(LocalDateTime.now()).mensagem((String) body).build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
