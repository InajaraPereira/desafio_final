package br.com.meli.desafio_final.model.repository;

import br.com.meli.desafio_final.model.entity.InBoundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderRepository extends JpaRepository<InBoundOrder, Long> {
}
