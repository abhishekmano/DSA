package Solutions.BitManipulation;

//2965. Find Missing and Repeated Values
//https://leetcode.com/problems/find-missing-and-repeated-values
public class FindMissingAndDuplicate {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int elem = 1;
        int aXb = 0;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                aXb ^= elem;
                aXb ^= grid[idx][idy];
                elem++;
            }
        }
        // since a and b are difference number atleast one bit in aXb will be set
        int bit = 0;
        int temp = aXb;
        while (temp != 0) {
            if ((temp & 1) == 1) {
                break;
            }
            bit++;
            temp = temp >> 1;
        }
        int setBasket = 0;
        int unSetBasket = 0;
        elem = 1;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                int number = grid[idx][idy];
                number = number >> bit;
                if ((number & 1) == 1) {
                    setBasket ^= grid[idx][idy];
                } else {
                    unSetBasket ^= grid[idx][idy];
                }
                int seq = elem;
                seq = seq >> bit;
                if ((seq & 1) == 1) {
                    setBasket ^= elem;
                } else {
                    unSetBasket ^= elem;
                }
                elem++;

            }
        }
        // at the end one of the basket will be non empty ;
        int elemA = setBasket;
        int elemB = unSetBasket;
        boolean checkA = false;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (elemA == grid[idx][idy]) {
                    checkA = true;
                }
            }
        }
        int[] result = new int[2];
        if (checkA) {
            result = new int[] { elemA, elemB };
        } else {
            result = new int[] { elemB, elemA };
        }

        return result;
    }
}
