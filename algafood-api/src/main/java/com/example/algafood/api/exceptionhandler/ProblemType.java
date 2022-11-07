package com.example.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso" ),
    ERRO_NEGOCIO("/erro-negocio","Violação de regra de negócio" ),
    MENSAGEM_INCOMPREENSIVEL("/mensage-incompreensível","Mensagem incompreensível" ),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido" ),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema" ),
    DADOS_INVALIDOS("/dados-invalidos", "Dados Inválidos" );

    private final String uri;
    private final String title;

    ProblemType(String path, String title ) {
        this.title = title;
        this.uri = "http://algafood.com.br" + path;
    }
}
