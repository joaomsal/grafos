package algoritmos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOÃO MANOEL SANTOS ALMEIDA & RIVANILDO JÚNIOR DOS SANTOS ANDRADE
 * @matricula 201600016896 & 201600092477
 */
public class Coloracao {

    public List<Integer> getAdjacentes(int linha, int grafo[][]) {
        List<Integer> adj = new ArrayList();
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[linha][i] == 1) {
                adj.add(i);
            }
        }
        return adj;
    }

    public void inicializa(boolean coloridos[]) {
        for (int i = 0; i < coloridos.length; i++) {
            coloridos[i] = false;
        }
    }

    public char getCor(int v, char[] vertices) {
        return vertices[v];
    }
    /*
    ALGORITMO DE COLORAÇÃO DE VÉRTICES
    O MÉTODO ABAIXO RECEBE UMA MATRIZ DE ADJACÊNCIA DE UM GRAFO QUALQUER
    E APLICA A COLORAÇÃO SEGUINDO O SEGUINTE CRITÉRIO:
    UM VÉRTICE NÃO PODE TER A MESMA COR QUE SEUS ADJACENTES;
    AS CORES ESTÃO REPRESENTADAS POR CARACTERES : 
    CORES = {a,b,c,...,g}
    */
    public char[] colorir(int grafo[][]) {
        char[] resultado = new char[grafo.length];
        char[] cores = {'a', 'b', 'c', 'd', 'e','f','g'};
        boolean[] coloridos = new boolean[grafo.length];
        int aux=0;
        for (int i = 0; i < grafo.length; i++) {
            aux=0;
            if (!coloridos[i]) {
                List<Integer> adj = getAdjacentes(i, grafo);
                for (int j = 0; j < adj.size(); j++) {
                    while (cores[aux] == getCor(adj.get(j), resultado)) {
                        aux++;
                    }
                    resultado[i] = cores[aux];
                }
            }
            coloridos[i] = true;
        }
        return resultado;

    }
    /*
    MÉTODO PRINCIPAL 
        ABAIXO ESTÁ A APLICAÇÃO DO ALGORITMO DE COLORAÇÃO DE VÉRTICES GRAFOS:
    PARA FACILITAR NA CORREÇÃO, EXISTEM ALGUNS GRAFOS PRONTOS PARA O TESTE DE PLANARIDADE, 
    ALGUNS ESTÃO EM ARQUIVOS COM OS NOMES:
        K4.txt, K5.txt, K6.txt, K33.txt, k44.txt E pentagono.txt
    OUTROS GRAFOS PARA TESTAR A PLANARIDADE ESTÃO INSTANCIADOS ABAIXO;
        PARA ESCOLHER UM DOS GRAFOS JÁ INSTANCIADOS, BASTA ATRIBUIR A MATRIZ aux[][] O NOME DO GRAFO ESCOLHIDO,
    NO CASO E ESCOLHER UM DOS GRAFOS PRESENTES NOS ARQUIVOS, BASTA PASSAR O ENDEREÇO ARQUIVO .txt E O N° DE VÉRTICES,
    PARA A MATRIZ matriz[][].
    */
    public static void main(String args[]) {
        Coloracao c = new Coloracao();
        int k33[][] = {
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 0}};

        int k5[][] = { 
            {0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0}};

        int k[][] = { 
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0}};

        int k4[][] = { 
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}};
        
        
        LeMatrizArquivo l = new LeMatrizArquivo();
        // passando a matriz do arquivo para o programa
        // os paramentros são: endereço do arquivo e número de vértices
        int matriz[][] = l.leArquivoEImprimeInstancias("pentagono.txt", 5);
        int aux[][] = matriz;
        char[] rs = c.colorir(aux);
        System.out.println("--------RESULTADOS--------");
        for (int i = 0; i < rs.length; i++) {
            System.out.println("COR DO VÉRTICE "+i +" ->"+rs[i]);
        }
        System.out.println("--------------------------");

    }
}
