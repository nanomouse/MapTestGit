package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nanomouse
 * Date: 25/06/13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class Tile {

    public static Map<String, Color> typeList = new HashMap<String, Color>();
    private static Random rand = new Random();
    private static List<String> keys;

    private Color color;
    private Rectangle shape;

    public int getY() {
        return y;
    }

    private final int y;

    public int getX() {
        return x;
    }

    private final int x;

    Tile(Color color, int y, int x) {

        this.color = color;
        this.y = y;
        this.x = x;

        this.shape = new Rectangle(Const.tileXSize, Const.tileYSize);
        this.shape.setFill(color);

        this.shape.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                shape.setEffect(Const.brightEffect);
            }
        });

        this.shape.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                shape.setEffect(null);
            }
        });

        this.shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                System.out.print(getY() + ":" + getX());
            }
        });

    }

    public static void init() {

        typeList.put("water", Color.DEEPSKYBLUE);
        typeList.put("grass", Color.LAWNGREEN);
        typeList.put("desert", Color.YELLOW);
        typeList.put("forest", Color.DARKGREEN);
        typeList.put("mountain", Color.GRAY);

        keys = new ArrayList<String>(typeList.keySet());
    }

    public static List<String> getKeys() {

        return keys;
    }

    public Rectangle getShape() {

        return this.shape;
    }

    public static Color getColorById(String type) {

        return typeList.get(type);
    }

    public static Tile getRandomTile(int y, int x) {

        Color randColor =  typeList.get(keys.get(rand.nextInt(keys.size())));
        return new Tile(randColor, y, x);
    }

    public static Map getMap() {

        return typeList;
    }

    public Color getColor() {

        return this.color;
    }

    public void setColor(Color color) {

        this.color = color;
        this.shape.setFill(color);
    }

}
