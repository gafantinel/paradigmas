import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class No {
    private ArrayList<Aresta> arestas;
    private Circle circulo;

    public No(Circle c){
        this.arestas = new ArrayList<Aresta>();
        this.circulo = c;
    }

    public Circle retornaCirculo(){
        return circulo;
    }


    public void conexao(Aresta a){
        arestas.add(a);
    }


    public ArrayList<Aresta> retornaListaDeArestas() {
        return arestas;
    }
}
