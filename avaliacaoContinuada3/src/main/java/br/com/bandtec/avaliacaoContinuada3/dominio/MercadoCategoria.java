package br.com.bandtec.avaliacaoContinuada3.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class MercadoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Mercado> mercado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Mercado> getMercado() {
        return mercado;
    }

    public void setMercado(List<Mercado> mercado) {
        this.mercado = mercado;
    }
}
