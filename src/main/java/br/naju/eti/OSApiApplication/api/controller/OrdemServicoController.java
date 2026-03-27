/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.naju.eti.OSApiApplication.api.controller;

import br.naju.eti.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.naju.eti.OSApiApplication.domain.model.OrdemServico;
import br.naju.eti.OSApiApplication.domain.model.StatusOrdemServico;
import br.naju.eti.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.naju.eti.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar (@RequestBody OrdemServico ordemServico) {
        
        return ordemServicoService.criar(ordemServico);
    }
    
    @DeleteMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<Void> excluir (@PathVariable Long ordemServicoID) {
        
        if (!ordemServicoRepository.existsById (ordemServicoID)) {
            return ResponseEntity.notFound().build();
        }
    
        ordemServicoService.excluir(ordemServicoID);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> buscar (@PathVariable Long ordemServicoID) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);
        
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoID, @RequestBody OrdemServico ordemServico) {
        
        if (!ordemServicoRepository.existsById(ordemServicoID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(ordemServicoID);
        ordemServico = ordemServicoService.salvar(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    @GetMapping("/ordem-servico")
    public ResponseEntity<List<OrdemServico>> buscar () {
        
        List<OrdemServico> ordemServico = ordemServicoService.findTodasOrdemServico();
        
        if (!ordemServico.isEmpty()) {
            return ResponseEntity.ok(ordemServico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**Implementa endpoint para alteração de status
     * @param ordemServicoID identificação da OS
     * @param statusDTO - status a ser atribuido
     * @return 200 ok, 204 ou throw exception
     */
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizarstatus(
    @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {
        
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
            ordemServicoID,
            statusDTO.status());
        
        if (optOS.isPresent()){
            return ResponseEntity.ok(optOS.get());
            
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
}                                             