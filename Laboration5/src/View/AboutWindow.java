
package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class AboutWindow extends Stage{
    private Canvas canvas;
    private GraphicsContext gc;
    
    public AboutWindow(){
        super();
        //this.initModality(Modality.WINDOW_MODAL);
        Image background = new Image("images/about2.png");
        System.out.println(background.getWidth());
        Group root = new Group();
        Scene scene = new Scene(root);
        canvas = new Canvas(background.getWidth(),background.getHeight());
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        root.getChildren().addAll(canvas);
        this.setResizable(false);
        this.setTitle("Controls");
        this.setScene(scene);
    }
}