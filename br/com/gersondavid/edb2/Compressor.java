package br.com.gersondavid.edb2;
import java.util.ArrayList;
import java.lang.String;
import java.util.BitSet;
import java.util.HashMap;

public class Compressor extends FileManager
{
    private String text;
    private ArrayList<Node> tree = new ArrayList<Node>();
    private String compressed;
    private String table;
    
    public Compressor() {}
    
    public Compressor(String text, String table, String compressed) {
        readFile(text);
        writeFile(table, compressed);
        
        try {
            int ch = input1.read();
            while (ch != -1) {
                text += (char) ch;
                ch = input1.read();
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
    
    public void treeSort() {
        for (int i = 1; i < tree.size() -1; i++) {
            int min = i;
            for (int j = i+1; j < tree.size(); j++) {
                if (tree.get(min).getCount() < tree.get(j).getCount()) min = j;
            }
            if (min != i) {
                Node tmp = tree.get(i);
                tree.set(i, tree.get(min));
                tree.set(min, tmp);
            }
            
            min = i+1;
            while (tree.get(min).getCount() == tree.get(i).getCount()) {
                if (tree.get(min).getLetter() == -1) {
                    Node tmp = tree.get(i);
                    tree.set(i, tree.get(min));
                    tree.set(min, tmp);
                    break;
                }
            }
        }
    }
    
    public void setTree() {
        HashMap<Integer,Integer> frequence = new HashMap<Integer,Integer>();
        
        for (int i = 0; i < text.length(); i++) {
            int key = text.charAt(i);
            if (frequence.containsKey(key)) frequence.put(key, frequence.get(key) + 1);
            else frequence.put(key, 1);
        }
        
        Node eof = new Node();
        eof.setLetter(256);
        eof.setCount(1);
        tree.add(eof);
        
        for (int i: frequence.keySet()) {
            Node n = new Node();
            n.setLetter(i);
            n.setCount(frequence.get(i));
            tree.add(n);
        }
        
        while (tree.size() > 1) {
            treeSort();
            Node n = new Node();
            n.setLeft(tree.get(0));
            n.setCount(n.getCount() + tree.get(0).getCount());
            tree.remove(0);
            n.setRight(tree.get(0));
            n.setCount(n.getCount() + tree.get(0).getCount());
            tree.remove(0);
            tree.add(n);
        }
    }
    
    public void fillTable (HashMap<Integer,String> map, Node n, String bin) {
        if (n.getLetter() != -1) map.put(n.getLetter(), bin);
        else {
            fillTable (map, n.getLeft(), bin + '0');
            fillTable (map, n.getRight(), bin + '1');
        }
    }
    
    public void setTable() {
        HashMap<Integer,String> tableMap = new HashMap<Integer,String>();
        fillTable(tableMap, tree.get(0), "");
        
        for (int i: tableMap.keySet()) {
            table += i;
            table += tableMap.get(i);
            table += '\n';
        }
        
        for (int i = 0; text.charAt(i) != -1; i++) compressed += tableMap.get(text.charAt(i));
    }
    
    public void compress() {
        setTree();
        setTable();
        
        BitSet bin = new BitSet(compressed.length());
        for (int i = 0; compressed.charAt(i) != -1; i++) {
            if ((char) compressed.charAt(i) == '1') bin.set(i);
        }
        
        byte[] bTable = table.getBytes();
        byte[] bCompress = bin.toByteArray();
        
        try {
            output1.write(bTable);
            output2.write(bCompress);
            close();
            
            double tax = compressed.length() / (text.length()*8);
            tax *= 100;
            System.out.println("Compressão concluída. Taxa de compressão: " + tax + "%.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
