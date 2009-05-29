/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fel.fiserm5.javabeans;

import java.io.Serializable;

/**
 *
 * @author Marek SMM
 */
public class Hrac implements Serializable {

    private String name;

    private int score;

    public Hrac() {
        this.name = "";
        this.score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
