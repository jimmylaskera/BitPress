package br.com.gersondavid.edb2;
import java.util.ArrayList;
import java.lang.String;
import java.util.BitSet;
import java.util.HashMap;

public class Extractor extends FileManager
{
    private String text;
    private String compressed;
    private String table;

    public Extractor() {}
    
    public Extractor (String compressed, String table, String text) {
        readFile(compressed, table);
        writeFile(text);
        
        try {
            int b = input1.read();
            while (b != -1) {
                BitSet bin = BitSet.valueOf(new byte[] {
                    (byte)((b >> 24) & 0xff),
                    (byte)((b >> 16) & 0xff),
                    (byte)((b >> 8) & 0xff),
                    (byte)((b >> 0) & 0xff),
                });
                
                for (int i = 0; i < 24; i++) {
                    if (bin.get(i)) compressed += '1';
                    else compressed += '0';
                }
                
                b = input1.read();
            }
            
            int ch = input2.read();
            while (ch != -1) {
                table += (char) ch;
                ch = input2.read();
            }
            
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
    
    public void textSet () {
        HashMap<String, String> tableMap = new HashMap<String,String>();
        String eof = "eof";
        String z = new String();
        
        int i = 3;
        while ((char) table.charAt(i) != '\n') {
            z += '0';
            i++;
        }
        tableMap.put(eof, z);
        i++;
        
        while (table.charAt(i) != -1) {
            String letter = new String();
            String code = new String();
            letter += (char) table.charAt(i);
            i++;
            while ((char) table.charAt(i) != '\n') {
                code += (char) table.charAt(i);
                i++;
            }
            tableMap.put(letter, code);
            i++;
        }
        
        for (int ch = 0; ch < compressed.length(); ch++) {
            String bin = new String();
            bin += (char) compressed.charAt(ch);
            while (!tableMap.containsValue(bin)) {
                ch++;
                if (ch < compressed.length()) bin += (char) compressed.charAt(ch);
                else break;
            }
            
            if (ch >= compressed.length() || tableMap.get(bin).equals(tableMap.get(eof))) break;
            
            for (String t: tableMap.keySet()) {
                if (tableMap.get(t).equals(bin)) {
                    text += t;
                    break;
                }
            }
        }
    }
    
    public void extract() {
        textSet();
        byte[] txt = text.getBytes();
        
        try {
            output1.write(txt);
            close();
            
            System.out.println("Extração concluída.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
