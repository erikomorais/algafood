package com.example.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurante")
public class Restaurante {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name="taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnore
    @ManyToOne //(fetch = FetchType.LAZY)
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
}