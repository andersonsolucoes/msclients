package org.ufc.example.msclients.infra.repository;

import org.springframework.stereotype.Repository;
import org.ufc.example.msclients.domain.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByCpf(String cpf);
    
}
