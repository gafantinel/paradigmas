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
        noAux = new No(c, contNos);
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

    public No pontoDentrodoNo(double x, double y) {
        for (int i = 0; i < nos.size(); i++) {
            if (nos.get(i).pontoDentroDoCirculo(x, y)) {
                return nos.get(i);
            }
        }
        return null;
    }

    public boolean temAresta (No n1, No n2) {
        for (int i = 0; i < nos.size(); i++) {
            for (int j = 0; j < nos.get(i).retornaListaDeArestas().size(); j++) {
                if (nos.get(i).retornaListaDeArestas().get(j).noInicio() == n1 && nos.get(i).retornaListaDeArestas().get(j).noFim() == n2) {
                    return true;
                }
            }
        }
        return false;
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


    ///////////////////////////////////

    public boolean pontoValido(double x, double y){
        for (No ref: this.nos){
            if (inCircle(x, y, ref.retornaCirculo())){
                return true;
            }
        }
        return false;
    }

    public boolean inCircle(double x, double y, Circle circ){
        double distance = Math.hypot(x-circ.getCenterX(), y-circ.getCenterY());
        if (distance <= circ.getRadius() + 4){
            return true;
        }else{
            return false;
        }
    }

    public No buscaNo(double x, double y){
        for (No ref: this.nos){
            if (inCircle(x, y, ref.retornaCirculo())){
                return ref;
            }
        }
        return null;
    }

    public ArrayList<No> retornaNos() {
        return nos;
    }

    public void reset(){
//        this.nos.forEach((x) -> {
//            x.retornaListaDeArestas().clear();
//        });

        this.nos.clear();
        this.arestas.clear();
        contNos = 0;
        contArestas = 0;
        contSobrepostas = 0;
    }



}
