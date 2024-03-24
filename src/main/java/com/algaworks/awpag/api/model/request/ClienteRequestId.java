package com.algaworks.awpag.api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestId {

    @NotNull
    private Long id;
}
