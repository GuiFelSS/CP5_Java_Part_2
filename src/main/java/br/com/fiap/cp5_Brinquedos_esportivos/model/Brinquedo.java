package br.com.fiap.cp5_Brinquedos_esportivos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TDS_SEC_MVC_TB_BRINQUEDOS")
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "idade_recomendada")
    private Integer idadeRecomendada;

    public Brinquedo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(Integer idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }
}