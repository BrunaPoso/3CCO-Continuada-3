package br.com.bandtec.avaliacaoContinuada3.repository;

import br.com.bandtec.avaliacaoContinuada3.dominio.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MercadoRepository extends JpaRepository<Mercado, Integer> {

    @Query("from Mercado where protocolo like ?1")
    Optional<Mercado> findByProtocolo(String numeroProtocolo);

}
