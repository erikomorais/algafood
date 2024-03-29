package com.example.algafood.domain.model;

import com.example.algafood.Groups;
import com.example.algafood.core.validation.TaxaFrete;
import com.example.algafood.core.validation.ValorZeroIncluiDescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ValorZeroIncluiDescricao( valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Getter
@Setter
@Entity
@Table(name = "restaurante")
public class Restaurante {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @TaxaFrete
    @Column(name="taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Valid
    @ConvertGroup(to= Groups.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name="cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @Column(nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime dataCadastro;

    @Column(nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurante that = (Restaurante) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}