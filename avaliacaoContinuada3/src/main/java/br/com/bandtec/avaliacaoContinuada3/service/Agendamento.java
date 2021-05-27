package br.com.bandtec.avaliacaoContinuada3.service;

import br.com.bandtec.avaliacaoContinuada3.FilaObj;
import br.com.bandtec.avaliacaoContinuada3.dominio.Mercado;
import br.com.bandtec.avaliacaoContinuada3.repository.MercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Agendamento {

    public FilaObj<Mercado> fila = new FilaObj<>(10);

    @Autowired
    private MercadoRepository repository;

    @Scheduled(fixedDelay = 12000)
    public void agendar(){
        if(!fila.isEmpty()){
            repository.save(fila.poll());
        } else {
            System.out.println("Nenhuma requisição");
        }

    }
}
