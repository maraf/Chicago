/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fel.fiserm5.javabeans;

/**
 *
 * @author Marek SMM
 */
public class AktualniHrac extends Hrac {

    private int round;

    private int inThisRound;

    private int inThisRoundPoints;

    private int[] numbers;

    private boolean[] cubes;

    public AktualniHrac() {
        round = 0;
        inThisRound = 0;
        numbers = new int[3];
        numbers[0] = numbers[1] = numbers[2] = 1;
        cubes = new boolean[3];
        cubes[0] = cubes[1] = cubes[2] = true;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return this.round;
    }

    public void setInThisRound(int inThisRound) {
        this.inThisRound = inThisRound;
    }

    public int getInThisRound() {
        return this.inThisRound;
    }

    public void setInThisRoundPoints(int inThisRoundPoints) {
        this.inThisRoundPoints = inThisRoundPoints;
    }

    public int getInThisRoundPoints() {
        return this.inThisRoundPoints;
    }

    public void setNumbers(int[] numbers) {
        this.numbers[0] = numbers[0];
        this.numbers[1] = numbers[1];
        this.numbers[2] = numbers[2];
    }

    public int[] getNumbers() {
        return this.numbers;
    }

    public void setCubes(boolean[] cubes) {
        this.cubes[0] = cubes[0];
        this.cubes[1] = cubes[1];
        this.cubes[2] = cubes[2];
    }

    public boolean[] getCubes() {
        return this.cubes;
    }
}
