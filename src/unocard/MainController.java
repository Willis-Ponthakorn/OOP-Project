/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author willi
 */
public class MainController implements Initializable {

    private GameState inGame = new GameState();

    @FXML
    private GridPane playerGrid;
    private ImageView[] playerCard = new ImageView[22];

    @FXML
    private GridPane bot1Grid;
    private ImageView[] bot1Card = new ImageView[22];

    @FXML
    private GridPane bot2Grid;
    private ImageView[] bot2Card = new ImageView[22];

    @FXML
    private GridPane bot3Grid;
    private ImageView[] bot3Card = new ImageView[22];

    @FXML
    private Button draw;
    @FXML
    private ImageView onTableCard;
    @FXML
    private TextField bot3Value;
    @FXML
    private TextField bot2Value;
    @FXML
    private TextField bot1Value;
    @FXML
    private ImageView cardTable;
    @FXML
    private Button red;
    @FXML
    private Button yellow;
    @FXML
    private Button blue;
    @FXML
    private Button green;
    @FXML
    private Button play;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private Rectangle tableColor;

    private int position = 0;

    private Glow glow = new Glow();
    private Glow nGlow = new Glow();

    private boolean checkUNO = false;
    @FXML
    private Button unoButton;
    @FXML
    private Text win;
    @FXML
    private TextArea scrollText;
    @FXML
    private TextField loop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        win.setOpacity(0);
        red.setDisable(true);
        yellow.setDisable(true);
        blue.setDisable(true);
        green.setDisable(true);
        onTableCard.setImage(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
                + inGame.getOnTableCard().getColor() + inGame.getOnTableCard().getType() + ".png")));
        scrollText.appendText("On Table Card: " + inGame.getOnTableCard().getColor() + " " + inGame.getOnTableCard().getType() + "\n");
        glow.setLevel(1);
        nGlow.setLevel(0);
        inGame.checkOnTableCard();
        if (inGame.getTurn() != 0) {
            botPlay();
        }
        updateOnScreenPlayerCard();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
        /*for (int i = 0; i < inGame.getPlayer().size(); i++) {
            ImageView playerCardV = new ImageView(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
                    + inGame.getPlayer().get(i).getColor() + inGame.getPlayer().get(i).getType() + ".png")));
            playerCardV.setFitHeight(150);
            playerCardV.setFitWidth(100);
            playerCard[i] = playerCardV;
            bot1Card[i] = addCardBot(inGame.getBot1().get(i));
            bot2Card[i] = addCardBot(inGame.getBot2().get(i));
            bot3Card[i] = addCardBot(inGame.getBot3().get(i));

            playerGrid.add(playerCard[i], i % 11, i / 11);
            bot1Grid.add(bot1Card[i], i % 11, i / 11);
            bot2Grid.add(bot2Card[i], i % 11, i / 11);
            bot3Grid.add(bot3Card[i], i % 11, i / 11);
//            playerCard[i].setOnMouseEntered(event -> {
//                playerCardV.setEffect(glow);
//            });
//            playerCard[i].setOnMouseExited(event -> {
//                playerCardV.setEffect(nGlow);
//            });
//            playerCard[i].setOnMouseClicked(event -> {
//
//                Card x = inGame.getPlayer().get(number);
//                Card y = inGame.getOnTableCard();
//                int check = inGame.check_state(x, y);
//                if (check != 0) {
//                    inGame.setOnTableCard(x);
//                    inGame.setOnTableColor(x.getColor());
//                    int[] num = inGame.getFill();
//                    num[x.getNum_type()] = 0;
//                    inGame.setFill(num);
//                    inGame.removeCard(number);
//                    if (check == 5) {
//                        red.setDisable(false);
//                        yellow.setDisable(false);
//                        blue.setDisable(false);
//                        green.setDisable(false);
//                    }
//                    testGrid = new GridPane();
//                    for (int j = 0; j < inGame.getPlayer().size(); j++) {
//                        ImageView playerCardNew = new ImageView(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
//                                + inGame.getPlayer().get(j).getColor() + inGame.getPlayer().get(j).getType() + ".png")));
//                        playerCardV.setFitHeight(150);
//                        playerCardV.setFitWidth(100);
//                        playerCard[j] = playerCardNew;
//                        testGrid.add(playerCard[j], j % 11, j / 11);
//                        
//                    }
//                }
//            });
        }
        playerCard[position].setEffect(glow);
        bot1Value.setText(inGame.getBot1().size() + " Cards");
        bot2Value.setText(inGame.getBot2().size() + " Cards");
        bot3Value.setText(inGame.getBot3().size() + " Cards");*/
    }

    @FXML
    private void redPressed(ActionEvent event) {
        inGame.setOnTableColor("RED");
        updateOnScreenTableCard();
        scrollText.appendText("You play " + inGame.getOnTableCard().getType() + inGame.getOnTableColor() + "\n");
        red.setDisable(true);
        yellow.setDisable(true);
        blue.setDisable(true);
        green.setDisable(true);
        inGame.runTurn();
        botPlay();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void yellowPressed(ActionEvent event) {
        inGame.setOnTableColor("YELLOW");
        updateOnScreenTableCard();
        scrollText.appendText("You play " + inGame.getOnTableCard().getType() + inGame.getOnTableColor() + "\n");
        red.setDisable(true);
        yellow.setDisable(true);
        blue.setDisable(true);
        green.setDisable(true);
        inGame.runTurn();
        botPlay();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void bluePressed(ActionEvent event) {
        inGame.setOnTableColor("BLUE");
        updateOnScreenTableCard();
        scrollText.appendText("You play " + inGame.getOnTableCard().getType() + inGame.getOnTableColor() + "\n");
        red.setDisable(true);
        yellow.setDisable(true);
        blue.setDisable(true);
        green.setDisable(true);
        inGame.runTurn();
        botPlay();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void greenPressed(ActionEvent event) {
        inGame.setOnTableColor("GREEN");
        updateOnScreenTableCard();
        scrollText.appendText("You play " + inGame.getOnTableCard().getType() + inGame.getOnTableColor() + "\n");
        red.setDisable(true);
        yellow.setDisable(true);
        blue.setDisable(true);
        green.setDisable(true);
        inGame.runTurn();
        botPlay();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void goLeft(ActionEvent event) {
        playerCard[position].setEffect(nGlow);
        position--;
        if (position == -1) {
            position = inGame.getPlayer().size() - 1;
        }
        playerCard[position].setEffect(glow);
    }

    @FXML
    private void drawPressed(ActionEvent event) {
        checkUNO = false;
        scrollText.appendText("You draw\n");
        playerCard[position].setEffect(nGlow);
        position = 0;
        if (inGame.getPlayer().size() < 22) {
            inGame.draw_to(inGame.getFill(), inGame.getCard(), inGame.getPlayer(), 1);
            updateOnScreenPlayerCard();
        }
        playerCard[position].setEffect(glow);
        inGame.runTurn();
        botPlay();
        updateOnScreenBotCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void playPressed(ActionEvent event) {
        playerCard[position].setEffect(nGlow);
        int check = inGame.check_state(inGame.getPlayer().get(position), inGame.getOnTableCard());
        if (check != 0) {
            if (inGame.getPlayer().size() == 2 && !checkUNO) {
                inGame.draw_to(inGame.getFill(), inGame.getCard(), inGame.getPlayer(), 2);
            }
            inGame.setOnTableCard(inGame.getPlayer().get(position));
            inGame.setOnTableColor(inGame.getPlayer().get(position).getColor());

            int[] num = inGame.getFill();
            num[inGame.getPlayer().get(position).getNum_type()] = 0;
            inGame.setFill(num);
            inGame.getPlayer().remove(position);
            if (inGame.getWin()) {
                win.setText("You Won!!!");
                win.setOpacity(1);
                draw.setDisable(true);
                play.setDisable(true);
                left.setDisable(true);
                right.setDisable(true);
                unoButton.setDisable(true);
            }

            if (check >= 0 && check <= 4 && !inGame.getWin()) {
                scrollText.appendText("You play " + inGame.getOnTableCard().getType() + " " + inGame.getOnTableColor() + "\n");
                checkUNO = false;
                inGame.checkOnTableCard();
                inGame.runTurn();
                botPlay();
            }
            if (check == 5 || check == 6 && !inGame.getWin()) {
                inGame.checkOnTableCard();
                red.setDisable(false);
                yellow.setDisable(false);
                blue.setDisable(false);
                green.setDisable(false);
                checkUNO = false;
            }

        }

        position = 0;

        updateOnScreenPlayerCard();
        updateOnScreenTableCard();
        updateArrow();
    }

    @FXML
    private void goRight(ActionEvent event) {
        playerCard[position].setEffect(nGlow);
        position++;
        if (position == inGame.getPlayer().size()) {
            position = 0;
        }
        playerCard[position].setEffect(glow);
    }

    private void updateOnScreenTableCard() {
        onTableCard.setImage(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
                + inGame.getOnTableCard().getColor() + inGame.getOnTableCard().getType() + ".png")));
        if (inGame.getOnTableColor() == "RED") {
            tableColor.setFill(Color.RED);
        }
        if (inGame.getOnTableColor() == "YELLOW") {
            tableColor.setFill(Color.GOLD);
        }
        if (inGame.getOnTableColor() == "GREEN") {
            tableColor.setFill(Color.GREEN);
        }
        if (inGame.getOnTableColor() == "BLUE") {
            tableColor.setFill(Color.BLUE);
        }
    }

    private void updateOnScreenPlayerCard() {
        playerGrid.getChildren().clear();
        /*for (int i = 0; i < playerGrid.getChildren().size(); i++) {
            playerGrid.getChildren().remove(i);
            playerCard[i] = new ImageView();
        }*/
        for (int i = 0; i < inGame.getPlayer().size(); i++) {
            ImageView playerCardV = new ImageView(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
                    + inGame.getPlayer().get(i).getColor() + inGame.getPlayer().get(i).getType() + ".png")));
            playerCardV.setFitHeight(150);
            playerCardV.setFitWidth(100);
            playerCard[i] = playerCardV;
            playerGrid.add(playerCard[i], i % 11, i / 11);
        }
        playerCard[position].setEffect(glow);
    }

    private void updateOnScreenBotCard() {
        bot1Grid.getChildren().clear();
        bot2Grid.getChildren().clear();
        bot3Grid.getChildren().clear();
        /*for (int i = 0; i < bot1Grid.getChildren().size(); i++) {
            bot1Grid.getChildren().remove(i);
        }*/
        for (int i = 0; i < inGame.getBot1().size(); i++) {
            bot1Card[i] = addCardBot(inGame.getBot1().get(i));
            bot1Grid.add(bot1Card[i], i % 11, i / 11);
        }
        /*for (int i = 0; i < bot2Grid.getChildren().size(); i++) {
            bot2Grid.getChildren().remove(i);
        }*/
        for (int i = 0; i < inGame.getBot2().size(); i++) {
            bot2Card[i] = addCardBot(inGame.getBot2().get(i));
            bot2Grid.add(bot2Card[i], i % 11, i / 11);
        }
        /*for (int i = 0; i < bot3Grid.getChildren().size(); i++) {
            bot3Grid.getChildren().remove(i);
        }*/
        for (int i = 0; i < inGame.getBot3().size(); i++) {
            bot3Card[i] = addCardBot(inGame.getBot3().get(i));
            bot3Grid.add(bot3Card[i], i % 11, i / 11);
        }
        bot1Value.setText(inGame.getBot1().size() + " Cards");
        bot2Value.setText(inGame.getBot2().size() + " Cards");
        bot3Value.setText(inGame.getBot3().size() + " Cards");
    }

    private ImageView addCardBot(Card bot) {
        ImageView botCardV = new ImageView(new Image("/uno/backcard.png"));
        //Show Bot Card
//        ImageView botCardV = new ImageView(new Image(MainController.class.getClassLoader().getResourceAsStream("uno/"
//                + bot.getColor() + bot.getType() + ".png")));
        botCardV.setFitHeight(25);
        botCardV.setFitWidth(23);
        return botCardV;
    }

    private void botPlay() {
        while (inGame.getTurn() != 0 && !inGame.getWin()) {
            if (inGame.getTurn() == 1 && !inGame.getWin()) {
                int temp = inGame.getBot1().size();
                Card tempCard = inGame.getOnTableCard();
                inGame.setOnTableCard(inGame.ai(inGame.getBot1(), inGame.getOnTableCard()));
                if (tempCard != inGame.getOnTableCard()) {
                    scrollText.appendText("Bot1 play " + inGame.getOnTableCard().getType() + " " + inGame.getOnTableColor() + "\n");
                }
                if (inGame.getBot1().size() > temp) {
                    scrollText.appendText("Bot1 draw\n");
                }
                if (inGame.getWin()) {
                    win.setText("Bot1 Won!!!");
                    draw.setDisable(true);
                    win.setOpacity(1);
                    play.setDisable(true);
                    left.setDisable(true);
                    right.setDisable(true);
                    unoButton.setDisable(true);
                }
                inGame.checkOnTableCard();
                inGame.runTurn();
                System.out.println("Card :" + inGame.getOnTableCard().getColor());
                System.out.println("Color :" + inGame.getOnTableColor());
                updateOnScreenBotCard();
                updateOnScreenTableCard();
                updateArrow();

            }
            if (inGame.getTurn() == 2 && !inGame.getWin()) {
                int temp = inGame.getBot2().size();
                Card tempCard = inGame.getOnTableCard();
                inGame.setOnTableCard(inGame.ai(inGame.getBot2(), inGame.getOnTableCard()));
                if (tempCard != inGame.getOnTableCard()) {
                    scrollText.appendText("Bot2 play " + inGame.getOnTableCard().getType() + " " + inGame.getOnTableColor() + "\n");
                }
                if (inGame.getBot2().size() > temp) {
                    scrollText.appendText("Bot2 draw\n");
                }
                if (inGame.getWin()) {
                    win.setText("Bot2 Won!!!");
                    draw.setDisable(true);
                    win.setOpacity(1);
                    play.setDisable(true);
                    left.setDisable(true);
                    right.setDisable(true);
                    unoButton.setDisable(true);
                }
                inGame.checkOnTableCard();
                inGame.runTurn();
                System.out.println("Card :" + inGame.getOnTableCard().getColor());
                System.out.println("Color :" + inGame.getOnTableColor());
                updateOnScreenBotCard();
                updateOnScreenTableCard();
                updateArrow();

            }
            if (inGame.getTurn() == 3 && !inGame.getWin()) {
                int temp = inGame.getBot3().size();
                Card tempCard = inGame.getOnTableCard();
                inGame.setOnTableCard(inGame.ai(inGame.getBot3(), inGame.getOnTableCard()));
                if (tempCard != inGame.getOnTableCard()) {
                    scrollText.appendText("Bot3 play " + inGame.getOnTableCard().getType() + " " + inGame.getOnTableColor() + "\n");
                }
                if (inGame.getBot3().size() > temp) {
                    scrollText.appendText("Bot3 draw\n");
                }
                if (inGame.getWin()) {
                    win.setText("Bot3 Won!!!");
                    draw.setDisable(true);
                    win.setOpacity(1);
                    play.setDisable(true);
                    left.setDisable(true);
                    right.setDisable(true);
                    unoButton.setDisable(true);
                }
                inGame.checkOnTableCard();
                inGame.runTurn();
                System.out.println("Card :" + inGame.getOnTableCard().getColor());
                System.out.println("Color :" + inGame.getOnTableColor());
                updateOnScreenBotCard();
                updateOnScreenTableCard();
                updateArrow();

            }
        }
    }

    @FXML
    private void sayUno(ActionEvent event) {
        checkUNO = true;
    }

    private void updateArrow() {
        if (inGame.getCounterClockWise()) {
            loop.setText("Loop : CounterClockWise");
        } else {
            loop.setText("Loop : ClockWise");
        }
    }
}
