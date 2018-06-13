import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Line2D;

import java.util.ArrayList;

public class Grafo {

    private ArrayList<No> nos;
    private ArrayList<Aresta> arestas;
    private int contNos;
    private int contArestas;
    private int contSobrepostas;

    public Grafo(){
        nos = new ArrayList<No>();
        arestas = new ArrayList<Aresta>();
        contNos = 0;
        contArestas = 0;
        contSobrepostas = 0;
    }

    public void novoCirculo(Circle c){
        No noAux;
        noAux = new No(c);
        nos.add(noAux);
        contNos++;
    }

    public void novaAresta(Aresta a){
        this.arestas.add(a);
        contArestas++;
    }

    public int numNos(){
        return contNos;
    }

    public int numArestas(){
        return contArestas;
    }

    public int arestasSobrepostas () {
        contSobrepostas = 0;
        for (Aresta aresta1 : arestas) {
            for (Aresta aresta2 : arestas) {
                if ((aresta1.noInicio().equals(aresta2.noInicio())) || (aresta1.noFim().equals(aresta2.noFim())) || (aresta1.noInicio().equals(aresta2.noFim())) || (aresta1.noFim().equals(aresta2.noInicio())))
                    continue;
                if (aresta1.equals(aresta2))
                    continue;
                if (Line2D.linesIntersect(aresta1.retornaAresta().getStartX(), aresta1.retornaAresta().getStartY(), aresta1.retornaAresta().getEndX(), aresta1.retornaAresta().getEndY(), aresta2.retornaAresta().getStartX(), aresta2.retornaAresta().getStartY(), aresta2.retornaAresta().getEndX(), aresta2.retornaAresta().getEndY())) {
                    contSobrepostas++;
                }
            }
        }
        return contSobrepostas/2;
    }

    public ArrayList<No> retornaArrayNos (){
        return nos;
    }


    public boolean mouseDentroCirculo(double x, double y, Circle circulo){

        double distance = Math.hypot(x-circulo.getCenterX(), y-circulo.getCenterY());

        if (distance <= circulo.getRadius() + 20){
            return true;
        } else {
            return false;
        }
    }

    public No buscaNo(double x, double y){
        for (No aux: this.nos){
            if (mouseDentroCirculo(x, y, aux.retornaCirculo())){
                return aux;
            }
        }
        return null;
    }

    public void reset(){

        this.nos.clear();
        this.arestas.clear();
        contNos = 0;
        contArestas = 0;
        contSobrepostas = 0;
    }



}
