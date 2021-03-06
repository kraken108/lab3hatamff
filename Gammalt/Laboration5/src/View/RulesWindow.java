
package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class RulesWindow extends Stage{
    private Canvas canvas;
    private GraphicsContext gc;
    
    public RulesWindow(){
        super();
        //this.initModality(Modality.WINDOW_MODAL);
        Image background = new Image("images/rules2.png");
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
