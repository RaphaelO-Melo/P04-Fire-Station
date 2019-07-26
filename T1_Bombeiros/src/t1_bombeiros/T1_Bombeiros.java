/**********************************************
 *                                            *
 *      Desenvolvido por @RaphaelMelo         *
 *      Centro Universitário SENAC - 2019     *
 *      Técnologia em Jogos Digitais III      *
 *                                            *
 **********************************************/
package t1_bombeiros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T1_Bombeiros {

    public static void main(String[] args) throws
            FileNotFoundException, IOException {
        int quartel = 1, objetivo;
        Grafo grafo;
        //Inicializando a variável de leitura e atribuindo a leitura buferizada:
        FileReader distritos;
        BufferedReader distBuff;
        distritos = new FileReader("arquivo.txt");
        distBuff = new BufferedReader(distritos);
        //Chamando a função que ira ler o arquivo e definir o objetivo:
        objetivo = defObjetivo(distBuff);
        //Chamando função que irá ler o resto do arquivo e criar o Grafo:
        grafo = leArquivo(distBuff);
        System.out.print("As rotas até o incêndio no distrito "+objetivo+" são: \n");
        System.out.print("__________________________________________\n");
        //Após conseguir ler o arquivo, inicializa a busca de todos os caminhos:
        grafo.BuscaProf(quartel, objetivo);
        System.out.println("\nExistem "+grafo.getNumCaminhos()+" caminho(s) até"
                + " o distrito em chamas.");
    }

    /*Função defObjetivo: Receberá o arquivo de leitura buferizado e irá ler 
     *a primeira linha, converter esse valor para um inteiro e o retornar:    
     */
    public static int defObjetivo(BufferedReader distritos) throws IOException {
        String distEmChamas;
        distEmChamas = distritos.readLine();
        int distConvertido = Integer.parseInt(distEmChamas);
        return distConvertido;
    }

    /*Função leArquivo: Similar à defObjetivo, a função recebe o arquivo de 
     *leitura buferizada e lê o resto do arquivo, porém enquanto lê cria as
     *arestas e checa as irreg ularidades no arquivo, no final, retorna o grafo:    
     */
    public static Grafo leArquivo(BufferedReader distritos) throws IOException {
        String line, vetorS[];
        Grafo grafo = new Grafo();
        for (int cont = 1, numLinha = cont+1; cont!= 0; numLinha++) {
            line = distritos.readLine();
            vetorS = line.split(" ");
            int x = Integer.parseInt(vetorS[0]);
            int y = Integer.parseInt(vetorS[1]);
            cont = grafo.InsereAresta(x, y, numLinha);
        }
        distritos.close();
        return grafo;
    }

}
