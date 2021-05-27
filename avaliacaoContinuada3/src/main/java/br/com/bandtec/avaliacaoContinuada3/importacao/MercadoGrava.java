package br.com.bandtec.avaliacaoContinuada3.importacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MercadoGrava {

    public static void leExibeArquivo(boolean isCSV) {
        FileReader arq= null;
        Scanner entrada = null;
        String nomeArquivo;
        boolean deuRuim= false;

        if (isCSV) {
            nomeArquivo= "mercado.csv";
        }
        else {
            nomeArquivo= "mercado.txt";
        }

        try {
            arq = new FileReader(nomeArquivo);
            if (isCSV) {
                entrada = new Scanner(arq).useDelimiter(";|\\r\\n");
            }
            else {
                entrada = new Scanner(arq);
            }
        }
        catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-20s%-6s%10s%2s\n","PRODUTO","PRECO","VALIDADE","CATEGORIA");
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
