package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{


        //root.setPrefSize(Const.tileXSize, Const.tileYSize);

        //root.add(new Tile(Color.BLACK), 0, 0);



        Tile.init();
        Const.init();

        final Level level = new Level();

        GridPane root = level.getRoot();



        //System.out.print(level.getMap()[0][0].getColor());

        Scene scene = new Scene(root, Const.appXSize, Const.appYSize);

        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode() == KeyCode.SPACE) {

                    for(int i = 0; i < 10; i++)
                        level.smooth();
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
