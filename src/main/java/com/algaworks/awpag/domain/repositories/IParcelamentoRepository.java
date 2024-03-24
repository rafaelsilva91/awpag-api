package com.algaworks.awpag.domain.repositories;

import com.algaworks.awpag.domain.entities.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParcelamentoRepository extends JpaRepository<Parcelamento, Long> {

}
