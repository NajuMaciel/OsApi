/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.naju.eti.OSApiApplication.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author sesi3dia
 */
@Entity
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "ordemServico_id")
    @JsonIgnore
    private OrdemServico ordemServico;
    
    @Size(max = 200)
    private String descricao;
    
    private LocalDateTime dataEnvio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
 
    public Comentario (long id, String descricao, LocalDateTime dataEnvio) {
        this.id = id;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id>>> 32));
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + Objects.hashCode(this.dataEnvio);
        return hash;
    }
    
    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Comentario other = (Comentario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return Objects.equals(this.dataEnvio, other.dataEnvio);
    }
    
    
    //public OrdemServico getOrdemServico() {
    //   return ordemServico;}
    
    //public void setOrdemServico (OrdemServico ordemServico) {
    //    this.ordemServico = ordemServico;}
    
    //COMENTARIOS NAO FINALIZADOS
}
