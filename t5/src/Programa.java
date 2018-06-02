import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Programa extends Application {
    private int modoDeOperacao = 0;
    private Line l;

    @Override
    public void start(Stage stage){

        BorderPane pane = new BorderPane();

        Button btnQuadrado = new Button("Quadrado");

        Button btnCirculo = new Button("Círculo");

        Button btnAresta = new Button("Aresta");

        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(100);
        slider.setValue(50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        Label sliderLabel = new Label("Tamanho");

        ToolBar toolbar = new ToolBar(btnQuadrado, btnCirculo, btnAresta, sliderLabel, slider);


        btnQuadrado.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("QUADRADO");
                modoDeOperacao = 1;
            }
        });

        btnCirculo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("CIRCULO");
                modoDeOperacao = 2;
            }
        });

        btnAresta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("ARESTA");
                modoDeOperacao = 3;
            }
        });

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (modoDeOperacao == 1){
                    Rectangle r = new Rectangle(event.getX(), event.getY(), slider.getValue(),slider.getValue());
                    r.setFill(Color.RED);
                    pane.getChildren().add(r);
                } else if (modoDeOperacao == 2){
                    Circle c = new Circle(event.getX(), event.getY(), slider.getValue()/2, Color.RED);
                    pane.getChildren().add(c);
                } else if (modoDeOperacao == 3){
                    l = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                    pane.getChildren().add(l);
                }
            }

        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (modoDeOperacao == 3){
                    l.setEndX(event.getX());
                    l.setEndY(event.getY());
                }
            }
        });

        pane.setTop(toolbar);
        stage.setTitle("Gerador de Grafos");
        stage.setScene(new Scene(pane, 950, 650));
        stage.show();
    }
}
