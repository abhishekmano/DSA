package Solutions.BitManipulation;

//2429. Minimize XOR
//https://leetcode.com/problems/minimize-xor
public class MinimizeXOR {
    public int minimizeXor(int num1, int num2) {
        int setBits = 0;
        while (num2 != 0) {
            setBits += (num2 & 1);
            num2 = num2 >> 1;
        }
        int optimal = num1 ^ 0;
        int oneCount = 0;
        int temp = optimal;
        while (temp != 0) {
            oneCount += temp & 1;
            temp = temp >> 1;
        }
        int oneToRemove = oneCount - setBits;
        for (int idx = 0; idx < Math.abs(oneToRemove); ++idx) {
            if (oneToRemove > 0)
                optimal = optimal & (optimal - 1);
            else
                optimal = optimal | (optimal + 1);
        }
        return optimal;
    }
}
