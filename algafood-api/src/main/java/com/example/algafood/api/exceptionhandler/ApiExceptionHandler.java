package com.example.algafood.api.exceptionhandler;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(ex,apiError,headers,status,request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormat((InvalidFormatException)rootCause, headers, status, request);
        } else  if (rootCause instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException)rootCause, headers, status, request);
        }
        ApiErrorType apiErrorType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, status, request);
        }
        return super.handleTypeMismatch(ex,headers,status,request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(),ex.getValue(),ex.getParameter().getParameterType().getSimpleName());
        ApiError apiError = createApiErrorBuilder(status, apiErrorType, detail).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());

        ApiErrorType problemType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        ApiError problem = createApiErrorBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorType apiErrorType = ApiErrorType.MENSAGEM_INCOMPREENSIVEL;
        String path = joinPath(ex.getPath());
        String detail = String.format("A propriedade '%s'  recebeu o valor '%s' que é de um tipo inválido. " +
                " Corrija e informe um valor compatível com o tipo %s",path, ex.getValue(), ex.getTargetType().getSimpleName());
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> path) {
        return path.stream().map(a -> a.getFieldName()).collect(Collectors.joining("."));
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> trataEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorType apiErrorType = ApiErrorType.RECURSO_NAO_ENCONTRADO;
        String detail = e.getMessage();
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiErrorType apiErrorType = ApiErrorType.ENTIDADE_EM_USO;
        String detail = e.getMessage();
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorType apiErrorType = ApiErrorType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        ApiError apiError = createApiErrorBuilder(status,apiErrorType,detail).build();
        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = ApiError.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }else if (body instanceof String){
            body = ApiError.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiError.ApiErrorBuilder createApiErrorBuilder(HttpStatus status, ApiErrorType type, String detail ){
        return ApiError
                .builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);

    }
}
