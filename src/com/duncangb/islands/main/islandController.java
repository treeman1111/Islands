package com.duncangb.islands.main;

import com.duncangb.islands.life.Grass;
import com.duncangb.islands.terrain.Coordinate;
import com.duncangb.islands.terrain.TileMap;
import com.duncangb.islands.time.GameClock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class islandController implements Initializable {
    @FXML private Canvas canvas;
    @FXML private Label info_lbl;
    private int x_position;
    private int y_position;
    private TileMap map;
    private GameClock clock;
    private int tiles_per_screen;
    //private List<Grass> grass;

    private static final int TILE_PIXELS = 10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.setWidth(640);
        canvas.setHeight(640);

        x_position = 0;
        y_position = 0;
        map = new TileMap(10, 10);
        clock = new GameClock();
        tiles_per_screen = (int) canvas.getWidth() / TILE_PIXELS;
        //grass = new ArrayList<>(1000);

        /* main game loop */
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(16),
                event -> {
                    update();
                    clock.tick();
                }
        ));
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();
    }

    @FXML
    public void handleKeyPress(KeyEvent key) {
        switch (key.getCode()) {
            case LEFT:
            case A:
                if (x_position > 0) x_position--; break;
            case RIGHT:
            case D:
                if (x_position < map.getWidthInTiles() - tiles_per_screen) x_position++; break;
            case UP:
            case W:
                if (y_position > 0) y_position--; break;
            case DOWN:
            case S:
                if (y_position < map.getHeightInTiles() - tiles_per_screen) y_position++; break;
        }
    }

    private void update() {
        GraphicsContext gfx = canvas.getGraphicsContext2D();
        boolean isNight = clock.isNight();

        for (int x = x_position; x < x_position + tiles_per_screen; x++) {
            for (int y = y_position; y < y_position + tiles_per_screen; y++) {
                gfx.setFill(isNight ? getNightColor(map.getTileAtPosition(x, y).getColor()) : map.getTileAtPosition(x, y).getColor());
                gfx.fillRect((x - x_position) * TILE_PIXELS, (y - y_position) * TILE_PIXELS, TILE_PIXELS, TILE_PIXELS);
            }
        }

        info_lbl.setText(clock.toString());
/*
        for(int i=0; i < grass.size(); i++) {
            Grass g = grass.get(i);
            Coordinate loc = g.tick();

            if(loc != null) {
                if(!map.getTileAtPosition(loc.getX(), loc.getY()).isOcean()) {
                    Grass gnu = new Grass(loc.getX(), loc.getY(), map);
                    grass.add(gnu);
                }
            }

            gfx.setFill(Color.RED);
            gfx.fillRect(g.getPosition().getX(), g.getPosition().getY(), TILE_PIXELS, TILE_PIXELS);
        }

        for(int i = 0; i < grass.size() / 4; i++) {
            grass.remove(i--);
        }*/
    }

    private Color getNightColor(Color c) {
        int r = (int) (c.getRed()   * 255);
        int g = (int) (c.getGreen() * 255);
        int b = (int) (c.getBlue()  * 255);
        return Color.rgb(r >> 2, g >> 2, b >> 2);
    }

    public void plantGrass(MouseEvent me) {
        //if(!map.getTileAtPosition((int) me.getX(), (int) me.getY()).isOcean())
            //grass.add(new Grass((int) me.getX(), (int) me.getY(), map));
    }
}
