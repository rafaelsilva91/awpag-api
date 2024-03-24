package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar(){
        return this.service.listar();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = Optional.ofNullable(this.service.buscar(clienteId));

        if(!cliente.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(cliente.get());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente cadastrar(@Valid @RequestBody Cliente cliente){
        return this.service.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        Cliente c = this.service.atualizar(cliente, clienteId);
        return ResponseEntity.ok().body(c);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
        this.service.delete(clienteId);
        return ResponseEntity.noContent().build();
    }

}
