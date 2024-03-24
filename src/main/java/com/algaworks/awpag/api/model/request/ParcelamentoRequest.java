package com.algaworks.awpag.api.model.request;

import com.algaworks.awpag.api.model.ClienteModel;
import com.algaworks.awpag.domain.validation.IValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ParcelamentoRequest {

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Max(value = 12)
    private Integer quantidadeParcelas;

    @Valid
    @NotNull
    private ClienteRequestId cliente;

}
