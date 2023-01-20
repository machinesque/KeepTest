package br.com.keeptest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.keeptest.domain.Regiao;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {

}
