package br.com.keeptest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.keeptest.domain.Agente;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {

}
