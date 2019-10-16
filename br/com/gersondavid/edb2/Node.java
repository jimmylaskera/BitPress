package br.com.gersondavid.edb2;

public class Node
{
    private int letter;
    private int count;
    private Node left;
    private Node right;

    public Node()
    {
        letter = -1;
        count = 0;
        left = null;
        right = null;
    }

    public int getLetter () { return letter; }

    public void setLetter (int l) { letter = l; }
    
    public int getCount () { return count; }

    public void setCount (int c) { count = c; }
    
    public Node getLeft () { return left; }

    public void setLeft (Node n) { left = n; }
    
    public void setLeft (int letter, int count) {
        Node n = new Node();
        n.setLetter(letter);
        n.setCount(count);
        left = n;
    }
    
    public Node getRight () { return right; }

    public void setRight (Node n) { right = n; }
    
    public void setRight (int letter, int count) {
        Node n = new Node();
        n.setLetter(letter);
        n.setCount(count);
        right = n;
    }
}
