package com.example.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso" ),
    ERRO_NEGOCIO("/erro-negocio","Violação de regra de negócio" ),
    MENSAGEM_INCOMPREENSIVEL("/mensage-incompreensível","Mensagem incompreensível" ),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido" );

    private final String uri;
    private final String title;

    ApiErrorType(String path, String title ) {
        this.title = title;
        this.uri = "http://algafood.com.br" + path;
    }
}
