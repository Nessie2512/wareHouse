package com.example.demo.controllers;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StockController {

    // LISTA: Inventário Geral (CRUD Total)
    private List<Produto> inventario = new ArrayList<>();

    // FILA: Produtos para Expedição (FIFO - Primeiro a entrar, primeiro a sair)
    private Queue<Produto> filaExpedicao = new LinkedList<>();

    // PILHA: Devoluções para Inspeção (LIFO - Último a entrar, primeiro a sair)
    private Stack<Produto> pilhaDevolucoes = new Stack<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produtos", inventario);
        model.addAttribute("fila", filaExpedicao);
        model.addAttribute("pilha", pilhaDevolucoes);
        // Espiar quem são os próximos sem remover (Método PEEK)
        model.addAttribute("proximoFila", filaExpedicao.peek());
        model.addAttribute("topoPilha", pilhaDevolucoes.isEmpty() ? null : pilhaDevolucoes.peek());
        return "armazem";
    }

    // --- MÉTODOS DA LISTA (Inventário) ---
    @PostMapping("/lista/adicionar")
    public String adicionarLista(@RequestParam String nome, @RequestParam int qtd) {
        Produto p = new Produto(nome, qtd);
        inventario.add(p); // List.add()
        return "redirect:/";
    }

    @PostMapping("/lista/remover")
    public String removerLista(@RequestParam int index) {
        if (index >= 0 && index < inventario.size()) {
            inventario.remove(index); // List.remove(index)
        }
        return "redirect:/";
    }

    // --- MÉTODOS DA FILA (Expedição) ---
    @PostMapping("/fila/entrar")
    public String entrarFila(@RequestParam String nome) {
        filaExpedicao.add(new Produto(nome, 1)); // Queue.add()
        return "redirect:/";
    }

    @PostMapping("/fila/despachar")
    public String despachar() {
        filaExpedicao.poll(); // Queue.poll() -> Remove o primeiro da fila
        return "redirect:/";
    }

    // --- MÉTODOS DA PILHA (Devoluções) ---
    @PostMapping("/pilha/empilhar")
    public String empilhar(@RequestParam String nome) {
        pilhaDevolucoes.push(new Produto(nome, 1)); // Stack.push() -> Coloca no topo
        return "redirect:/";
    }

    @PostMapping("/pilha/processar")
    public String processarDevolucao() {
        if (!pilhaDevolucoes.isEmpty()) {
            pilhaDevolucoes.pop(); // Stack.pop() -> Remove o do topo
        }
        return "redirect:/";
    }
}