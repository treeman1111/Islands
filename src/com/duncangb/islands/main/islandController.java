package com.duncangb.islands.main;

import com.duncangb.islands.gfx.Shaders;
import com.duncangb.islands.terrain.Tile;
import com.duncangb.islands.terrain.ChunkManager;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class islandController implements Initializable {
    @FXML private Canvas canvas;
    @FXML private Label info_lbl;
    @FXML private BorderPane border;

    private static final int TILE_PIXELS = 15;

    private int x_position, y_position;
    private ChunkManager map;
    private GameClock clock;
    private GraphicsContext gfx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init_resize_listeners();
        init_vars();
        init_map();
        init_timer();
    }

    @FXML
    public void handleKeyPress(KeyEvent key) {
        switch (key.getCode()) {
            case LEFT:
            case A:
                if (x_position > 0) x_position--;
                break;
            case RIGHT:
            case D:
                if (x_position < map.getWidthInTiles() - (canvas.getWidth() / TILE_PIXELS)) x_position++;
                break;
            case UP:
            case W:
                if (y_position > 0) y_position--;
                break;
            case DOWN:
            case S:
                if (y_position < map.getHeightInTiles() - (canvas.getHeight() / TILE_PIXELS)) y_position++;
                break;
        }
    }


    @FXML
    public void handleMouseClicked(MouseEvent me) {
        int tile_x = x_position + (int) (me.getX() / TILE_PIXELS);
        int tile_y = y_position + (int) (me.getY() / TILE_PIXELS);
        Tile t = map.getTileAtPosition(tile_x, tile_y);

        info_lbl.setText(String.format("(%d,%d): %f, %f", tile_x, tile_y, t.getHeight(), t.getTemperature()));
    }


    private void init_resize_listeners() {
        this.border.heightProperty().addListener((observableValue, old_val, new_val) -> {
            this.canvas.setHeight(new_val.doubleValue() - this.info_lbl.getHeight());
            draw();
        });

        this.border.widthProperty().addListener((observableValue, old_val, new_val) -> {
            this.canvas.setWidth(new_val.doubleValue());
            draw();
        });
    }

    private void init_vars() {
        this.x_position = 0;
        this.y_position = 0;
        this.map = new ChunkManager(50, 50);
        this.clock = new GameClock();
        this.gfx = canvas.getGraphicsContext2D();
    }

    private void init_map() {
        for(int x = 0; x < this.map.getWidthInChunks(); x++) {
            for(int y = 0; y < this.map.getHeightInChunks(); y++) {
                this.map.getChunkAtPosition(x, y);
            }
        }

        while(this.map.getNumChunksNeedingUpdate() > 0) {
            this.map.updateChunks();
        }
    }

    private void init_timer() {
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(16),
                event -> {
                    draw();
                    this.clock.tick();
                }
        ));

        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();
    }

    private void draw() {
        for(int x = 0; x < (canvas.getWidth() / TILE_PIXELS); x++) {
            for(int y = 0; y < (canvas.getHeight() / TILE_PIXELS); y++) {
                gfx.setFill(Shaders.shade_elevation(map.getTileAtPosition(x_position + x, y_position + y)));
                gfx.fillRect(x * TILE_PIXELS, y * TILE_PIXELS, TILE_PIXELS, TILE_PIXELS);
            }
        }
    }

    private Color getNightColor(Color c) {
        int r = (int) (c.getRed()   * 255);
        int g = (int) (c.getGreen() * 255);
        int b = (int) (c.getBlue()  * 255);
        return Color.rgb(r >>> 2, g >>> 2, b >>> 2);
    }
}
