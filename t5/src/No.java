import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class No {
    private int indice;
    private ArrayList<Aresta> adjacente;
    private Rectangle quadrado;
    private Circle circulo;

    public No(Circle c, int ind){
        adjacente = new ArrayList<Aresta>();
        circulo = c;
        indice = ind;
    }

    public No(Rectangle q, int ind){
        adjacente = new ArrayList<Aresta>();
        quadrado = q;
        indice = ind;
    }


}
