package com.example.demo.controllers;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class StockController {

    private List<Produto> inventario = new ArrayList<>();
    private Queue<Produto> filaExpedicao = new LinkedList<>();
    private Stack<Produto> pilhaDevolucoes = new Stack<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produtos", inventario);
        model.addAttribute("fila", filaExpedicao);
        model.addAttribute("pilha", pilhaDevolucoes);
        return "armazem";
    }

    // LISTA: Adicionar produto com quantidade
    @PostMapping("/lista/adicionar")
    public String adicionarLista(@RequestParam String nome, @RequestParam int qtd) {
        inventario.add(new Produto(nome, qtd));
        return "redirect:/";
    }

    // LISTA: Nova Feature - Remover unidade por unidade
    @PostMapping("/lista/remover")
    public String removerUnidade(@RequestParam int index) {
        if (index >= 0 && index < inventario.size()) {
            Produto p = inventario.get(index);
            if (p.getQuantidade() > 1) {
                p.reduzirQuantidade(); // Apenas diminui 1
            } else {
                inventario.remove(index); // Se for o último, remove o item da lista
            }
        }
        return "redirect:/";
    }

    // FILA: Entrada e Saída (FIFO)
    @PostMapping("/fila/entrar")
    public String entrarFila(@RequestParam String nome) {
        filaExpedicao.add(new Produto(nome, 1));
        return "redirect:/";
    }

    @PostMapping("/fila/despachar")
    public String despachar() {
        filaExpedicao.poll(); 
        return "redirect:/";
    }

    // PILHA: Entrada e Saída (LIFO)
    @PostMapping("/pilha/empilhar")
    public String empilhar(@RequestParam String nome) {
        pilhaDevolucoes.push(new Produto(nome, 1));
        return "redirect:/";
    }

    @PostMapping("/pilha/processar")
    public String processar() {
        if (!pilhaDevolucoes.isEmpty()) pilhaDevolucoes.pop();
        return "redirect:/";
    }
}