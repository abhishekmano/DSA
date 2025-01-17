package Solutions.BitManipulation;

//2683. Neighboring Bitwise XOR
//https://leetcode.com/problems/neighboring-bitwise-xor
public class NeighbouringXOR {
    public boolean doesValidArrayExist(int[] derived) {
        // b0 = a0 - a1
        // b1 = a1 - a2
        // bn = an - a0
        int xor = 0;
        for (int num : derived) {
            xor ^= num;
        }
        return xor == 0;
    }
}
