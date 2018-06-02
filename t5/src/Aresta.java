import javafx.scene.shape.Line;

public class Aresta {

    private No inicio;
    private No fim;
    private Line aresta;

    public Aresta(No i, No f, Line a){
        inicio = i;
        fim = f;
        aresta = a;
    }
}
