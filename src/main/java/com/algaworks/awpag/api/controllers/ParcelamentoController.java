package com.algaworks.awpag.api.controllers;

import com.algaworks.awpag.api.model.ParcelamentoModel;
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

    public ParcelamentoController(ParcelamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Parcelamento> listar(){
        return this.service.listar();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId){
        Optional<ParcelamentoModel> parcModel = Optional.ofNullable(this.service.buscar(parcelamentoId))
                .map(parcelamento -> {
                    var parcelamentoModel = new ParcelamentoModel();
                    parcelamentoModel.setId(parcelamento.getId());
                    parcelamentoModel.setDescricao(parcelamento.getDescricao());
                    parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
                    parcelamentoModel.setParcelas((int) parcelamento.getQuantidadeParcelas());
                    parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
                    return parcelamentoModel;
                });

        if(!parcModel.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(parcModel.get());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento){
        return this.service.salvar(parcelamento);
    }

    @PutMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> atualizar(@PathVariable Long parcelamentoId, @RequestBody Parcelamento parcelamento){
        Parcelamento p = this.service.atualizar(parcelamento, parcelamentoId);
        return ResponseEntity.ok().body(p);
    }

    @DeleteMapping("/{parcelamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long parcelamentoId){
        this.service.delete(parcelamentoId);
        return ResponseEntity.noContent().build();
    }


}
