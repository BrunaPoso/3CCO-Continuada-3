package br.com.bandtec.avaliacaoContinuada3.importacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MercadoGrava {

    public static void leExibeArquivo(boolean isCSV) {
        FileReader arq= null;		// objeto FileReader - representa o arquivo a ser lido
        Scanner entrada = null;		// objeto Scanner - para ler do arquivo
        String nomeArquivo;			// nome do arquivo
        boolean deuRuim= false;		// indica se deu erro

        if (isCSV) {
            nomeArquivo= "mercado.csv";	// nome do arquivo, se for CSV
        }
        else {
            nomeArquivo= "mercado.txt";	// nome do arquivo, se for TXT
        }

        // Abre o arquivo para leitura
        try {
            arq = new FileReader(nomeArquivo);
            if (isCSV) {
                // se o arquivo for CSV, usa como delimitador de campo o ';' e o fim de registro
                entrada = new Scanner(arq).useDelimiter(";|\\r\\n");
            }
            else {
                // se o arquivo for TXT, usa como delimitador de campo o ' ' e o fim de registro
                entrada = new Scanner(arq);
            }
        }
        catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1); // encerra o programa, com status de erro
        }

        // Lê os registros do arquivo e exibe os dados lidos na console
        try {
            // Exibe na console os títulos das colunas
            System.out.printf("%-20s%-6s%10s%2s\n","PRODUTO","PRECO","VALIDADE","CATEGORIA");
            // Enquanto tem registro a ser lido
            while (entrada.hasNext()) {
                String produto = entrada.next();
                double preco = entrada.nextDouble();
                String validade = entrada.next();
                Integer categoria = entrada.nextInt();
                System.out.printf("%-20s%-6.2f%10s%2d\n",produto,preco,validade,categoria);
            }
        }
        catch (NoSuchElementException erro)
        {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        }
        catch (IllegalStateException erro)
        {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

}
