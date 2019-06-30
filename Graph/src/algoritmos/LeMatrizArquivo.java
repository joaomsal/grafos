package algoritmos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LeMatrizArquivo {

	Scanner leEntradaTerminal = new Scanner( System.in );
	 BufferedReader leArquivo;
	StringTokenizer leLinhaArquivo;
        
        
	public int[][] leArquivoEImprimeInstancias(String arquivo, int x) {
		// usados para percorrer matriz
		int i, j;
		// indica fim de arquivo
		boolean fimArquivo;
		// guarda o nome do arquivo
		String nomeArquivo = arquivo;
		// matriz temporaria que recebe todas as matrizes do seu arquivo
		int matriz[][] = null;
		// numero de linhas e colunas da matriz
		int N = x;
		try {
			matriz = new int[N][N];
			// cria classe para ler o arquivo e retornar seu conteudo.
			// voce fornece o nome do arquivo e a classe é criada para ler este arquivo.
			leArquivo = new BufferedReader( new FileReader( nomeArquivo ) );
			// Le todas as matrizes do arquivo.
			// Cada matriz é armazenada numa matriz temporária e impressa p/ o usuário.
			int nInstancia = 0;		// informa qual a instancia(matriz) impressa
			String linhaArquivo;	// linha lida do arquivo
			fimArquivo = false;
			while( true ) {	// comandos break fazem o while parar quando o arquivo termina
				nInstancia++;	// seta o numero da instancia atual
				/* Lê a matriz: **************************/
				// as N linhas seguintes do arquivo correspondem as N linhas da matriz:
				for(i = 0; i < N; i++){
					linhaArquivo = leArquivo.readLine();	// le uma linha do arquivo (toda a linha)
					if( linhaArquivo == null ){	// se nada for retornado, é porque acabou o arquivo: 
						fimArquivo = true;
						break; // sai do laco "for(i = 0; i < M; i++)"
					}	
					leLinhaArquivo = new StringTokenizer(linhaArquivo);	// habilita o tokenizer para ler os componetes de linhaArquivo
					// le os M elementos deLinhaArquivo
					for(j = 0; j < N; j++){
						matriz[i][j] = Integer.parseInt( leLinhaArquivo.nextToken());
					}
				}
				// La em cima, foi detectado que o arqiuvo acabou. Entao, nenhuma matriz foi armazenada.
				// Nada sera impresso e como nao há mas matrizes , o programa acabou:
				if(fimArquivo)	break; // sai do while
			}
		} catch( Exception erro ) {
			System.out.println("ERRO : " + erro.toString());
		}
	return matriz;
        }
}