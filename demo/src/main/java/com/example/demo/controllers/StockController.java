package com.example.demo.controllers;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class StockController {

    // 1. LISTA: Inventário completo do armazém
    private List<Produto> inventario = new ArrayList<>();

    // 2. FILA: Ordem de saída para entrega (FIFO)
    private Queue<Produto> filaExpedicao = new LinkedList<>();

    // 3. PILHA: Produtos devolvidos aguardando inspeção (LIFO)
    private Stack<Produto> pilhaDevolucoes = new Stack<>();

    @GetMapping("/")
    public String gerirArmazem(Model model) {
        model.addAttribute("produtos", inventario);
        model.addAttribute("expedicao", filaExpedicao);
        model.addAttribute("devolucoes", pilhaDevolucoes);
        return "armazem";
    }

    // Adicionar ao Inventário e colocar na Fila de Saída
    @PostMapping("/receber")
    public String receberProduto(@RequestParam String nome, @RequestParam int quantidade) {
        Produto p = new Produto(nome, quantidade);
        inventario.add(p);
        filaExpedicao.add(p);
        return "redirect:/";
    }

    // Processar Saída (Remove da Fila - o primeiro que entrou sai agora)
    @PostMapping("/despachar")
    public String despachar() {
        if (!filaExpedicao.isEmpty()) {
            filaExpedicao.poll(); 
        }
        return "redirect:/";
    }

    // Simular Devolução (Adiciona na Pilha - fica no topo)
    @PostMapping("/devolver")
    public String devolver(@RequestParam String nome) {
        pilhaDevolucoes.push(new Produto(nome, 1));
        return "redirect:/";
    }

    // Processar Devolução (Remove da Pilha - tira o que está no topo)
    @PostMapping("/inspeccionar")
    public String inspeccionar() {
        if (!pilhaDevolucoes.isEmpty()) {
            pilhaDevolucoes.pop();
        }
        return "redirect:/";
    }
}