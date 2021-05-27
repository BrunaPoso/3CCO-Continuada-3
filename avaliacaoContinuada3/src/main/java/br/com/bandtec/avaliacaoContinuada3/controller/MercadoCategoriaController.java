package br.com.bandtec.avaliacaoContinuada3.controller;

import br.com.bandtec.avaliacaoContinuada3.dominio.MercadoCategoria;
import br.com.bandtec.avaliacaoContinuada3.repository.MercadoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class MercadoCategoriaController {

    List<MercadoCategoriaController> lista;

    @Autowired
    private MercadoCategoriaRepository repository;

    @GetMapping
    public ResponseEntity getCategoria(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity postCategoria(@RequestBody @Valid MercadoCategoria novaCategoria) {
        repository.save(novaCategoria);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable int id){
        repository.deleteById(id);
        return ResponseEntity.status(204).body("Deletado com sucesso!");
    }

    @GetMapping("/recursivo/{id}")
    public ResponseEntity getAnteriores(@PathVariable int id){
        Integer valor = Math.toIntExact(repository.count());
        return ResponseEntity.status(200).body(getAnteriores(valor));
    }


}
