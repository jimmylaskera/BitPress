package br.com.gersondavid.edb2;

public class Main
{
    public static void main(String[] args) {
        if (args[0].equals("compress")) {
            if (args[1].endsWith(".txt") && args[2].endsWith(".edz") && args[3].endsWith(".edt")) {
                Compressor press = new Compressor(args[1], args[3], args[2]);
                press.compress();
            } else System.out.println("Erro na descrição dos arquivos. Execute o programa sem comandos para mais informações.");
        } else if (args[0].equals("extract")) {
            if (args[1].endsWith(".edt") && args[2].endsWith(".edz") && args[3].endsWith(".txt")) {
                Extractor press = new Extractor(args[2], args[1], args[3]);
                press.extract();
            } else System.out.println("Erro na descrição dos arquivos. Execute o programa sem comandos para mais informações.");
        } else System.out.println("Comando inválido. Execute o programa seguido da função desejada " +
        "no terminal ('compress' para compressão, ou 'extract' para extração). Em seguida, os nomes " +
        "dos arquivos na seguinte ordem: \n 'compress file_name.txt file_name.edz file_name.edt' " +
        "para compressão, com o nome do arquivo original primeiro, e então o nome dos arquivos finais" +
        " de compressão e de tabela em sequência; \n " +
        "'extract file_name.edt file_name.edz file_name.txt' para extração, contendo o nome dos arquivos" +
        "de tabela e o comprimido, respectivamente, e o arquivo final que será criado.\n\n" +
        "Caso os arquivos finais não existam, eles serão criados.");
        
    }
}
