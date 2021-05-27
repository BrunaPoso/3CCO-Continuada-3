package br.com.bandtec.avaliacaoContinuada3.controller;

import br.com.bandtec.avaliacaoContinuada3.dominio.Mercado;
import br.com.bandtec.avaliacaoContinuada3.importacao.MercadoGrava;
import br.com.bandtec.avaliacaoContinuada3.repository.MercadoRepository;
import br.com.bandtec.avaliacaoContinuada3.PilhaObj;
import br.com.bandtec.avaliacaoContinuada3.service.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class MercadoController {

    PilhaObj<Mercado> pilha = new PilhaObj(10);
    Mercado mercado = new Mercado();
    MercadoGrava mercadoGrava = new MercadoGrava();

    @Autowired
    private MercadoRepository repository;

    @Autowired
    private Agendamento agendamento;

    @DeleteMapping("/desfazer")
    public ResponseEntity desfazerPost(){
        if (!pilha.isEmpty()){
            repository.deleteById(pilha.pop().getId());
            System.out.println("Desfeito");
        } else {
            System.out.println("Não tem nenhum POST para desfazer");
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity getProdutos(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity postProdutos(@RequestBody @Valid Mercado novoProduto){
        pilha.push(novoProduto);
        repository.save(novoProduto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/adicionar")
    public ResponseEntity novoProduto(@RequestBody @Valid Mercado novoProduto){
        String protocoloGerado = UUID.randomUUID().toString();
        LocalDateTime previsao = LocalDateTime.now().plusSeconds(16);

        mercado.setProtocolo(protocoloGerado);
        agendamento.fila.insert(novoProduto);

        return ResponseEntity
                .status(202)
                .header("protocolo", protocoloGerado)
                .header("previsao", previsao.toString())
                .build();

    }


    @GetMapping("/{protocolo}")
    public ResponseEntity getProduto (@PathVariable String protocolo){
        Optional<Mercado> protocoloOptional = repository.findByProtocolo(protocolo);

        if(protocoloOptional.isPresent()){
            return ResponseEntity.status(200).body
                    (protocoloOptional.get().getProduto());
        } else {
            LocalDateTime previsao = LocalDateTime.now().plusSeconds(5);
            return ResponseEntity.status(404).header("previsao", previsao.toString()).build();
        }


    }


    @PostMapping("/anexo")
    public ResponseEntity criarAnexo(@RequestParam MultipartFile arquivo) throws IOException {

        byte[] conteudo = arquivo.getBytes();

        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        mercadoGrava.leExibeArquivo(true);

        return ResponseEntity.status(201).build();

    }


}
