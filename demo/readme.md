📦 Gestão de Armazém - Projeto AED📋

 Sobre o ProjetoEste projeto simula a lógica de operações logísticas num armazém utilizando as três estruturas fundamentais de AED: Pilhas, Filas e Listas. O objetivo é otimizar o fluxo de mercadorias desde a chegada até à expedição.

 🛠️ Estruturas de Dados AplicadasClique nas secções abaixo para ver como cada estrutura é utilizada no armazém:
 
 📚 Pilha (Stack) - LIFO (Last-In, First-Out)Utilizada para o Empilhamento de Contentores. No armazém, o último contentor a ser colocado no topo é o primeiro a ser retirado para evitar movimentações desnecessárias de carga pesada.Operação: push() para adicionar carga; pop() para retirar.

 🚚 Fila (Queue) - FIFO (First-In, First-Out)
 Aplicada no Cais de Receção e Expedição. Os camiões que chegam primeiro ao armazém devem ser os primeiros a ser descarregados/carregados, garantindo a ordem de chegada.Operação: enqueue() para entrada na fila; dequeue() para saída.

 📑 Lista Ligada (Linked List)
  Organização Geral Usada para o Inventário de Produtos. Como o stock muda constantemente, a lista permite inserir e remover produtos em qualquer posição (corredor/prateleira) de forma dinâmica.Vantagem: Facilidade em adicionar novos itens sem precisar de reorganizar todo o espaço físico (memória).

  