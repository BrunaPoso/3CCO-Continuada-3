package br.com.bandtec.avaliacaoContinuada3.dominio;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Mercado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    private String produto;

    @Positive
    @NotNull
    private Double preco;

    @FutureOrPresent
    private LocalDate validade;

    @ManyToOne
    private MercadoCategoria categoria;

    private String protocolo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public MercadoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(MercadoCategoria categoria) {
        this.categoria = categoria;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
