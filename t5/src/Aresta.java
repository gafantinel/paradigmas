import javafx.scene.shape.Line;

public class Aresta {

    private No inicio;
    private No fim;
    private Line adjacente;

    public Aresta(No i, No f, Line a){
        this.inicio = i;
        this.fim = f;
        this.adjacente = a;
    }

    public No noInicio(){
        return inicio;
    }

    public No noFim(){
        return fim;
    }

    public Line retornaAresta() {
        return adjacente;
    }

}
