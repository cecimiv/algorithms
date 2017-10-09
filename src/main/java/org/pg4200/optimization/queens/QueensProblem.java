package org.pg4200.optimization.queens;

import java.util.Objects;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class QueensProblem {

    public static boolean isCorrect(int[] positions){
        return evaluate(positions) == 0;
    }

      /*
           ----
         3|  q |
         2|q   |
         1|   q|
         0| q  |
           ----
           0123

         positions = {2,0,3,1}
     */

    /**
     *
     * @param positions, raw indices on the matrix for the given column indices,
     *                   ie, positions[0] is the raw of the queen in column 0
     * @return a positive heuristics telling how many queens are in conflict.
     *          If there is no conflict, and solution is found, return 0.
     */
    public static int evaluate(int[] positions){

        validatePositions(positions);

        int penalty = 0;

        for(int i=0; i<positions.length-1; i++){

            for(int j=i+1; j<positions.length; j++){

                int a = positions[i];
                int b = positions[j];
                int rowDiff = a - b;
                int columnDiff = j - i;

                assert columnDiff > 0;

                if(a > b){
                    assert rowDiff > 0;

                    if(rowDiff == columnDiff){
                        penalty++;
                    }

                } else {
                    assert a < b && rowDiff < 0;

                    if(-rowDiff == columnDiff){
                        penalty++;
                    }
                }
            }
        }

        return penalty;
    }

    private static void validatePositions(int[] positions){
        Objects.requireNonNull(positions);

        boolean[] presences = new boolean[positions.length];

        for(int i=0; i<positions.length; i++){
            presences[positions[i]] =  true;
        }

        for(int i=0; i<presences.length; i++){
            if(! presences[i]){
                throw new IllegalArgumentException("Missing index for " + i);
            }
        }
    }
}
