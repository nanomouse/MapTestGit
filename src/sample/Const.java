package sample;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

/**
 * Created with IntelliJ IDEA.
 * User: nanomouse
 * Date: 25/06/13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class Const {

    public static final int tileXSize = 7;
    public static final int tileYSize = 7;

    public static final int tileXAmount = 100;
    public static final int tileYAmount = 100;

    public static final int appXSize = tileXSize * tileXAmount;
    public static final int appYSize = tileYSize * tileYAmount;

    public static final ColorAdjust brightEffect = new ColorAdjust();

    public static void init() {

        brightEffect.setBrightness(0.5);
    }





}
