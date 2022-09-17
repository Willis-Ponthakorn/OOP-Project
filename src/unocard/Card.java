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
public class Card {

    String color;
    String type;
    int num_type;

    public Card() {

        this.color = "";
        this.type = "";
        this.num_type = -1;

    }

    public int getNum_type() {
        return num_type;
    }

    public void setNum_type(int nun_type) {
        this.num_type = nun_type;
    }

    public Card( String type,String color, int num_type) {
        this.color = color;
        this.type = type;
        this.num_type = num_type;
    }

    public Card(String type, String color) {
        this.color = color;
        this.type = type;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
