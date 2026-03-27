package br.naju.eti.OSApiApplication.domain.service;

import br.naju.eti.OSApiApplication.domain.ClienteRepository;
import br.naju.eti.OSApiApplication.domain.exception.DomainException;
import br.naju.eti.OSApiApplication.domain.model.OrdemServico;
import br.naju.eti.OSApiApplication.domain.model.StatusOrdemServico;
import br.naju.eti.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IMPLEMENTA A ATUALIZAÇÃO DE STATUS DA ORDEM E SERVICO VERIFICA SE OS EXISTE
 * ANTES DE SALVAR
 *
 * @param ordemServicoID
 * @param status
 * @return Optional<OrdemServico> or throw if not found
 */
@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void excluir(Long ordemServicoId) {
        ordemServicoRepository.deleteById(ordemServicoId);
    }

    public OrdemServico salvar(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> findTodasOrdemServico() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {

        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

        if (optOrdemServico.isPresent()) {
            OrdemServico ordemServico = optOrdemServico.get();

            //verifica se a ordem esta ABERTA
            if (ordemServico.getStatus() == StatusOrdemServico.ABERTA
                    && status != StatusOrdemServico.ABERTA) {

                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);

            } else {
                //ordem finalizada - não alterar
                return Optional.empty();
            }
        } else {
            //lança exception se o ID nao for encontrado
            throw new DomainException("Não existe OS cm o id" + ordemServicoID);
        }
    }
}
