package br.com.bandtec.avaliacaoContinuada3.controller;

import br.com.bandtec.avaliacaoContinuada3.dominio.Mercado;
import br.com.bandtec.avaliacaoContinuada3.dominio.MercadoCategoria;
import br.com.bandtec.avaliacaoContinuada3.repository.MercadoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MercadoControllerTest {

    @Autowired
    MercadoController controller;

    @MockBean
    MercadoRepository repository;

    @Test
    void getProdutosComRegistros() {
        List<Mercado> mercadoTeste = Arrays.asList(new Mercado(), new Mercado(), new Mercado());

        Mockito.when(repository.findAll()).thenReturn(mercadoTeste);

        ResponseEntity<List<Mercado>> resposta = controller.getProdutos();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    void getProdutosSemRegisto(){
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Mercado>> resposta = controller.getProdutos();

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    void postProdutosSemErro() {

        Mercado mercado = new Mercado();
        mercado.setProduto("Queijo");
        mercado.setPreco(12.0);
        mercado.setValidade(LocalDate.of(2021,05,28));

        ResponseEntity resposta = controller.postProdutos(mercado);
        assertEquals(201, resposta.getStatusCodeValue());

    }

    @Test
    void postProdutosComErro() {

        Mercado mercado = new Mercado();
        mercado.setProduto(null);
        mercado.setPreco(-1.0);
        mercado.setValidade(LocalDate.of(2020,05,28));

        ResponseEntity resposta = controller.postProdutos(mercado);
        assertEquals(201, resposta.getStatusCodeValue());
    }
}