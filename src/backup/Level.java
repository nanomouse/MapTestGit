package backup;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Const;
import sample.Tile;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nanomouse
 * Date: 25/06/13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class Level {

    private static Map<String, Integer> percentMap = new HashMap<String, Integer>();
    private static List<Tile> tileList = new ArrayList<Tile>();
    private static Random rand = new Random();
    private static int tileRandomizer;

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


                //this.root.getChildren().remove(y, x);
                this.root.add(this.map[y][x].getShape(), y, x);
            }
        }
    }

    public Tile [][] getMap() {

        return this.map;
    }

    public void smooth() {

        for(int y = 0; y < Const.tileYAmount; y++)

            for (int x = 0; x < Const.tileXAmount; x++) {

                /*
                 * flush percent map to zero values
                 */
                for (String key : percentMap.keySet()) {

                    percentMap.put(key, 0);
                }

                setAroundTiles(y, x);

                int checkEmptyTiles = 0;

                for(Tile value : tileList) {

                    checkEmptyTiles++;

                    for (Map.Entry<String, Color> entry : Tile.typeList.entrySet()) {

                        if(value.getColor().equals(entry.getValue())) {

                            percentMap.put(entry.getKey(), percentMap.get(entry.getKey()) + 1);
                            break;
                        }
                    }
                }

                checkEmptyTiles = 8 - checkEmptyTiles;
                System.out.println("CHECK : " + checkEmptyTiles);



                while(checkEmptyTiles > 0) {

                    checkEmptyTiles--;

                    int valuePosition = rand.nextInt(Tile.getKeys().size());

                    Iterator<String> it = Tile.getKeys().iterator();

                    while(it.hasNext() && valuePosition > 0) {

                        valuePosition--;

                        if(valuePosition == 0) {

                            percentMap.put(it.next(), percentMap.get(it.next()) + 1);
                        }
                    }
                }

                for(Map.Entry<String, Color> entry : Tile.typeList.entrySet()) {

                    if(entry.getValue().equals(map[y][x].getColor())) {

                        percentMap.put(entry.getKey(), percentMap.get(entry.getKey()) + 2);
                    } else {

                        //System.out.print("NOOOOOOO");
                    }
                }

                tileRandomizer = rand.nextInt(10) + 1;

                label : while(tileRandomizer > 0) {

                    for(Map.Entry<String, Integer> entry : percentMap.entrySet()) {

                        while(entry.getValue() > 0) { System.out.println(tileRandomizer);

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

        //fillField();
    }

    public Tile getTile(int y, int x) {

        return map[y][x];
    }



    public void setAroundTiles(int y, int x) {

        System.out.println("Y : " + y + " X : " + x);

        tileList.clear();

        if(!isPositionIsOutOfBounds(y - 1, x - 1)) {

            tileList.add(getTile(y - 1, x - 1));
        }

        if(!isPositionIsOutOfBounds(y - 1, x)) {

            tileList.add(getTile(y - 1, x));
        }

        if(!isPositionIsOutOfBounds(y - 1, x + 1)) {

            tileList.add(getTile(y - 1, x + 1));
        }

        if(!isPositionIsOutOfBounds(y, x - 1)) {

            tileList.add(getTile(y, x - 1));
        }

        if(!isPositionIsOutOfBounds(y, x + 1)) {

            tileList.add(getTile(y, x + 1));
        }

        if(!isPositionIsOutOfBounds(y + 1, x - 1)) {

            tileList.add(getTile(y + 1, x - 1));
        }

        if(!isPositionIsOutOfBounds(y + 1, x)) {

            tileList.add(getTile(y + 1, x));
        }

        if(!isPositionIsOutOfBounds(y + 1, x + 1)) {

            tileList.add(getTile(y + 1, x + 1));
        }

    }

    public boolean isPositionIsOutOfBounds(int y, int x) {

        if(y < 0 || x < 0 || y > Const.tileYAmount - 1 || x > Const.tileXAmount - 1) {

            return true;
        } else {

            return false;
        }
    }
}
