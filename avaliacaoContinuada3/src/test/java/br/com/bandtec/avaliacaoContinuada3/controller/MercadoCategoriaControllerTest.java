package br.com.bandtec.avaliacaoContinuada3.controller;

import br.com.bandtec.avaliacaoContinuada3.dominio.Mercado;
import br.com.bandtec.avaliacaoContinuada3.dominio.MercadoCategoria;
import br.com.bandtec.avaliacaoContinuada3.repository.MercadoCategoriaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MercadoCategoriaControllerTest {

    @Autowired
    MercadoCategoriaController controller;

    @MockBean
    MercadoCategoriaRepository repository;

    @Test
    void getCategoriasComRegistros() {
        List<MercadoCategoria> mercadoTeste = Arrays.asList(new MercadoCategoria(), new MercadoCategoria());

        Mockito.when(repository.findAll()).thenReturn(mercadoTeste);

        ResponseEntity<List<Mercado>> resposta = controller.getCategoria();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void getCategoriaSemRegistro() {
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<MercadoCategoria>> resposta = controller.getCategoria();

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    void postCategoriaSemErro() {

        MercadoCategoria categoria = new MercadoCategoria();
        categoria.setNome("Leite");

        ResponseEntity resposta = controller.postCategoria(categoria);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void postCategoriaComErro() {
        MercadoCategoria categoria = new MercadoCategoria();
        categoria.setNome(null);

        ResponseEntity resposta = controller.postCategoria(categoria);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void deleteCategoria() {
        int idTeste = 2;
        MercadoCategoria categoria = new MercadoCategoria();

        Mockito.when(repository.findById(idTeste));

        ResponseEntity resposta = controller.deleteCategoria(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(idTeste, categoria.getId());
    }
}