package com.algaworks.awpag.api.controllers;

import com.algaworks.awpag.api.mapper.ParcelamentoMapper;
import com.algaworks.awpag.api.model.ParcelamentoModel;
import com.algaworks.awpag.api.model.request.ParcelamentoRequest;
import com.algaworks.awpag.domain.entities.Parcelamento;
import com.algaworks.awpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoService service;
    private final ParcelamentoMapper parcelamentoMapper;


    public ParcelamentoController(ParcelamentoService service, ParcelamentoMapper parcelamentoMapper) {
        this.service = service;
        this.parcelamentoMapper = parcelamentoMapper;
    }

    @GetMapping
    public List<ParcelamentoModel> listar() {
        List<Parcelamento> parcelamentos = this.service.listar();
        return parcelamentoMapper.toCollectionModel(parcelamentos);

    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
        Optional<ParcelamentoModel> parcelamentoModel = Optional.ofNullable(this.service.buscar(parcelamentoId))
                .map(parcelamentoMapper::toModel);

        if (!parcelamentoModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(parcelamentoModel.get());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoRequest request) {
        Parcelamento parcelamento = parcelamentoMapper.toEntity(request);
        Parcelamento parcelamentoCadastrado = this.service.salvar(parcelamento);
        return parcelamentoMapper.toModel(parcelamentoCadastrado);
    }

    @PutMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> atualizar(@PathVariable Long parcelamentoId, @RequestBody Parcelamento parcelamento) {
        Parcelamento p = this.service.atualizar(parcelamento, parcelamentoId);
        return ResponseEntity.ok().body(parcelamentoMapper.toModel(p));
    }

    @DeleteMapping("/{parcelamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long parcelamentoId) {
        this.service.delete(parcelamentoId);
        return ResponseEntity.noContent().build();
    }


}
