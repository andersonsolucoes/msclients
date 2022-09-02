package org.ufc.example.msclients.application;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ufc.example.msclients.application.representation.ClienteSaveRequest;
import org.ufc.example.msclients.domain.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService services;

    @GetMapping("status")
    public String status() {
        return "Ok";
    }

    @PostMapping("save")
    public ResponseEntity<Cliente> save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        services.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("cpf={cpf}")
            .buildAndExpand(cliente.getCpf())
            .toUri();
        
            return ResponseEntity.created(headerLocation).build();

    }

    @GetMapping("dadosCliente")
    public ResponseEntity<Optional<Cliente>> dadosCliente(@RequestParam String cpf) {
        Optional<Cliente> cliente = services.getByCpf(cpf);
        if(cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cliente);
        }
    }

    
}
