/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;

/**
 *
 * @author willi
 */
import java.util.ArrayList;

public class GenCard {

    public static ArrayList<Card> generate() {

        ArrayList<Card> card = new ArrayList<Card>();
        String[] color = {"RED", "YELLOW", "GREEN", "BLUE"};

        for (int i = 0; i < color.length; i++) {

            card.add(new Card(String.valueOf(0), color[i], card.size()));
            for (int j = 1; j <= 9; j++) {
                card.add(new Card(String.valueOf(j), color[i], card.size()));

                card.add(new Card(String.valueOf(j), color[i], card.size()));
            }
        }

        for (int i = 0; i < color.length; i++) {
            {
                for (int j = 0; j < 2; j++) {
                    card.add(new Card("SKIP", color[i], card.size()));
                    card.add(new Card("REVERSE", color[i], card.size()));
                    card.add(new Card("DRAW_TWO", color[i], card.size()));

                }
            }
        }

        for (int i = 0; i < 4; i++) {
            card.add(new Card("WILD_COLOR", "NULL", card.size()));
            card.add(new Card("WILD_DRAW_FOUR", "NULL", card.size()));
        }
        
        

        return card;
    }

}
