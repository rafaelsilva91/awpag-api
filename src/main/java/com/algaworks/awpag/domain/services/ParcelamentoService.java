package com.algaworks.awpag.domain.services;

import com.algaworks.awpag.domain.exceptions.NegocioException;
import com.algaworks.awpag.domain.entities.Cliente;
import com.algaworks.awpag.domain.entities.Parcelamento;
import com.algaworks.awpag.domain.repositories.IParcelamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelamentoService {

    private final IParcelamentoRepository repository;
    private final ClienteService clienteService;

    public ParcelamentoService(IParcelamentoRepository repository, ClienteService clienteService) {
        this.repository = repository;
        this.clienteService = clienteService;
    }

    @Transactional
    public Parcelamento salvar(Parcelamento parcelamento){
        Parcelamento p = new Parcelamento();
        if(parcelamento.getId() != null){
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }

        Cliente cliente = this.clienteService.buscar(parcelamento.getCliente().getId());
        parcelamento.setCliente(cliente);
        return this.repository.save(parcelamento);
    }

    @Transactional
    public List<Parcelamento> listar(){
        return this.repository.findAll();
    }

    @Transactional
    public Parcelamento buscar(Long id){
        Optional<Parcelamento> parcelamento = this.repository.findById(id);
        return parcelamento.orElse(null);

    }

    @Transactional
    public Parcelamento atualizar(Parcelamento parcelamento, Long id){
        if(!existe(id)){
            return null;
        }

        parcelamento.setId(id);
        return repository.save(parcelamento);
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
