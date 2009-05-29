/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fel.fiserm5.tools;

import cz.cvut.fel.fiserm5.javabeans.Hrac;
import java.util.Comparator;

/**
 *
 * @author Marek SMM
 */
public class HracComparator implements Comparator {
    
    public static int ASCENDING = 101;
    
    public static int DESCENDING = 102;

    private int order;

    public HracComparator(int order) {
        if(order == HracComparator.ASCENDING) {
            this.order = HracComparator.ASCENDING;
        } else {
            this.order = HracComparator.DESCENDING;
        }
    }

    public int compare(Object o1, Object o2) {
        Hrac h1 = (Hrac) o1;
        Hrac h2 = (Hrac) o2;

        if(h1.getScore() > h2.getScore()) {
            if(this.order == HracComparator.ASCENDING)
                return 1;
            else
                return 0;
        } else {
            if(this.order == HracComparator.ASCENDING)
                return 0;
            else
                return 1;
        }
    }

}
