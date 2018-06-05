import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Programa extends Application {
    private int modoDeOperacao = 0;
    private Grafo g;
    private Line aresta;
    private No noAuxiliar;

    @Override
    public void start(Stage stage){

        g = new Grafo();

        BorderPane pane = new BorderPane();

        //Button btnQuadrado = new Button("Quadrado");

        Button btnCirculo = new Button("Círculo");

        Button btnAresta = new Button("Aresta");

        Button btnInfo = new Button("Informações");

        Slider raio = new Slider();
        raio.setMin(1);
        raio.setMax(100);
        raio.setValue(50);
        raio.setShowTickLabels(true);
        raio.setShowTickMarks(true);

        Label sliderLabel = new Label("Raio");

        Slider largura = new Slider();
        largura.setMin(1);
        largura.setMax(10);
        largura.setValue(4);
        largura.setShowTickLabels(true);
        largura.setShowTickMarks(true);

        Label larguraLabel = new Label("Largura");

        ColorPicker colorPicker = new ColorPicker(Color.RED);

        ToolBar toolbar = new ToolBar(btnCirculo, btnAresta, sliderLabel, raio, larguraLabel, largura, colorPicker, btnInfo);

        /*btnQuadrado.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("QUADRADO");
                modoDeOperacao = 1;
            }
        });*/

        btnCirculo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //System.out.println("CIRCULO");
                modoDeOperacao = 1;
            }
        });

        btnAresta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //System.out.println("ARESTA");
                modoDeOperacao = 2;
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

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (modoDeOperacao == 1){
                    Circle c = new Circle(event.getX(), event.getY(), raio.getValue()/2, colorPicker.getValue());
                    g.novoCirculo(c);
                    pane.getChildren().add(c);
                } else if (modoDeOperacao == 2){
                    No temporario = g.pontoDentrodoNo(event.getX(), event.getY());
                    if (temporario != null) {
                        if (noAuxiliar == null) {
                            aresta = new Line(temporario.retornaCirculo().getCenterX(), temporario.retornaCirculo().getCenterY(), event.getX(), event.getY());
                            aresta.setStroke(colorPicker.getValue());
                            aresta.setStrokeWidth(largura.getValue());
                            pane.getChildren().add(aresta);
                            noAuxiliar = temporario;
                        } else if (noAuxiliar != temporario) {
                            if (!g.temAresta(noAuxiliar, temporario)) {
                                pane.getChildren().remove(aresta);
                                g.novaAresta(new Aresta(noAuxiliar, temporario, aresta));
                                aresta = new Line(noAuxiliar.retornaCirculo().getCenterX(), noAuxiliar.retornaCirculo().getCenterY(), temporario.retornaCirculo().getCenterX(), temporario.retornaCirculo().getCenterY());
                                aresta.setStroke(colorPicker.getValue());
                                aresta.setStrokeWidth(largura.getValue());
                                pane.getChildren().add(aresta);
                                aresta = null;
                                noAuxiliar = null;
                            }
                        }
                    }
                }
            }

        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try{
                    if (modoDeOperacao == 2){

                        aresta.setEndX(event.getX());
                        aresta.setEndY(event.getY());
                    }
                } catch (NullPointerException ex){
                    return;
                }
            }
        });

        pane.setTop(toolbar);
        stage.setTitle("Gerador de Grafos");
        stage.setScene(new Scene(pane, 950, 650));
        stage.show();
    }
}
