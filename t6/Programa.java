import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Random;

public class Programa extends Application {
    private int nivel = 1;
    private int verticesinicio = 5;
    private Grafo g;

    public Pane pane = new Pane();

    public BorderPane raiz = new BorderPane();

    public void novoJogo(){
        pane.getChildren().clear();
        g.reset();
        for (int i = 0; i<verticesinicio; i++){
            Random random = new Random();
            Circle c = new Circle(random.nextInt(900), random.nextInt(600), 25, Color.BLUE);
            c.setStrokeWidth(5);

            g.novoCirculo(c);
            pane.getChildren().add(c);
        }
        for (int i = 0; i<verticesinicio; i++){

            Line linha = new Line();
            linha.setStrokeWidth(8);

            if (i+1 == verticesinicio) {
                linha.setStartX(g.retornaArrayNos().get(i).retornaCirculo().getCenterX());
                linha.setStartY(g.retornaArrayNos().get(i).retornaCirculo().getCenterY());

                linha.setEndX(g.retornaArrayNos().get(0).retornaCirculo().getCenterX());
                linha.setEndY(g.retornaArrayNos().get(0).retornaCirculo().getCenterY());

                Aresta a = new Aresta(g.retornaArrayNos().get(i), g.retornaArrayNos().get(0), linha);
                g.retornaArrayNos().get(i).conexao(a);
                g.retornaArrayNos().get(0).conexao(a);

                g.novaAresta(a);

            } else {
                linha.setStartX(g.retornaArrayNos().get(i).retornaCirculo().getCenterX());
                linha.setStartY(g.retornaArrayNos().get(i).retornaCirculo().getCenterY());

                linha.setEndX(g.retornaArrayNos().get(i+1).retornaCirculo().getCenterX());
                linha.setEndY(g.retornaArrayNos().get(i+1).retornaCirculo().getCenterY());

                Aresta a = new Aresta(g.retornaArrayNos().get(i), g.retornaArrayNos().get(i+1), linha);
                g.retornaArrayNos().get(i).conexao(a);
                g.retornaArrayNos().get(i+1).conexao(a);

                g.novaAresta(a);
            }
            pane.getChildren().add(0,linha);
        }

        g.arestasSobrepostas();
    }

    @Override
    public void start(Stage stage){

        g = new Grafo();

        novoJogo();

        Button btnNovoGrafo = new Button("Novo Grafo");

        Button btnProximoNivel = new Button("Próximo Nível");

        Button btnVerificar = new Button("Verificar");

        Button btnInfo = new Button("Info");

        ToolBar toolbar = new ToolBar(btnNovoGrafo, btnProximoNivel, btnVerificar, btnInfo);

        btnNovoGrafo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                g.reset();
                novoJogo();
            }
        });

        btnProximoNivel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                g.reset();
                novoJogo();
                nivel++;
                verticesinicio++;
            }
        });

        btnVerificar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (g.arestasSobrepostas() == 0){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Verificar");
                    alerta.setHeaderText(null);
                    nivel++;
                    alerta.setContentText("Nível concluído! Prepare-se para o nível " + nivel + ".");
                    alerta.showAndWait();
                    g.reset();
                    verticesinicio++;
                    novoJogo();

                } else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Verificar");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Ainda há " + g.arestasSobrepostas() + " sobreposições. Tente Novamente.");
                    alerta.showAndWait();
                }
            }
        });

        btnInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Informações");
                alerta.setHeaderText(null);
                alerta.setContentText("Nós: " + g.numNos() +  "\nArestas: " + g.numArestas() + "\nSobreposições: " + g.arestasSobrepostas());
                alerta.showAndWait();
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    No noAux = g.buscaNo(event.getX(), event.getY());
                    noAux.retornaCirculo().setFill(Color.RED);
                    for (Aresta a : noAux.retornaListaDeArestas()) {
                        if (noAux.retornaCirculo().getCenterX() == a.retornaAresta().getStartX() && noAux.retornaCirculo().getCenterY() == a.retornaAresta().getStartY()) {
                            a.retornaAresta().setStartX(event.getX());
                            a.retornaAresta().setStartY(event.getY());
                        } else if (noAux.retornaCirculo().getCenterX() == a.retornaAresta().getEndX() && noAux.retornaCirculo().getCenterY() == a.retornaAresta().getEndY()) {
                            a.retornaAresta().setEndX(event.getX());
                            a.retornaAresta().setEndY(event.getY());
                        }
                    }
                    noAux.retornaCirculo().setCenterX(event.getX());
                    noAux.retornaCirculo().setCenterY(event.getY());
                    pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            for (No aux : g.retornaArrayNos()) {
                                aux.retornaCirculo().setFill(Color.BLUE);
                            }
                        }
                    });
                } catch (NullPointerException exception){

                }
            }
        });

        raiz.setTop(toolbar);
        raiz.setCenter(pane);
        stage.setTitle("Gerador de Grafos");
        stage.setScene(new Scene(raiz, 950, 650));
        stage.show();
    }
}
