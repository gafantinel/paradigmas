import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Controller {
    Model model = new Model();

    public void atualizaGraficoPizza(PieChart pizzaAux) {
        int total = model.numeroTotalDeBus();
        int parados = model.retornaBusParados();

        pizzaAux.setTitle("Total de veículos: " + total);
        ObservableList<PieChart.Data> pizza = FXCollections.observableArrayList(new PieChart.Data(parados + " parados", parados), new PieChart.Data((total-parados) + " em movimento", (total-parados)));
        pizzaAux.setData(pizza);
    }

    public void atualizaGraficoBarras(BarChart barras) {
        XYChart.Series<String,Number> barrasAux = new XYChart.Series<>();

        ArrayList<String> linhasAux = model.retornaLinhas();
        for (String linhas : linhasAux) {
            int numOnibus = model.retornaBusEmMovimento(linhas);
            if (numOnibus > 0)
                barrasAux.getData().add(new XYChart.Data<>(linhas, numOnibus));
        }
        barras.getData().clear();
        barras.getData().add(barrasAux);
    }

    public ArrayList<Model.TableData> retornaPosicoes() {
        return model.retornaDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
    }


}
