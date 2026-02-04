/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.naju.eti.OSApiApplication.api.controller;

import br.naju.eti.OSApiApplication.domain.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */

@RestController
public class ClienteController {
    
    List<Cliente> listaClientes;
    
    @GetMapping ("/clientes")
    public List<Cliente>  listas() {
        
        listaClientes = new ArrayList<Cliente>();
        listaClientes.add (new Cliente (1, "Kge", "kge.teste.com", "11-99999-9999"));
        listaClientes.add (new Cliente (2, "Naju", "naju.teste.com", "12-44444-4444"));
        listaClientes.add (new Cliente (2, "Naju", "naju.teste.com", "12-44444-4444"));
        
        return listaClientes;
    }
    
}
