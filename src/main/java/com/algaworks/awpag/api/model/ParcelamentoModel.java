package com.algaworks.awpag.api.model;

import com.algaworks.awpag.domain.entities.Cliente;
import com.algaworks.awpag.domain.validation.IValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ParcelamentoModel {

    private Long id;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;
    private LocalDateTime data_criacao = LocalDateTime.now();
    private String nomeCliente;

}
