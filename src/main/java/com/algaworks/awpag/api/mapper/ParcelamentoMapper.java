package com.algaworks.awpag.api.mapper;

import com.algaworks.awpag.api.model.ParcelamentoModel;
import com.algaworks.awpag.api.model.request.ParcelamentoRequest;
import com.algaworks.awpag.domain.entities.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoMapper {

    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento){
        return modelMapper.map(parcelamento, ParcelamentoModel.class);
    }

    public List<ParcelamentoModel> toCollectionModel(List<Parcelamento> parcelamentos){
        return parcelamentos.stream()
                .map(this::toModel)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoRequest parcelamentoRequest){
        return modelMapper.map(parcelamentoRequest, Parcelamento.class);
    }

}
