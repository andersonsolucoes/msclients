package org.ufc.example.msclients.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ufc.example.msclients.domain.Cliente;
import org.ufc.example.msclients.infra.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
    
}
