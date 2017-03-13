package com.duncangb.islands.main;

import com.duncangb.islands.terrain.TileMap;
import com.duncangb.islands.time.GameClock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class islandController implements Initializable {
    @FXML private Canvas canvas;
    private int x_position;
    private int y_position;
    private TileMap map;
    private int tiles_per_screen;

    private static final int TILE_PIXELS = 10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        x_position = 0;
        y_position = 0;
        map = new TileMap(20, 20);
        tiles_per_screen = (int) canvas.getWidth() / TILE_PIXELS;

        //GameClock clock = new GameClock();

        /* main game loop */
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(16),
                event -> {
                    drawMap();
                    //clock.tick();
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

    private void drawMap() {
        GraphicsContext gfx = canvas.getGraphicsContext2D();

        for (int x = x_position; x < x_position + tiles_per_screen; x++) {
            for (int y = y_position; y < y_position + tiles_per_screen; y++) {
                gfx.setFill(map.getTileAtPosition(x, y).getColor());
                gfx.fillRect((x - x_position) * TILE_PIXELS, (y - y_position) * TILE_PIXELS, TILE_PIXELS, TILE_PIXELS);
            }
        }
    }
}
