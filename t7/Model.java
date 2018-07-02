import javafx.beans.property.SimpleStringProperty;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;



public class Model {
    private HttpJSONService http = new HttpJSONService();
    private ArrayList<TableData> tabledata = new ArrayList<>();
    public ArrayList<TableData> retornaDados(String url){
        Atualizar(retornaJSON(url));
        return tabledata;
    }

    private void Atualizar(Map obj) {
        tabledata.clear();
        for (Object lista : (List)obj.get("DATA")) {
            tabledata.add(new TableData((List) lista));
        }
    }

    public int numeroTotalDeBus() {
        return tabledata.size();
    }

    public int retornaBusParados() {
        int parados = 0;
        for (TableData d : tabledata) {
            if (d.getVelocidade() == 0.0)
                parados++;
        }
        return parados;
    }

    public ArrayList<String> retornaLinhas() {
        ArrayList<String> linhasAux = new ArrayList<>();
        for (TableData d : tabledata) {
            if (!linhasAux.contains(d.getLinha()))
                linhasAux.add(d.getLinha());
        }
        return linhasAux;
    }

    public int retornaBusEmMovimento(String linha) {
        int bus = 0;
        for (TableData d : tabledata) {
            if (d.getLinha().equals(linha) && d.getVelocidade() != 0.0)
                bus++;
        }
        return bus;
    }

    private Map retornaJSON(String url){
        Map json = null;
        try {
            json = http.sendGet(url);
        } catch (Exception e){
            System.out.println("Oops");
        }
        return json;
    }


    public class TableData {
        private final SimpleStringProperty datahora;
        private final SimpleStringProperty ordem;
        private final SimpleStringProperty linha;
        private final SimpleStringProperty latitude;
        private final SimpleStringProperty longitude;
        private final SimpleStringProperty velocidade;

        public TableData(List tabledata) {
            this.datahora = new SimpleStringProperty((String)tabledata.get(0));
            this.ordem = new SimpleStringProperty((String)tabledata.get(1));
            this.linha = new SimpleStringProperty(String.valueOf(tabledata.get(2)));
            this.latitude = new SimpleStringProperty(String.valueOf(tabledata.get(3)));
            this.longitude = new SimpleStringProperty(String.valueOf(tabledata.get(4)));
            this.velocidade = new SimpleStringProperty(String.valueOf(tabledata.get(5)));
        }
        // DataHora
        public SimpleStringProperty datahoraProperty() {
            return datahora;
        }
        public String getDatahora() {
            return datahora.get();
        }
        public void setDatahora(String datahora) {
            this.datahora.set(datahora);
        }

        //Ordem
        public SimpleStringProperty ordemProperty() {
            return ordem;
        }
        public String getOrdem() {
            return ordem.get();
        }
        public void setOrdem(String ordem) {
            this.ordem.set(ordem);
        }

        //Linha
        public SimpleStringProperty linhaProperty() {
            return linha;
        }
        public String getLinha() {
            return linha.get();
        }
        public void setLinha(String linha) {
            this.linha.set(linha);
        }

        //Latitude
        public SimpleStringProperty latitudeProperty() {
            return latitude;
        }
        public String getLatitude() {
            return latitude.get();
        }
        public void setLatitude(String latitude) {
            this.latitude.set(latitude);
        }

        //Longitude
        public SimpleStringProperty longitudeProperty() {
            return longitude;
        }
        public String getLongitude() {
            return longitude.get();
        }
        public void setLongitude(String longitude) {
            this.longitude.set(longitude);
        }

        //Velocidade
        public SimpleStringProperty velocidadeProperty() {
            return velocidade;
        }
        public double getVelocidade() {
            return Double.parseDouble(velocidade.get());
        }
        public void setVelocidade(String velocidade) {
            this.velocidade.set(velocidade);
        }

    }

    class HttpJSONService {

        private final String USER_AGENT = "Mozilla/5.0";
        private JSONParsing engine = new JSONParsing();

        // HTTP GET request
        public Map sendGet(String url) throws Exception {

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            System.out.println("\n'GET' request sent to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print result
            // System.out.println(response.toString());

            // Parse JSON result
            JSONParsing engine = new JSONParsing();
            return engine.parseJSON(response.toString());
        }

    }


    class JSONParsing {

        private ScriptEngine engine;

        public JSONParsing() {
            ScriptEngineManager sem = new ScriptEngineManager();
            this.engine = sem.getEngineByName("javascript");
        }

        public Map parseJSON(String json) throws IOException, ScriptException {
            String script = "Java.asJSONCompatible(" + json + ")";
            Object result = this.engine.eval(script);
            Map contents = (Map) result;
            return contents;
        }

    }
}
