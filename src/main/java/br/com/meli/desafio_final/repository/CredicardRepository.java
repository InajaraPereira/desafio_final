package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.model.entity.Credicard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredicardRepository extends JpaRepository<Credicard, Long> {
}
