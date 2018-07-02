import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.chart.*;

public class View extends Application {
    private BorderPane borderPane = new BorderPane();
    private TableView<Model.TableData> tabela = new TableView();
    private PieChart pizza = new PieChart();
    private Controller controller = new Controller();
    private final NumberAxis eixoX = new NumberAxis();
    private final CategoryAxis eixoY = new CategoryAxis();
    private BarChart <String, Number> barras = new BarChart<>(eixoY, eixoX);
    private Button btnDados = new Button("Atualizar");
    private ToolBar toolbar = new ToolBar();
    private final ObservableList<Model.TableData> tabelaDeDados = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        btnDados.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ObservableList<Model.TableData> listaAux = FXCollections.observableArrayList();
                for (Model.TableData d : controller.retornaPosicoes()) {
                    listaAux.add(d);
                }
                tabela.setItems(listaAux);
                controller.atualizaGraficoPizza(pizza);
                controller.atualizaGraficoBarras(barras);
            }
        });
        barras.setTitle("Nº de ônibus por linha");

        TableColumn<Model.TableData,String> datahoraCol = new TableColumn<Model.TableData,String>("DataHora");
        datahoraCol.setCellValueFactory(cellData -> cellData.getValue().datahoraProperty());

        TableColumn<Model.TableData,String> ordemCol = new TableColumn<Model.TableData,String>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());

        TableColumn<Model.TableData,String> linhaCol = new TableColumn<Model.TableData,String>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());

        TableColumn<Model.TableData,String> latitudeCol = new TableColumn<Model.TableData,String>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());

        TableColumn<Model.TableData,String> longitudeCol = new TableColumn<Model.TableData,String>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());

        TableColumn<Model.TableData,String> velocidadeCol = new TableColumn<Model.TableData,String>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());

        toolbar.getItems().add(btnDados);
        tabela.getColumns().addAll(datahoraCol, ordemCol, linhaCol, latitudeCol, longitudeCol, velocidadeCol);
        tabela.setItems(tabelaDeDados);
        borderPane.setLeft(pizza);
        borderPane.setBottom(toolbar);
        borderPane.setTop(barras);
        borderPane.setCenter(tabela);
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(borderPane, 1200, 700));
        stage.show();
    }

}
