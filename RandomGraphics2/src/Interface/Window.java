package Interface;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Application implements Runnable{

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private Thread thread;
    private boolean start=true;
    private int xRect;
    private int yRect;
    private int xOval;
    private int yOval;
    private int xRect2;
    private int yRect2;
    private int xOval2;
    private int yOval2;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Random Graphics");
        initComponents(primaryStage);
        primaryStage.show();
    }

    private void initComponents(Stage primaryStage) {
        this.pane = new Pane();
        this.scene = new Scene(this.pane, WIDTH, HEIGHT);
        this.canvas = new Canvas(WIDTH, HEIGHT);
        
        this.thread = new Thread(this);
        this.thread.start();
        
        this.pane.getChildren().add(this.canvas);
        primaryStage.setScene(this.scene);
        primaryStage.setOnCloseRequest(exit);
    }

    private void myDraw(GraphicsContext gc) {
        Random rand = new Random();
        while(true){    
            try {
                if(start){
                    xRect=rand.nextInt(WIDTH-100)+1;
                    xRect2=xRect;
                    yRect=rand.nextInt(HEIGHT-100)+1;
                    yRect2=yRect;
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    gc.setFill(Color.AQUA);
                    gc.fillRect(xRect,yRect, 100, 100);

                    xOval=rand.nextInt(WIDTH-100)+1;
                    xOval2=xOval;
                    yOval=rand.nextInt(HEIGHT-100)+1;
                    yOval2=yOval;
                    gc.setFill(Color.ROSYBROWN);
                    gc.fillOval(xOval,yOval, 100, 100);
                    //Thread.sleep(500);
                }else{
                    while(true){
                        //se pregunta en caso de que la figura ya llegara al random
                        //para hacer otros random
                        if(xRect2==xRect&&yRect2==yRect){
                            xRect2=rand.nextInt(WIDTH-100)+1;
                            yRect2=rand.nextInt(HEIGHT-100)+1;
                        }
                        if(xOval2==xOval&&yOval2==yOval){
                            xOval2=rand.nextInt(WIDTH-100)+1;
                            yOval2=rand.nextInt(HEIGHT-100)+1;
                        }
                        
                        //pregunto si la posicion x de la figura es menor o mayor al nuevo random
                        //dependiendo de eso sumo o resto el eje x
                        if(xRect<xRect2){
                            gc.clearRect(0, 0, WIDTH, HEIGHT);
                            gc.setFill(Color.AQUA);
                            xRect=xRect+1;
                            gc.fillRect(xRect,yRect, 100, 100);
                            
                            gc.setFill(Color.ROSYBROWN);
                            gc.fillOval(xOval,yOval, 100, 100);
                        }else{
                            if(xRect>xRect2){
                                gc.clearRect(0, 0, WIDTH, HEIGHT);
                                gc.setFill(Color.AQUA);
                                xRect=xRect-1;
                                gc.fillRect(xRect,yRect, 100, 100);

                                gc.setFill(Color.ROSYBROWN);
                                gc.fillOval(xOval,yOval, 100, 100);
                            }
                        }
                        //lo mismo para el eje y
                        if(yRect<yRect2){
                            gc.clearRect(0, 0, WIDTH, HEIGHT);
                            gc.setFill(Color.AQUA);
                            yRect=yRect+1;
                            gc.fillRect(xRect,yRect, 100, 100);
                            
                            gc.setFill(Color.ROSYBROWN);
                            gc.fillOval(xOval,yOval, 100, 100);
                        }else{
                            if(yRect>yRect2){
                                gc.clearRect(0, 0, WIDTH, HEIGHT);
                                gc.setFill(Color.AQUA);
                                yRect=yRect-1;
                                gc.fillRect(xRect,yRect, 100, 100);

                                gc.setFill(Color.ROSYBROWN);
                                gc.fillOval(xOval,yOval, 100, 100);
                            }
                        }
                        
                        //el mismo prosedimiento del rectangulo pero con el ovalo
                        if(xOval<xOval2){
                            gc.clearRect(0, 0, WIDTH, HEIGHT);
                            gc.setFill(Color.AQUA);
                            gc.fillRect(xRect,yRect, 100, 100);
                            
                            gc.setFill(Color.ROSYBROWN);
                            xOval=xOval+1;
                            gc.fillOval(xOval,yOval, 100, 100);
                        }else{
                            if(xOval>xOval2){
                                gc.clearRect(0, 0, WIDTH, HEIGHT);
                                gc.setFill(Color.AQUA);
                                gc.fillRect(xRect,yRect, 100, 100);

                                gc.setFill(Color.ROSYBROWN);
                                xOval=xOval-1;
                                gc.fillOval(xOval,yOval, 100, 100);
                            }
                        }
                        //tambien para el eje y del ovalo
                        if(yOval<yOval2){
                            gc.clearRect(0, 0, WIDTH, HEIGHT);
                            gc.setFill(Color.AQUA);
                            gc.fillRect(xRect,yRect, 100, 100);
                            
                            gc.setFill(Color.ROSYBROWN);
                            yOval=yOval+1;
                            gc.fillOval(xOval,yOval, 100, 100);
                        }else{
                            if(yOval>yOval2){
                                gc.clearRect(0, 0, WIDTH, HEIGHT);
                                gc.setFill(Color.AQUA);
                                gc.fillRect(xRect,yRect, 100, 100);

                                gc.setFill(Color.ROSYBROWN);
                                yOval=yOval-1;
                                gc.fillOval(xOval,yOval, 100, 100);
                            }
                        }
                        //se pone un tiempo de espera para que no vaya muy rapido
                        //lo reduje porque duraba mucho
                        Thread.sleep(4);
                    }
                }
                start=false;
            } 
            catch (InterruptedException ex) {}
        }
    }

    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        myDraw(gc);
    }
    
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}