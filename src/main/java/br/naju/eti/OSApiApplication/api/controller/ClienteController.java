package br.naju.eti.OSApiApplication.api.controller;

import br.naju.eti.OSApiApplication.domain.model.Cliente;
import br.naju.eti.OSApiApplication.domain.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/clientes")
    public List<Cliente> listas(){
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{ClienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long ClienteID){
        
        Optional<Cliente> cliente = clienteRepository.findById(ClienteID);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping ("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar (@RequestBody Cliente cliente) {
        
        return clienteRepository.save(cliente);
    }
    
    @PutMapping ("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteID, @RequestBody Cliente cliente) {
        
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
}
