package com.example.demo.controller;

import com.example.demo.model.Chamado;
import com.example.demo.model.Prioridade;
import com.example.demo.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private List<Chamado> chamados = new ArrayList<>();

    public ChamadoController() {
        chamados.add(new Chamado(UUID.randomUUID().toString(), "PC não liga", "João", "TI", Prioridade.ALTA, Status.ABERTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Impressora sem tinta", "Maria", "Financeiro", Prioridade.MEDIA, Status.EM_ANDAMENTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Sem acesso ao email", "Carlos", "RH", Prioridade.ALTA, Status.RESOLVIDO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Teclado quebrado", "Ana", "Marketing", Prioridade.BAIXA, Status.ABERTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Lentidão na internet", "Pedro", "TI", Prioridade.MEDIA, Status.EM_ANDAMENTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Instalar Photoshop", "Lucas", "Marketing", Prioridade.BAIXA, Status.RESOLVIDO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Monitor piscando", "Fernanda", "Financeiro", Prioridade.MEDIA, Status.ABERTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Esqueci a senha do sistema", "Juliana", "Vendas", Prioridade.ALTA, Status.RESOLVIDO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Mouse não funciona", "Roberto", "Vendas", Prioridade.BAIXA, Status.ABERTO));
        chamados.add(new Chamado(UUID.randomUUID().toString(), "Sistema financeiro fora do ar", "Camila", "Financeiro", Prioridade.ALTA, Status.EM_ANDAMENTO));
    }

    @GetMapping
    public List<Chamado> listarTodos() {
        return chamados;
    }

    @GetMapping("/{id}")
    public Chamado buscarPorId(@PathVariable String id) {
        return chamados.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/setor")
    public List<Chamado> buscarPorSetor(@RequestParam String nome) {
        return chamados.stream().filter(c -> c.getSetor().equalsIgnoreCase(nome)).toList();
    }

    @GetMapping("/prioridade")
    public List<Chamado> buscarPorPrioridade(@RequestParam String nivel) {
        return chamados.stream().filter(c -> c.getPrioridade().name().equalsIgnoreCase(nivel)).toList();
    }

    @GetMapping("/busca")
    public List<Chamado> buscarPorTitulo(@RequestParam String texto) {
        return chamados.stream().filter(c -> c.getTitulo().toLowerCase().contains(texto.toLowerCase())).toList();
    }

    @GetMapping("/criticos")
    public List<Chamado> buscarCriticos() {
        return chamados.stream().filter(c -> c.getPrioridade() == Prioridade.ALTA && c.getStatus() != Status.RESOLVIDO).toList();
    }

    @GetMapping("/resumo")
    public Map<String, Long> resumoChamados() {
        Map<String, Long> resumo = chamados.stream().collect(Collectors.groupingBy(c -> c.getStatus().name(), Collectors.counting()));
        resumo.put("TOTAL", (long) chamados.size());
        return resumo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamado cadastrar(@RequestBody Chamado novoChamado) {
        novoChamado.setId(UUID.randomUUID().toString());
        chamados.add(novoChamado);
        return novoChamado;
    }
}