package com.hackerxrank;

import java.util.Scanner;

/**
 *
 *
 */
public class OneDArrayGameSolution {

    /**
     * Answer an instance for the following arguments
     */
    public OneDArrayGameSolution() {
        super();
    }

   public static boolean canWin(int leap, int[] game) {
        
        boolean[] tempVisitedNodes = new boolean[game.length];
        return canWin(0, leap, game, tempVisitedNodes);
        
    }
    
    protected static boolean canWin(int aCurrentPosition, 
                                    int leap, 
                                    int[] game,
                                    boolean[] aVisitedNodes) {
        
        boolean tempResult = false;
        
        
        if (!tempResult && aCurrentPosition >= 0) {
            
            if (gameTerminationWin(aCurrentPosition, leap, game)) {
                
                tempResult = true;
            }
            else if (game[aCurrentPosition] == 0 && !aVisitedNodes[aCurrentPosition]) {
                

                System.out.println("Forward move by 1 from currentPosition: " + aCurrentPosition 
                                       + " nextPosition: " + (aCurrentPosition + 1));
                 tempResult = canWin(aCurrentPosition + 1, leap, game, aVisitedNodes);
                
                if (!tempResult && (leap > 0)) {
                    
                    System.out.println("Forward move by leap: " + leap + " from currentPosition: " + aCurrentPosition 
                                        + " nextPosition: " + (aCurrentPosition + leap));
                    tempResult = canWin(aCurrentPosition + leap, leap, game,aVisitedNodes);               
                }
                
                if (!tempResult) {
                    
                    System.out.println("Backward move by 1 from currentPosition: " + aCurrentPosition 
                            + " nextPosition: " + (aCurrentPosition - 1));
                    
                    aVisitedNodes[aCurrentPosition] = true;
                    tempResult = canWin(aCurrentPosition - 1, leap, game,aVisitedNodes);
                }
                
                
            }
            else {
                
                System.out.println("Blocked at current position: " + aCurrentPosition);
                aVisitedNodes[aCurrentPosition] = true;
            }
            
        }
               
            
        return tempResult;
        
    }

    /**
     * Answer whether the game has terminated with a win
     * @param aCurrentPosition
     * @param leap
     * @param game
     * @return boolean
     */
    protected static boolean gameTerminationWin(int aCurrentPosition, 
                                                int leap,
                                                int[] game) {
        
        return (game.length - aCurrentPosition) < 1 ||
                (game[aCurrentPosition] == 0) &&
                    ((game.length - aCurrentPosition < leap)
                            || (game.length - aCurrentPosition == 1));
        
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
    
    
}
