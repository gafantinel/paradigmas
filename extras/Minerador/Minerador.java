import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;

import javafx.scene.media.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


interface BrowsableImageCollection<T> {
   public T forward();
   public T backward();
   public T current();
}

// Classe que encapsula uma implementacao de uma colecao 'navegavel' de imagens.
// Outras implementacoes sao possiveis: por exemplo, pode-se otimizar o codigo 
// para evitar criar uma nova imagem (new Image) a cada chamada de metodo.
class ImageModel implements BrowsableImageCollection<Image> {

   private List<String> names = Arrays.asList("Coal_Ore.png",
                                              "Iron_Ore.png",
                                              "Gold_Ore.png",
                                              "Redstone_Ore.png",
                                              "Emerald_Ore.png",
                                              "Diamond_Ore.png");
   private int curr = 0;
   private int forca;
   private int resistencia;
   private int coal, iron, gold, redstone, emerald, diamond = 0;

   private int cont = 0;

   JFXPanel teste = new JFXPanel();

   public Image forward() {
      curr = curr == names.size()-1 ? 0 : curr+1;
      if (curr == 0 || curr == 1){
         resistencia = 0;
      } else if (curr == 2 || curr == 3){
         resistencia = 1;
      } else if (curr == 4 || curr == 5){
         resistencia = 2;
      }
      return current();
   }
   public Image backward() {
      curr = curr == 0 ? names.size()-1 : curr-1;
      return current();
   }
   public Image current() {
      return new Image(names.get(curr));
   }


   ///// Métodos auxiliares com som /////
   public void addCont(){
      this.cont += 1;
      Media media = new Media(new File("metal_crunch.wav").toURI().toASCIIString());
      MediaPlayer player = new MediaPlayer(media);
      player.play();
      player.setOnEndOfMedia(new Runnable() {
         @Override
         public void run() {
            player.dispose();
         }
      });

   }
   public void zeraCont(){
      if (this.cont == 0){
         return;
      } else {
         this.cont = 0;
         Media media = new Media(new File("coin_flip.wav").toURI().toASCIIString());
         MediaPlayer player = new MediaPlayer(media);
         player.play();
         player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
               player.dispose();
            }
         });
      }
   }

   public void soco(){
      Media media = new Media(new File("punch.wav").toURI().toASCIIString());
      MediaPlayer player = new MediaPlayer(media);
      player.play();
      player.setOnEndOfMedia(new Runnable() {
         @Override
         public void run() {
            player.dispose();
         }
      });
   }
   
   ///// Métodos auxiliares /////
   public void addCoal(){
      this.coal += 1;
   }
   
   public int getCont(){
      return cont;
   }
   public int getCoal(){
      return coal;
   }

   public void addIron(){
      this.iron += 1;
   }
   public int getIron(){
      return iron;
   }

   public void addGold(){
      this.gold += 1;
   }
   public int getGold(){
      return gold;
   }

   public void addRedstone(){
      this.redstone += 1;
   }
   public int getRedstone(){
      return redstone;
   }

   public void addEmerald(){
      this.emerald += 1;
   }
   public int getEmerald(){
      return emerald;
   }

   public void addDiamond(){
      this.diamond += 1;
   }
   public int getDiamond(){
      return diamond;
   }

   public int getResistencia(){
      return resistencia;
   }
   public void setResistencia(int resistencia){
      this.resistencia = resistencia;
   }

   public int getCurr(){
      return curr;
   }

   public int getForca(){
      return forca;
   }
   public void setForca(int forca){
      this.forca = forca;
   }



}



public class Minerador extends Application{


   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) {

      BrowsableImageCollection<Image> images = new ImageModel();

      BorderPane borderPane = new BorderPane();
      HBox root = new HBox();
      Scene scene = new Scene(borderPane, 700, 600);
      ImageView imageView = new ImageView(images.current());

      Image wood = new Image(getClass().getResourceAsStream("Wooden_Pickaxe.png"));
      Image iron = new Image(getClass().getResourceAsStream("Iron_Pickaxe.png"));
      Image diamond = new Image(getClass().getResourceAsStream("Diamond_Pickaxe.png"));
      Image punch = new Image(getClass().getResourceAsStream("box.png"));

      Label carvao = new Label("Coal = " +((ImageModel) images).getCoal());
      Label ferro = new Label("Iron = " +((ImageModel) images).getIron());
      Label ouro = new Label("Gold = " +((ImageModel) images).getGold());
      Label pedrav = new Label("Redstone = " +((ImageModel) images).getRedstone());
      Label esmeralda = new Label("Emerald = " +((ImageModel) images).getEmerald());
      Label diamante = new Label("Diamond = " +((ImageModel) images).getDiamond());


      Button btnBack = new Button("<");
      Button btnFwd = new Button(">");

      Button diamondPick = new Button();
      diamondPick.setGraphic(new ImageView(diamond));
      Button ironPick = new Button();
      ironPick.setGraphic(new ImageView(iron));
      Button woodenPick = new Button();
      woodenPick.setGraphic(new ImageView(wood));
      Button punchGlove = new Button();
      punchGlove.setGraphic(new ImageView(punch));

      Pane spacer = new Pane();
      HBox.setHgrow(spacer, Priority.SOMETIMES);
      ToolBar toolBar = new ToolBar(diamondPick, ironPick, woodenPick, punchGlove,spacer);
      HBox inventory = new HBox();
      ToolBar toolBar2 = new ToolBar(inventory);


      //////////////////////////////////////////////// BOTÕES /////////////////////////////////////////////

      btnBack.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
            imageView.setImage(images.backward());
         }
      });

      btnFwd.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
            imageView.setImage(images.forward());
         }
      });

      diamondPick.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            scene.setCursor(new ImageCursor(diamond));
            ((ImageModel) images).zeraCont();
            ((ImageModel) images).setForca(3);
         }
      });

      ironPick.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            scene.setCursor(new ImageCursor(iron));
            ((ImageModel) images).zeraCont();
            ((ImageModel) images).setForca(2);
         }
      });

      woodenPick.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            scene.setCursor(new ImageCursor(wood));
            ((ImageModel) images).zeraCont();
            ((ImageModel) images).setForca(1);
         }
      });

      punchGlove.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            scene.setCursor(new ImageCursor(punch));
            ((ImageModel) images).zeraCont();
            ((ImageModel) images).setForca(0);
         }
      });

      imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent mouseEvent) {

            // Bloco Coal
            if (((ImageModel) images).getCurr() == 0){
               if (((ImageModel) images).getForca() == 0){
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 3){
                     ((ImageModel) images).addCoal();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 2){
                     ((ImageModel) images).addCoal();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 1){
                     ((ImageModel) images).addCoal();
                     ((ImageModel) images).zeraCont();
                  }
               }

            // Bloco Iron
            } else if (((ImageModel) images).getCurr() == 1){
               if (((ImageModel) images).getForca() == 0){
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 3){
                     ((ImageModel) images).addIron();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 2){
                     ((ImageModel) images).addIron();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 1){
                     ((ImageModel) images).addIron();
                     ((ImageModel) images).zeraCont();
                  }
               }

            // Bloco Gold
            } else if (((ImageModel) images).getCurr() == 2){
               if (((ImageModel) images).getForca() == 0){
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 5){
                     ((ImageModel) images).addGold();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 4){
                     ((ImageModel) images).addGold();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 3){
                     ((ImageModel) images).addGold();
                     ((ImageModel) images).zeraCont();
                  }
               }

            // BLoco Redstone
            } else if (((ImageModel) images).getCurr() == 3){
               if (((ImageModel) images).getForca() == 0){
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 5){
                     ((ImageModel) images).addRedstone();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 4){
                     ((ImageModel) images).addRedstone();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 3){
                     ((ImageModel) images).addRedstone();
                     ((ImageModel) images).zeraCont();
                  }
               }

            // Bloco Emerald
            } else if (((ImageModel) images).getCurr() == 4){
               if (((ImageModel) images).getForca() == 0){
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 8){
                     ((ImageModel) images).addEmerald();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 7){
                     ((ImageModel) images).addEmerald();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3){
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 6){
                     ((ImageModel) images).addEmerald();
                     ((ImageModel) images).zeraCont();
                  }
               }

            // Bloco Diamond
            } else if (((ImageModel) images).getCurr() == 5) {
               if (((ImageModel) images).getForca() == 0) {
                  ((ImageModel) images).soco();

               } else if (((ImageModel) images).getForca() == 1) {
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 8) {
                     ((ImageModel) images).addDiamond();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 2) {
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 7) {
                     ((ImageModel) images).addDiamond();
                     ((ImageModel) images).zeraCont();
                  }
               } else if (((ImageModel) images).getForca() == 3) {
                  ((ImageModel) images).addCont();
                  if (((ImageModel) images).getCont() == 6) {
                     ((ImageModel) images).addDiamond();
                     ((ImageModel) images).zeraCont();
                  }
               }
            }

            // Atualiza labels
            carvao.setText("Coal = " + ((ImageModel) images).getCoal());
            ferro.setText("Iron = " + ((ImageModel) images).getIron());
            ouro.setText("Gold = " + ((ImageModel) images).getGold());
            pedrav.setText("Redstone = " + ((ImageModel) images).getRedstone());
            esmeralda.setText("Emerald = " + ((ImageModel) images).getEmerald());
            diamante.setText("Diamond = " + ((ImageModel) images).getDiamond());
         }
      });

    /////////////////////////////////////////////////////////////////////////////////////////////////////

      inventory.getChildren().addAll(carvao, ferro, ouro, pedrav, esmeralda, diamante);
      inventory.setAlignment(Pos.CENTER);
      inventory.setSpacing(50);
      Image bg1 = new Image("bg.jpg");
      BackgroundImage bg = new BackgroundImage(bg1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
      Background bg2 = new Background(bg);
      borderPane.setBackground(bg2);
      root.getChildren().addAll(btnBack,imageView,btnFwd);
      root.setAlignment(Pos.CENTER);
      borderPane.setCenter(root);
      borderPane.setBottom(toolBar2);
      borderPane.setTop(toolBar);
      stage.setTitle("Minerador");
      stage.setScene(scene);
      stage.show();
   }
}
