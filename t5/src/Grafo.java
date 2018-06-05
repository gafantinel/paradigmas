import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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

    /*public void novoQuadrado(Rectangle r){
        No noAux;
        noAux = new No(r, contNos);
        nos.add(noAux);
        System.out.println("adicionou porra, é nois");
        contNos++;
    }*/

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



    public int arestasSobrepostas() {
        for (int i = 0; i < nos.size(); i++) {
            for (int j = 0; j < nos.get(i).retornaListaDeArestas().size(); j++) {
                for (int a = 0; a < nos.size(); a++)
                    for (int b = 0; b < nos.get(a).retornaListaDeArestas().size(); b++) {
                        Aresta a1 = nos.get(i).retornaListaDeArestas().get(j);
                        Aresta a2 = nos.get(a).retornaListaDeArestas().get(b);
                        System.out.println("ué");
                        if (a1.noInicio() != a2.noInicio() && a1.noInicio() != a2.noFim() && a1.noFim() != a2.noInicio() && a1.noFim() != a2.noFim() && a1.retornaAresta().getBoundsInParent().intersects(a2.retornaAresta().getBoundsInParent())) {
                            contSobrepostas++;
                            System.out.println("SOBREPOSTAAAAAAA");
                        }
                    }
            }
        }
        return contSobrepostas/2;
    }


}
