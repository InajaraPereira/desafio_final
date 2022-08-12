package br.com.meli.desafio_final.repository;

import br.com.meli.desafio_final.exception.entity.Section;
import br.com.meli.desafio_final.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCategory(Category category);
}