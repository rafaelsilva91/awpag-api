package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exceptions.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.IClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final IClienteRepository repository;

    public ClienteService(IClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = this.repository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente) )
                .isPresent();
        if (emailEmUso){
            throw new NegocioException("Já Existe um cliente cadastrado com esse e-mail!");
        }
        return this.repository.save(cliente);
    }

    @Transactional
    public List<Cliente> listar(){
        return this.repository.findAll();
    }

    @Transactional
    public Cliente buscar(Long id){
        Optional<Cliente> cliente = this.repository.findById(id);
        return cliente.orElseThrow(() -> new NegocioException("Cliente não Encontrado"));

    }

    @Transactional
    public Cliente atualizar(Cliente cliente, Long id){
        if(!existe(id)){
            return null;
        }

        cliente.setId(id);
        return salvar(cliente);
    }

    @Transactional
    public void delete(Long id){
        if (existe(id)){
            this.repository.deleteById(id);
        }
    }

    @Transactional
    private boolean existe(Long id){
        return this.repository.existsById(id);
    }

}
