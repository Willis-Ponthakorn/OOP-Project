/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;

import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class GameState {

    private final ArrayList<Card> card = GenCard.generate();
    private int[] fill = new int[109];
    private ArrayList<Card> player = new ArrayList<Card>();
    private ArrayList<Card> bot1 = new ArrayList<Card>();
    private ArrayList<Card> bot2 = new ArrayList<Card>();
    private ArrayList<Card> bot3 = new ArrayList<Card>();
    private Card onTableCard = new Card();
    private String onTableColor;
    private boolean counterClockWise;
    private int turn;

    public GameState() {
        this.turn = 0;
        draw_to(fill, card, player, 7);
        draw_to(fill, card, bot1, 7);
        draw_to(fill, card, bot2, 7);
        draw_to(fill, card, bot3, 7);
        onTableCard = card.get(rand_state(fill));
        onTableColor = onTableCard.getColor();
        counterClockWise = true;
    }

    public Card getOnTableCard() {
        return onTableCard;
    }

    private int _rand(int fill[]) {
        int x = (int) (Math.random() * 108);
        while (fill[x] == 1) {
            x = (int) (Math.random() * 108);
        }
        fill[x] = 1;
        return x;
    }

    public int rand_state(int fill[]) {
        int x = (int) (Math.random() * 108);
        while (fill[x] == 1 || (x >= 100 && x < 108)) {
            x = (int) (Math.random() * 108);
        }
        fill[x] = 1;
        return x;

    }

    public int check_state(Card x, Card y) {
        if ("SKIP" == (x.type) && (x.color == onTableColor || x.type == y.type)) {
            return 2;
        }

        if ("REVERSE" == (x.type) && (x.color == onTableColor || x.type == y.type)) {
            return 3;
        }
        if ("DRAW_TWO" == (x.type) && (x.color == onTableColor || x.type == y.type)) {
            return 4;
        }
        if ("WILD_COLOR" == (x.type)) {
            return 5;
        }
        if ("WILD_DRAW_FOUR" == (x.type)) {
            return 6;
        }
        if (x.type.length() == 1 && (Integer.parseInt(x.type) >= 0) && (Integer.parseInt(x.type) <= 9)) {

            if (x.color == onTableColor) {
                return 1;
            }

            if (y.type.length() == 1 && (Integer.parseInt(x.type) == Integer.parseInt(y.type))) {
                return 1;
            }

        }

        return 0;
    }

    public int start_state(int fill[], ArrayList<Card> card) {

        int id = rand_state(fill);
        fill[id] = 1;

        return id;
    }

    public void show_state(int id, ArrayList<Card> card) {
        System.out.println(card.get(id).type + " " + card.get(id).color);

    }

    public void on_hand(ArrayList<Card> card) {

        for (int i = 0; i < card.size(); i++) {
            System.out.print(card.get(i).type + ":" + card.get(i).color + ":" + card.get(i).num_type + " ");

        }
        System.out.print("\n");

    }

    public ArrayList<Card> draw_to(int fill[], ArrayList<Card> card, ArrayList<Card> x, int n) {
        for (int i = 0; i < n; i++) {
            x.add(card.get(_rand(fill)));
        }
        return x;
    }

    public Card ai(ArrayList<Card> bot, Card ontable_now) {

        for (int i = 0; i < bot.size(); i++) {

            if ("SKIP" == (bot.get(i).type) && (bot.get(i).color == onTableColor || bot.get(i).type == ontable_now.type)) {

                Card temp = bot.get(i);
                onTableColor = bot.get(i).getColor();
                System.out.println("Bot play " + bot.get(i).type + " " + bot.get(i).color);
                bot.remove(i);

                return temp;
            }

            if ("REVERSE" == (bot.get(i).type) && (bot.get(i).color == onTableColor || bot.get(i).type == ontable_now.type)) {
                Card temp = bot.get(i);
                onTableColor = bot.get(i).getColor();
                System.out.println("Bot play " + bot.get(i).type + " " + bot.get(i).color);
                bot.remove(i);

                return temp;
            }
            if ("DRAW_TWO" == (bot.get(i).type) && (bot.get(i).color == onTableColor || bot.get(i).type == ontable_now.type)) {
                Card temp = bot.get(i);
                onTableColor = bot.get(i).getColor();
                System.out.println("Bot play " + bot.get(i).type + " " + bot.get(i).color);
                bot.remove(i);

                return temp;
            }

            if (bot.get(i).type.length() == 1 && (Integer.parseInt(bot.get(i).type) >= 0) && (Integer.parseInt(bot.get(i).type) <= 9)) {

                if (bot.get(i).color == onTableColor) {
                    Card temp = bot.get(i);
                    onTableColor = bot.get(i).getColor();
                    System.out.println("Bot play " + bot.get(i).type + " " + bot.get(i).color);
                    bot.remove(i);

                    return temp;

                }
                if (ontable_now.type.length() == 1 && (Integer.parseInt(bot.get(i).type) == Integer.parseInt(ontable_now.type))) {
                    Card temp = bot.get(i);
                    onTableColor = bot.get(i).getColor();
                    System.out.println("Bot play " + bot.get(i).type + " " + bot.get(i).color);
                    bot.remove(i);

                    return temp;
                }
            }
        }
        int nWDF = 0;
        int lWDF = 0;
        for (int i = 0; i < bot.size(); i++) {

            if (bot.get(i).type == "WILD_COLOR" || bot.get(i).type == "WILD_DRAW_FOUR") {
                nWDF++;
                lWDF = i;
            }

        }
        if (nWDF != 0) {
            int[] bot_color = new int[4]; // red green blue yellow
            int i;
            String[] color = {"RED", "YELLOW", "GREEN", "BLUE"};
            for (i = 0; i < bot.size(); i++) {
                if (bot.get(i).color == "RED") {
                    bot_color[0]++;
                }
                if (bot.get(i).color == "YELLOW") {
                    bot_color[1]++;
                }
                if (bot.get(i).color == "GREEN") {
                    bot_color[2]++;
                }
                if (bot.get(i).color == "BLUE") {
                    bot_color[3]++;
                }

            }

            ArrayList<String> str_color = new ArrayList<String>();
            for (int j = 0; j < 4; j++) {
                while (bot_color[j] > 0) {
                    bot_color[j]--;
                    str_color.add(color[j]);
                }
            }
            Card rt = bot.get(lWDF);

            bot.remove(lWDF);
            onTableColor = str_color.get((int) Math.random() * str_color.size());
            System.out.println("Bot play Wild");
            return rt;

        }
        draw_to(fill, card, bot, 1);
        System.out.println("Bot draw");

        return ontable_now;

    }
    
    public void botPlay(){
        while (turn != 0) {
            if (turn == 1) {
                onTableCard = ai(bot1, onTableCard);
                checkOnTableCard();
            }
            if (turn == 2) {
                onTableCard = ai(bot2, onTableCard);
                checkOnTableCard();
            }
            if (turn == 3) {
                onTableCard = ai(bot3, onTableCard);
                checkOnTableCard();
            }
            if(counterClockWise){
                turn++;
                if(turn > 3)
                    turn -= 4;
            }
            if(!counterClockWise){
                turn--;
                if(turn < 0)
                    turn += 4;
            }
        }
    }
    
    public void checkOnTableCard(){
        if(getOnTableCard().getType() == "SKIP"){
            if(counterClockWise){
                turn++;
                if(turn > 3)
                    turn -= 4;
            }
            if(!counterClockWise){
                turn--;
                if(turn < 0)
                    turn += 4;
            }
        }
        if(getOnTableCard().getType() == "REVERSE"){
            if(counterClockWise == true){
                counterClockWise = false;
            }
            else if(counterClockWise == false){
                counterClockWise = true;
            }
        }
        if(getOnTableCard().getType() == "DRAW_TWO"){
            if(counterClockWise){
                if(turn==0)
                    draw_to(fill, card, bot1, 2);
                if(turn==1)
                    draw_to(fill, card, bot2, 2);
                if(turn==2)
                    draw_to(fill, card, bot3, 2);
                if(turn==3)
                    draw_to(fill, card, player, 2);
                turn++;
                if(turn > 3)
                    turn -= 4;
            }
            if(!counterClockWise){
                if(turn==0)
                    draw_to(fill, card, bot3, 2);
                if(turn==1)
                    draw_to(fill, card, player, 2);
                if(turn==2)
                    draw_to(fill, card, bot1, 2);
                if(turn==3)
                    draw_to(fill, card, bot2, 2);
                turn--;
                if(turn < 0)
                    turn += 4;
            }
        }
        if(getOnTableCard().getType() == "WILD_DRAW_FOUR"){
            if(counterClockWise){
                if(turn==0)
                    draw_to(fill, card, bot1, 4);
                if(turn==1)
                    draw_to(fill, card, bot2, 4);
                if(turn==2)
                    draw_to(fill, card, bot3, 4);
                if(turn==3)
                    draw_to(fill, card, player, 4);
                turn++;
                if(turn > 3)
                    turn -= 4;
            }
            if(!counterClockWise){
                if(turn==0)
                    draw_to(fill, card, bot3, 4);
                if(turn==1)
                    draw_to(fill, card, player, 4);
                if(turn==2)
                    draw_to(fill, card, bot1, 4);
                if(turn==3)
                    draw_to(fill, card, bot2, 4);
                turn--;
                if(turn < 0)
                    turn += 4;
            }
        }
    }

    public int[] getFill() {
        return this.fill;
    }

    public ArrayList<Card> getCard() {
        return this.card;
    }

    public ArrayList<Card> getPlayer() {
        return player;
    }

    public ArrayList<Card> getBot1() {
        return bot1;
    }

    public ArrayList<Card> getBot2() {
        return bot2;
    }

    public ArrayList<Card> getBot3() {
        return bot3;
    }

    public void setFill(int[] fill) {
        this.fill = fill;
    }

    public void setPlayer(ArrayList<Card> player) {
        this.player = player;
    }

    public void setBot1(ArrayList<Card> bot1) {
        this.bot1 = bot1;
    }

    public void setBot2(ArrayList<Card> bot2) {
        this.bot2 = bot2;
    }

    public void setBot3(ArrayList<Card> bot3) {
        this.bot3 = bot3;
    }

    public void setOnTableCard(Card onTableCard) {
        this.onTableCard = onTableCard;
    }

    public String getOnTableColor() {
        return onTableColor;
    }

    public void setOnTableColor(String onTableColor) {
        this.onTableColor = onTableColor;
    }

    public void removeCard(int x) {
        getPlayer().remove(x);
    }

    public boolean getCounterClockWise() {
        return counterClockWise;
    }

    public void setCounterClockWise(boolean counterClockWise) {
        this.counterClockWise = counterClockWise;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public void runTurn(){
        if(counterClockWise){
                turn++;
                if(turn > 3)
                    turn -= 4;
            }
            if(!counterClockWise){
                turn--;
                if(turn < 0)
                    turn += 4;
            }
    }
    
    public boolean getWin(){
        return player.isEmpty() || bot1.isEmpty() || bot2.isEmpty() || bot3.isEmpty();
    }
    
    
}
