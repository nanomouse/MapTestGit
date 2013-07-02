package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

import static sample.Tile.getKeys;
import static sample.Tile.getMap;

/**
 * Created with IntelliJ IDEA.
 * User: nanomouse
 * Date: 25/06/13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class Level {

    private static Map<String, Integer> percentMap = new HashMap<String, Integer>();

    private GridPane root = new GridPane();

    private Tile [][] map = new Tile[Const.tileYAmount][Const.tileXAmount];

    Level() {

        Iterator<String> it = Tile.getKeys().iterator();

        while(it.hasNext()) {

            percentMap.put(it.next(), 0);
        }

        for(int y = 0; y < Const.tileYAmount; y++) {

            for(int x = 0; x < Const.tileXAmount; x++) {

                map[y][x] = Tile.getRandomTile(y, x);
            }
        }

        fillField();
    }

    public GridPane getRoot() {

        return this.root;
    }

    private void fillField() {

        //this.root.getChildren().clear();

        for(int y = 0; y < Const.tileYAmount; y++) {

            for(int x = 0; x < Const.tileXAmount; x++) {

                this.root.add(this.map[y][x].getShape(), x, y);
            }
        }
    }

    public Tile [][] getMap() {

        return this.map;
    }

    public void smooth() {

        for(int y = 0; y < Const.tileYAmount; y++) {

            for (int x = 0; x < Const.tileXAmount; x++) {

                /*
                 * flush percent map to zero values
                 */
                for (String key : percentMap.keySet()) {

                    percentMap.put(key, 0);
                }

                setPercentForTile(y - 1, x - 1);
                setPercentForTile(y - 1, x);
                setPercentForTile(y - 1, x + 1);
                setPercentForTile(y, x - 1);

                setPercentByKey(getTile(y, x), 2);

                setPercentForTile(y, x + 1);
                setPercentForTile(y + 1, x - 1);
                setPercentForTile(y + 1, x);
                setPercentForTile(y + 1, x + 1);

                Random rand = new Random();

                int tileRandomizer = rand.nextInt(10) + 1;

                label : while(tileRandomizer > 0) {

                    for(Map.Entry<String, Integer> entry : percentMap.entrySet()) {

                        while(entry.getValue() > 0) {

                            tileRandomizer--;
                            entry.setValue(entry.getValue() - 1);

                            if(tileRandomizer == 0) {

                                map[y][x].setColor(Tile.typeList.get(entry.getKey()));
                                break label;
                            }
                        }
                    }
                }
            }
        }
    }

    public Tile getTile(int y, int x) {

        return map[y][x];
    }

    private void setPercentForTile(int y, int x) {

        if(isPositionIsOutOfBounds(y, x)) {

            addRandomPercent();
        } else {

            setPercentByKey(getTile(y, x), 1);
        }
    }

    private void setPercentByKey(Tile tile, int amount) {

        for(Map.Entry<String, Color> entry : Tile.typeList.entrySet()) {

            if(tile.getColor().equals(entry.getValue())) {

                percentMap.put(entry.getKey(), percentMap.get(entry.getKey()) + amount);
                return;
            }
        }
    }

    private void addRandomPercent() {

        Random rand = new Random();
        int r = rand.nextInt(Tile.getKeys().size());
        String key = Tile.getKeys().get(r);
        percentMap.put(key, percentMap.get(key) + 1);
    }

    public boolean isPositionIsOutOfBounds(int y, int x) {

        if(y < 0 || x < 0 || y > Const.tileYAmount - 1 || x > Const.tileXAmount - 1) {

            return true;
        } else {

            return false;
        }
    }
}
