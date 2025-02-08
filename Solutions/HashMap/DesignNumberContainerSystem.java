package Solutions.HashMap;

import java.util.HashMap;
import java.util.TreeSet;

//https://leetcode.com/problems/design-a-number-container-system
//2349. Design a Number Container System
public class DesignNumberContainerSystem {
    class NumberContainers {
        HashMap<Integer, TreeSet<Integer>> numberToIndices;
        HashMap<Integer, Integer> indexToNumber;

        public NumberContainers() {
            numberToIndices = new HashMap<>();
            indexToNumber = new HashMap();
        }

        public void change(int index, int number) {
            // Replace
            if (indexToNumber.containsKey(index)) {
                int oldNumber = indexToNumber.get(index);
                numberToIndices.get(oldNumber).remove(index);

            }
            indexToNumber.put(index, number);
            numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
        }

        public int find(int number) {
            if (!numberToIndices.containsKey(number) || numberToIndices.get(number).isEmpty())
                return -1;
            return numberToIndices.get(number).first();
        }
    }

    /**
     * Your NumberContainers object will be instantiated and called as such:
     * NumberContainers obj = new NumberContainers();
     * obj.change(index,number);
     * int param_2 = obj.find(number);
     */
}
