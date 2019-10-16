package br.com.gersondavid.edb2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.io.IOException;

public abstract class FileManager
{
    protected FileInputStream input1;
    protected FileInputStream input2;
    protected FileOutputStream output1;
    protected FileOutputStream output2;

    public void readFile(String name)
    {
        try {
            input1 = new FileInputStream(name);
        } catch (FileNotFoundException fnfe) {
            System.out.println("O arquivo de entrada nao existe ou seu nome esta incorreto.");
        } catch (SecurityException se) {
            System.out.println("O programa nao possui direitos necessarios para abrir este arquivo.");
        }
    }
    
    public void readFile(String name1, String name2)
    {
        try {
            input1 = new FileInputStream(name1);
            input2 = new FileInputStream(name2);
        } catch (FileNotFoundException fnfe) {
            System.out.println("O arquivo de entrada nao existe ou seu nome esta incorreto.");
        } catch (SecurityException se) {
            System.out.println("O programa nao possui direitos necessarios para abrir este arquivo.");
        }
    }
    
    public void writeFile(String name)
    {
        try {
            output1 = new FileOutputStream(name);
        } catch (FileNotFoundException fnfe) {
            System.out.println("O arquivo de saida nao pode ser criado.");
        } catch (SecurityException se) {
            System.out.println("O programa nao possui direitos necessarios para abrir este arquivo.");
        }
    }
    
    public void writeFile(String name1, String name2)
    {
        try {
            output1 = new FileOutputStream(name1);
            output2 = new FileOutputStream(name2);
        } catch (FileNotFoundException fnfe) {
            System.out.println("O arquivo de saida nao pode ser criado.");
        } catch (SecurityException se) {
            System.out.println("O programa nao possui direitos necessarios para abrir este arquivo.");
        }
    }
    
    public void close() {
        try {
            if (input1 != null) input1.close();
            if (input2 != null) input2.close();
            if (output1 != null) output1.close();
            if (output2 != null) output2.close();
        } catch (IOException ioe) {
            System.out.println("Chamada invalida.");
        }
    }
}
