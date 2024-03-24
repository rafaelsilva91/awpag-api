package com.algaworks.awpag.domain.model;

import com.algaworks.awpag.domain.validation.IValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_cliente")
public class Cliente {

    @NotNull(groups = IValidationGroups.ClienteId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo NOME está vazio")
    @Size(max = 60, min = 3, message = "campo nome é máximo 60 caracteres")
    private String nome;

    @NotBlank(message = "O campo Email está vazio")
    @Size(max = 255,message = "campo Email é máximo 255 caracteres")
    @Email
    private String email;

    @NotBlank(message = "O campo Telefone está vazio")
    @Size(max = 20,message = "campo telefone é máximo 20 caracteres")
    @Column(name = "fone")
    private String telefone;

}
