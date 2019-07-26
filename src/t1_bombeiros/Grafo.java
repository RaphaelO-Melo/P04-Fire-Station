//Classe Grafo: cria o grafo com as informações obtidas no arquivo de leitura:
package t1_bombeiros;


import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private final int vertices;
    private final boolean vVisitados[];
    private final List<Integer> listaCaminho;
    private final int[][] adj;
    private int numCaminhos;

    //Consturor
    public Grafo(){
        this.vertices = 21;
        this.adj = new int[vertices][vertices];
        this.listaCaminho = new ArrayList<>();
        this.vVisitados = new boolean[vertices];
    }
    
    /*Método que insere as arestas do Grafo: antes de inserir confere a validade
     *da aresta e caso não se encaixe nos padrões permitidos, informa ao cliente
     *sobre a divergência e não inclui a mesma no grafo:
     *
     *Retornos:
     *0 = Fim do arquivo;
     *1 = Aresta inserida com sucesso;
     */
    public int InsereAresta( int vertA, int vertB, int numLinha){
        //Se 00, o arquivo acabou, então chama o método para procurar ciclos 
        if (vertA == 0 && vertB == 0){
            BuscaCiclos(1);
            return 0;
        }
        if (vertA == vertB) { throw new IllegalArgumentException
        ("Erro de leitura na linha "+numLinha+": Par (x: "+vertA+", y: "+vertB+
         ") X deve ser diferente de Y!");
        }
        if (this.adj[vertA][vertB] == 1) { throw new IllegalArgumentException
        ("Erro de leitura na linha "+numLinha+": Par (x: "+vertA+", y: "+vertB+
         ") Caminho já inserido!");
        }
        //Se a aresta está dentro do padrão, insere a mesma:
        this.adj[vertA][vertB]=1;
        return 1;
    }
    
    /*Método que remove aresta, nessa aplicação só é utilizado quando a aresta
     *forma um ciclo. A aresta é removida e o usuário é informado:
     */
    private void RemoveAresta(int vertA, int vertB) {
        this.adj[vertA][vertB] = 0;
        System.out.println("O par (X:"+vertA+" Y:"+vertB+") foi removido por"
                + " formar ciclo");
    }

    /*Método que realiza a busca em profundidade a partir do vértice 1(quartel)
     *e utiliza do vetor de visitados para identidficar o ciclo. Caso o vértice
     *adjacente atual já tenha sido visitado, o ciclo é identificado e a aresta
     *é removida com o auxílio da RemoveAresta:    
     */
    private void BuscaCiclos(int vertice) {
        //Vertice atual marcado como visitado
        vVisitados[vertice] = true;
        //Para cada vertice adjacente do atual realiza a recursividade:
        for (int w = 0; w < vertices; w++) {
            if (this.adj[vertice][w] == 1) {
                //Caso o vertice adjacente foi visitado, identificou ciclo:
                if (vVisitados[w]) {
                    RemoveAresta(vertice, w);
                }
                if (!vVisitados[w]) {
                    BuscaCiclos(w);
                    //Caso não tenham mais adjacentes, remove de visitados:
                    vVisitados[w] = false;
                }
            }
        }
    }

    /*Método que mostra os adjacente, disponível para o caso do cliente desejar
     *conferir os adjacentes de cada um dos 20 vertices possíveis:    
     */
    public void MostraAdj() {
        for (int v = 0; v < this.vertices; v++) {
            System.out.print(v + ":");
            for (int w = 0; w < this.vertices; w++) {
                if (this.adj[v][w] == 1) {
                    System.out.print(" " + w);
                }
            }
            System.out.println();
        }
    }
    
    /*Método que faz a busca em profundidade pelos vertices para achar o caminho
     *de "start" a "end", para isso é auxiliado por uma lista que recebe os
     *vertices por onde a busca está passando:    
     */
    public void BuscaProf(int start, int end) {
        //Se start for igual a end, significa que chegou ao caminho
        if (start == end) {
            listaCaminho.add(start);
            System.out.println(listaCaminho);
            numCaminhos = numCaminhos + 1;
        }
        if (start != end) {
            listaCaminho.add(start);
            for (int x = 0; x < vertices; x++) {
                if (adj[start][x] == 1) {
                    BuscaProf(x, end);
                    listaCaminho.remove(listaCaminho.size() - 1);
                }
            }
        }
    }
    
    // Método que retorna número de caminhos para o cliente:
    public int getNumCaminhos() {
        return numCaminhos;
    }
}
