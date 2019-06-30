package algoritmos;

/**
 *
 * @author JOÃO MANOEL SANTOS ALMEIDA & RIVANILDO JÚNIOR DOS SANTOS ANDRADE
 * @matricula 201600016896 & 201600092477
 */
public class TestedePlanaridade {

    private int cont;

    // aplicação da busca em profundidade
    public void DFS(int grafo[][], boolean marcados[], int n, int vert, int inicio) {
        marcados[vert] = true;

        if (n == 0) {
            marcados[vert] = false;

            if (grafo[vert][inicio] == 1) {
                cont++;
                return;
            } else {
                return;
            }

        }

        for (int i = 0; i < grafo.length; i++) {
            if (!marcados[i] && grafo[vert][i] == 1) {
                DFS(grafo, marcados, n - 1, i, inicio);
            }
        }

        marcados[vert] = false;

    }
    // contador de ciclos no grafo 
    // tCiclo - representa o tamanho do ciclo a ser verificado

    public int contaCiclos(int grafo[][], int tCiclo) {
        boolean marcados[] = new boolean[grafo.length + 1];

        for (int i = 0; i < grafo.length - (tCiclo - 1); i++) {
            DFS(grafo, marcados, tCiclo - 1, i, i);

            marcados[i] = true;
        }

        return cont / 2;
    }

    // percorre a matriz de adjacência para verificar as arestas presentes
    //no grafo e retornar esse número
    public int numArestas(int grafo[][]) {
        int m[][] = new int[grafo.length][grafo.length];
        int c = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = grafo[i][j];
            }
        }

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                    m[i][j] = 0;
                    m[j][i] = 0;
                    c++;
                }
            }
        }
        return c;
    }
    // verifica o numero de vértices em um dado grafo    

    public int numVertice(int grafo[][]) {
        int x = 0, z = 0;

        for (int i = 0; i < grafo.length; i++) {
            z = 0;
            for (int j = 0; j < grafo.length; j++) {
                z += grafo[i][j];
            }
            if (z != 0) {
                x++;
            }
        }
        return x;
    }

// verifica a planaridade de um dado grafo com base na fórmula de EULER
    public boolean verificaPlanaridade(int grafo[][]) {
        int arestas = numArestas(grafo);
        int vertices = grafo.length;
        // Aplicação da fórmula : V*3 -6
        int formula = (vertices * 3) - 6;
        // Verifica se   E <= V*3 -6
        if (arestas <= formula) {
            return true;
        } else {
            return false;
        }

    }
// removedor de grafos paralelos provenientes da aplicação do algoritmo de remoção de vértice

    public int[][] removeParalelo(int grafo[][]) {
        int aux[][] = grafo;

        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo.length; j++) {
                if (aux[i][j] == 2) {
                    aux[i][j] = 1;
                }
            }
        }
        return aux;
    }
// pega o grau de um determinado vértice

    public int getGrau(int linha, int[][] grafo) {
        int aux = 0;

        for (int i = 0; i < grafo.length; i++) {
            aux += grafo[linha][i];
        }

        return aux;
    }
// encontra vertices necessários para a aplicação da remoção

    public int[] encontraVertice(int linha, int[][] grafo) {
        int aux[][] = grafo;
        int a[] = new int[2];
        int ax = 0;
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[linha][i] == 1) {
                a[ax] = i;
                ax++;
            }
        }
        return a;
    }
// remove os vertices encontrados 

    public int[][] removeVertice(int linha, int[][] grafo) {
        int aux[][] = grafo;
        int vert[] = encontraVertice(linha, grafo);

        for (int i = 0; i < vert.length; i++) {
            aux[linha][vert[i]] = 0;
            aux[vert[i]][linha] = 0;
        }
        aux[vert[0]][vert[1]] += 1;
        aux[vert[1]][vert[0]] += 1;

        return aux;

    }
// aplicação do teorema de remoção de vértices para verificar se um dado grafo é planar

    public int[][] aplicaAlgoritmo(int grafo[][]) {
        int aux[][] = removeParalelo(grafo);
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo.length; j++) {
                aux = removeParalelo(aux);
                if (getGrau(i, aux) == 2) {
                    aux = removeVertice(i, aux);
                }
            }
        }
        return aux;
    }
// identifica se um dado grafo é do tipo K33

    public boolean verificaK33(int grafo[][]) {
        System.out.println(grafo.length + ">>>");
        for (int i = 0; i < grafo.length; i++) {
            if (getGrau(i, grafo) != 3 && getGrau(i, grafo) != 0) {
                return false;
            }
        }

        return true;
    }

    //identifica se um dado grafo é completo ou não    
    public boolean identificaCompletos(int grafo[][]) {

        for (int i = 0; i < grafo.length; i++) {
            if (getGrau(i, grafo) != grafo.length - 1) {
                return false;
            }
        }
        return true;
    }

    public boolean identificaBipartidos(int grafo[][]) {
        int c = 3;
        for (int i = 3; i < grafo.length; i++) {
            if (contaCiclos(grafo, c) != 0) {
                c++;
                return false;
            }
        }
        return true;
    }

    // Após aplicação do algoritmo de redução do grafo, devemos verificar se 
    // certas condições são válidas para um grafo ser planar
    // caso1: o grafo resultante é apenas uma aresta
    public boolean caso1(int grafo[][]) {
        if (numArestas(grafo) == 1) {
            return true;
        }
        return false;
    }

    //caso2: verifica se o grafo gerado é um grafo completo de 4 vértices  
    public boolean caso2(int grafo[][]) {
        if (numVertice(grafo) == 4 && identificaCompletos(grafo)) {
            return true;
        }
        return false;
    }

    /*
    MÉTODO PRINCIPAL
        ABAIXO ESTÁ A APLICAÇÃO DO ALGORITMO DE TESTE DE PLANARIDADE, PARA FACILITAR A CORREÇÃO
    EXISTEM ALGUNS GRAFOS PRONTOS PARA O TESTE DE PLANARIDADE, ALGUNS ESTÃO EM ARQUIVOS COM OS NOMES:
        K4.txt, K5.txt, K6.txt, K33.txt, k44.txt, exemplo.txt(GRAFO DADO NA QUESTÃO 1) E pentagono.txt
    OUTROS GRAFOS PARA TESTAR A PLANARIDADE ESTÃO INSTANCIADOS ABAIXO;
    O MÉTODO PARA PEGAR O GRAFO DE UM DETERMINADO ARQUIVO DE TEXTO, DEVE RECEBER O ENDEREÇO DO ARQUIVO E O N° DE VÉRTICES DO GRAFO.
    CASO SEJA ESCOLHIDO TESTAR UM DOS GRAFOS INSTANCIADOS ABAIXO, BASTA ATRIBUIR A MATRIZ aux 0 NOME DO GRAFO ESCOLHIDO. 
     */
    public static void main(String[] args) {

        TestedePlanaridade t = new TestedePlanaridade();

        int k6[][] = { //  é um grafo completo
            {0, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 0}};

        int k5[][] = { //  é um grafo completo
            {0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0}};

        int k[][] = { //  é um grafo simples (formato pentagono)
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0}};

        int k4[][] = { // é um grafo completo
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}};

        int k44[][] = { // é um grafo bipartido
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0}};

        int k33[][] = { //  é um grafo bipartido
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 0}};

        int kk[][] = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 0, 1},
            {0, 0, 1, 1, 0}};

        int g1[][] = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 0, 1},
            {0, 0, 1, 1, 0}};

        int k8[][] = { // grafo planar
            {0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0}};

        LeMatrizArquivo l = new LeMatrizArquivo();
        // PARA LER UM .txt DE UM GRAFO, BASTA PASSAR O ENDEREÇO E O Nº DE VÉRTICES 
        int matrizArquivo[][] = l.leArquivoEImprimeInstancias("exemplo.txt", 10);
        // aux -> RECEBE A MATRIZ LIDA DO ARQUIVO, CASO NÃO QUUEIRA TESTAR UM DOS GRAFOS INSTANCIADOS
        // BASTA ATRIBUIR A MATRIZ aux, O NOME NO GRAFO ESCOLHIDO
        int aux[][] = matrizArquivo;

        /*
        VERIFICAÇÃO DA PLANARIDADE:
        
        UTILIZANDO AS PROPRIEDADES DE PLANARIDADE EM UM DADO GRAFO G, REPRESENTADO POR UMA
        MATRIZ DE ADJACÊNCIA É FEITA A VERIFICAÇÃO DO TIPO DO GRAFO E DEPOIS É FEITA A VERIFICAÇÃO
        DE ALGUMAS PROPRIEDADES DE PLANARIDADE PARA AQUELE TIPO DE GRAFO:
        
            - PARA GRAFOS COMPLETOS: É FEITA A VERIFICAÇÃO DO NÚMERO DE VÉRTICES,
        CASO SEJA MAIOR QUE 4, O GRAFO NÃO É PLANAR;
        
            - PARA GRAFOS NÃO COMPLETOS: É APLICADA UMA VARIAÇÃO DA FÓRMULA DE EULER PARA PLANARIDADE
        ONDE SE O N° DE ARESTAS <= 3* N° VÉRTICES -6 , O GRAFO PODE SER PLANAR E ENTÃO SÃO VERIFICADAS 
        OUTRAS PROPRIEDADES DE PLANARIDADE DE GRAFOS;
        
            - PARA GRAFOS OS DEMAIS CASO É APLICADO O ALGORITMO DE FUSÃO DE ARESTAS/REMOÇÃO DE VÉRTICES:
        ESSE ALGORITMO CONSISTE EM:
              PARA CADA COMPONENTE DO GRAFO É FEITA:
                A REMOÇÃO DE LOOPS;
                A REMOÇÃO DE ARESTAS PARALELAS;
                A ELIMINAÇÃO DE VÉRTICES DE GRAU 2, PELA FUSÃO DE ARESTAS;
        
                O PROCEDIMENTO É REPETIDO ENQUANTO FOR POSSÍVEL, SE A APLICAÇÃO DO ALGORITMO RESULTAR EM:
                 a) UMA ÚNICA ARESTA; OU
                 b) UM GRAFO COMPLETO DE 4 VÉRTICES (K4);
                 
                 O GRAFO É PLANAR CASO a) OU b) SEJA VERDADE, PARA OS DEMAIS CASOS É NECESSÁRIO VERIFICAR
        OUTRAS PROPRIEDADES.
        
            - PARA GRAFOS BIPARTIDOS: É FEITA A VERIFICAÇÃO DO NÚMERO DE VÉRTICES, CASO SEJA MAIOR OU IGUAL
        A TRÊS É APLICADA UMA VARIAÇÃO DA FÓRUMLA DE EULER PARA PLANARIDADE EM GRAFOS BIPARTIDOS ,ONDE
        SE O N° DE ARESTAS <= 2* N° VÉRTICES -4, O GRAFO É PLANAR, CASO CONTRÁRIO O GRAFO NÃO É PLANAR;
         */
        if (t.identificaCompletos(aux)) {
            if (aux.length > 4) {
                System.out.println("O GRAFO DADO NÃO É PLANAR");
            } else {
                System.out.println("O GRAFO DADO É PLANAR");
            }

        } else if (t.identificaBipartidos(aux)) {
            if (t.numArestas(aux) >= 3 && t.numArestas(aux) <= 2 * t.numVertice(aux) - 4) {
                System.out.println("O GRAFO DADO É PLANAR");

            } else {
                System.out.println("O GRAFO DADO NÃO É PLANAR ²");
            }
        } else if (t.verificaPlanaridade(aux) && t.numVertice(aux) >= 3) {
            aux = t.aplicaAlgoritmo(aux);
            if (t.caso1(aux) || t.caso2(aux)) {
                System.out.println("O GRAFO DADO É PLANAR");
            } else if (t.verificaPlanaridade(aux)) {
                System.out.println("O GRAFO DADO É PLANAR");
            } else {
                System.out.println("O GRAFO DADO É PLANAR");
            }

        } else {
            System.out.println("O GRAFO DADO NÃO É PLANAR");
        }
     
    }
}
