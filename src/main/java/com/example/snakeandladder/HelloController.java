package com.example.snakeandladder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView a1;
    @FXML
    private ImageView a2;
    @FXML
    private Button button;
    @FXML
    private ImageView dice_image;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;
    @FXML
    private ImageView gif_pic;
    @FXML
    private ImageView down_arrow;
    @FXML
    private Label name;
    private static String winner;
    private piece piece1;
    private piece piece2;
    private Player p1 = new Player(true);
    private Player p2 = new Player(false);
    private ArrayList<Ladder> ladders = new ArrayList<>();
    private ArrayList<Snake> snakes = new ArrayList<>();

    public HelloController(){
        Ladder temp1 = new Ladder();
        Snake temp2 = new Snake();
        ladders.add(temp1);
        set(ladders);
        snakes.add(temp2);
        set(snakes);

    }

    public void show_name(ActionEvent e)
    {
        name.setText(winner);
        name.setVisible(true);
    }

    public void exit(ActionEvent e)
    {
        System.exit(0);
    }

    public <T> void set(ArrayList<T> t)
    {
        if(t.get(0) instanceof Ladder)
        {
            t.remove(t.get(0));
            int[] start = {4,6,8,10,89,74,65,58,41,61};
            int[] end = {37,55,68,90,92,94,96,98,60,81};
            for(int i = 0; i<start.length; i++)
            {
                T temp = (T) new Ladder(start[i], end[i]);
                t.add(temp);
            }
        }
        else {
            t.remove(t.get(0));
            int[] start = {40,99,43,97,56,95,67,93,72};
            int[] end = {20,19,3,44,16,66,7,73,12};
            for(int i = 0; i<start.length; i++)
            {
                T temp = (T) new Snake(start[i], end[i]);
                t.add(temp);
            }
        }
    }


    public void buttonclick(ActionEvent event) throws IOException, InterruptedException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        Image i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\one.jpg");
        gif_pic.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                e -> {
                    Image im = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\dice-gif.gif");
                    gif_pic.setImage(im);
                    gif_pic.setVisible(true);
                    Image im2 = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\down_arrow.gif");
                    down_arrow.setImage(im2);
                    down_arrow.setVisible(true);
        }));
        timeline.setCycleCount(1);
        timeline.play();
        gif_pic.setVisible(false);
        down_arrow.setVisible(false);
        Random r = new Random();
        int dice = r.nextInt(1, 7);

        switch (dice)
        {
            case 1 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\one.jpg");
                break;
            case 2 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\two.jpg");
                break;
            case 3 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\three.jpg");
                break;
            case 4 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\four.jpg");
                break;
            case 5 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\five.jpg");
                break;
            case 6 : i = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\six.jpg");
                break;
        }
        dice_image.setImage(i);
        Image finalI = i;
        timeline = new Timeline(new KeyFrame(Duration.millis(10),
                e -> {
                    dice_image.setImage(finalI);
                }));
        timeline.setCycleCount(1);
        timeline.play();
        move(dice);
    }

    public void move(int dice) throws IOException, InterruptedException {
            if(p1.isTurn()) {
                p1.setTurn(false);
                Image im = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\player1.jpg");
                player1.setImage(im);
                im = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\player2_dull.jpg");
                player2.setImage(im);
                //System.out.println("p1 : " + dice);
                if(dice == 1 && p1.isCan_move())
                {
                    a1.setX(-10);
                    a1.setY(-50);
                    p1.setP(a1);
                    p1.setCan_move(true);
                    return;
                }
                if(p1.isCan_move())
                    return;

                if(p1.getCovered_tiles()+dice > 100)
                    return;
//                for(int i = 0; i<dice; i++) {
//                    p1.getP().move();
//                    a1.setX(p1.getP().getX());
//                    a1.setY(p1.getP().getY());
//                    p1.setCovered_tiles(p1.getCovered_tiles()+1);
////                    Scene stemp = a1.getScene();
////                    stage.setScene(stemp);
////                    stage.();
////                    stage.show();
////                    Thread.sleep(100);
////                    stage.setScene(scene);
////                    stage.show();
//
//
//                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
//                            ae -> a1.setX(p1.getP().getX())));
//                    timeline.setCycleCount(1);
//                    timeline.play();
//                }
                if(p1.getCovered_tiles() + dice > 100)
                    return;
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                        e -> {
                            p1.getP().move();
                            a1.setX(p1.getP().getX());
                            a1.setY(p1.getP().getY());
                            p1.setCovered_tiles(p1.getCovered_tiles()+1);
                            //System.out.println("p1 covered : " + p1.getCovered_tiles());
                            if(p1.getP().iswin(p1)) {
                                System.out.println("WINNNN A----------------------------------");
                                winner = "Player 1";
                                FXMLLoader fxmlLoader = new FXMLLoader(FrontController.class.getResource("end-view.fxml"));
                                Scene scene = null;
                                try {
                                    scene = new Scene(fxmlLoader.load(), 740, 640);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                stage.setTitle("End-Game");
                                stage.setScene(scene);
                                stage.show();
                            }
                        }));
                timeline.setCycleCount(dice);
                timeline.play();
                for(Ladder temp : ladders)
                {
                    if(temp.getStart() == p1.getCovered_tiles())
                    {
                        p1.setCovered_tiles(temp.getEnd());
                        int jump = (temp.getEnd() - temp.getStart())/10;
                        if(temp.getStart() == 6 || temp.getStart() == 89)
                            jump++;
                        p1.getP().setRow(p1.getP().getRow()+jump);
                        p1.getP().setY(p1.getP().getY() - jump*p1.getP().getSizey());
                        a1.setX(p1.getP().getX());
                        a1.setY(p1.getP().getY());

                    }
                }
                for(Snake temp : snakes)
                {
                    if(temp.getStart() == p1.getCovered_tiles())
                    {
                        p1.setCovered_tiles(temp.getEnd());
                        int jump = (temp.getEnd() - temp.getStart())/10;
                        p1.getP().setRow(p1.getP().getRow()+jump);
                        p1.getP().setY(p1.getP().getY() - jump*p1.getP().getSizey());
                        a1.setX(p1.getP().getX());
                        a1.setY(p1.getP().getY());

                    }
                }
            }
            else
            {
                p1.setTurn(true);
                Image im = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\player1_dull.jpg");
                player1.setImage(im);
                im = new Image("C:\\Users\\yanuj\\Desktop\\SnakeAndLadder\\src\\main\\resources\\com\\example\\snakeandladder\\player2.jpg");
                player2.setImage(im);
                //System.out.println("p2 : " + dice);
                if(dice == 1 && p2.isCan_move())
                {
                    a2.setX(0);
                    a2.setY(-50);
                    p2.setP(a2);
                    p2.setCan_move(true);
                    return;
                }
                if(p2.isCan_move())
                    return;

                if(p2.getCovered_tiles()+dice > 100)
                    return;
                //for(int i = 0; i<dice; i++)
                //{
                if(p2.getCovered_tiles() + dice > 100)
                    return;
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                        e -> {
                            p2.getP().move();
                            a2.setX(p2.getP().getX());
                            a2.setY(p2.getP().getY());
                            p2.setCovered_tiles(p2.getCovered_tiles()+1);
                            //System.out.println("p2 covered : " + p2.getCovered_tiles());
                            if(p2.getP().iswin(p2)) {
                                //System.out.println("WINNNN B----------------------------------");
                                winner = "Player 2";
                                FXMLLoader fxmlLoader = new FXMLLoader(FrontController.class.getResource("end-view.fxml"));
                                Scene scene = null;
                                try {
                                    scene = new Scene(fxmlLoader.load(), 740, 640);
                                } catch (IOException ex) {
                                    //ex.printStackTrace();
                                }
                                stage.setTitle("End-Game");
                                stage.setScene(scene);
                                stage.show();

                            }
                        }));
                timeline.setCycleCount(dice);
                timeline.play();
                //}
                for(Ladder temp : ladders)
                {
                    if(temp.getStart() == p2.getCovered_tiles())
                    {
                        p2.setCovered_tiles(temp.getEnd());
                        int jump = (temp.getEnd() - temp.getStart())/10;
                        if(temp.getStart() == 6 || temp.getStart() == 89)
                            jump++;
                        p2.getP().setRow(p2.getP().getRow()+jump);
                        p2.getP().setY(p2.getP().getY() - jump*p2.getP().getSizey());
                        a2.setX(p2.getP().getX());
                        a2.setY(p2.getP().getY());
                    }
                }
                for(Snake temp : snakes)
                {
                    if(temp.getStart() == p2.getCovered_tiles())
                    {
                        //System.out.println("--------- SNAKE BITE ----------");
                        p2.setCovered_tiles(temp.getEnd());
                        int jump = (temp.getEnd() - temp.getStart())/10;
                        p2.getP().setRow(p2.getP().getRow()+jump);
                        p2.getP().setY(p2.getP().getY() - jump*p2.getP().getSizey());
                        a2.setX(p2.getP().getX());
                        a2.setY(p2.getP().getY());
                    }
                }

            }
    }
}

class Ladder{
    private int start, end;
    public Ladder(int start, int end){
        this.start = start;
        this.end = end;
    }

    public Ladder() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class Snake{
    private int start, end;
    public Snake(int start, int end){
        this.start = start;
        this.end = end;
    }

    public Snake() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class Player {
    private boolean turn;
    private boolean can_move;
    private int covered_tiles = 1;
    private piece p;

    public int getCovered_tiles() {
        return covered_tiles;
    }

    public void setCovered_tiles(int covered_tiles) {
        this.covered_tiles = covered_tiles;
    }

    public piece getP() {
        return p;
    }

    public void setP(ImageView a1) {
        p = new piece((int)a1.getX(), (int)a1.getY(), 635, 500);
    }

    public Player(ImageView a1, boolean turn){
        p = new piece((int)a1.getX(), (int)a1.getY(), 635, 500);
        this.turn = turn;
        can_move = false;
    }

    public Player(boolean turn)
    {
        this.turn = turn;
        can_move = false;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isCan_move() {
        return !can_move;
    }

    public void setCan_move(boolean can_move) {
        this.can_move = can_move;
    }
}

class piece extends board{
    private int x,y;
    public piece(int x, int y, int width, int length)
    {
        super(width, length);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(){
        if(ispossible(x,y))
        {
            if(getRow()%2 == 0)
            {
                this.x += getSizex();
            }
            if(getRow()%2 != 0)
            {
                this.x -= getSizex();
            }
        }
        else
        {
            if(getRow() == 9)
                return;
            this.y -= getSizey();
            setRow(getRow()+1);
        }
    }

}

class board extends tile{

    private final int width;
    private int length;
    private int row;
    public board(int width, int length) {
        this.width = width;
        this.length = length;
        this.row = 0;
    }
    public boolean ispossible(int x, int y)
    {
        if(row%2 == 0)
        {
            return x + getSizex() < width;
        }
        else
        {
            return x - getSizex() > -60; //change
        }
    }
    public boolean iswin(Player p)
    {
        return p.getCovered_tiles() == 100; //change
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

class tile{
    private int sizex = 10, sizey = 10;
    public tile(){
        this.sizex = 67;
        this.sizey = 40;
    }

    public int getSizex() {
        return sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }
}