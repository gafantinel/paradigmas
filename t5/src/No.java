import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class No {
    //private int indice;
    private ArrayList<Aresta> arestas;
    //private Rectangle quadrado;
    private Circle circulo;

    public No(Circle c, int ind){
        this.arestas = new ArrayList<Aresta>();
        this.circulo = c;
        //this.indice = ind;
    }

    /*public No(Rectangle q, int ind){
        this.arestas = new ArrayList<Aresta>();
        this.quadrado = q;
        this.indice = ind;
    }*/

    public Circle retornaCirculo(){
        return circulo;
    }

    /*public Rectangle retornaQuadrado(){
        return quadrado;
    }*/

    public void conexao(Aresta a){
        arestas.add(a);
    }

    public boolean pontoDentroDoCirculo(double x, double y){
        if (this.distanciaAteCentro(x, y)<this.circulo.getRadius()){
            return true;
        } else {
            return false;
        }
    }

    public double distanciaAteCentro(double x, double y){
        return (Math.sqrt(((x-circulo.getCenterX())*(x-circulo.getCenterX()))+((y-circulo.getCenterY())*(y-circulo.getCenterY()))));
    }

    public ArrayList<Aresta> retornaListaDeArestas() {
        return arestas;
    }
}
