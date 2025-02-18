package Solutions.HashMap.SortedMap;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

//Beautiful Problem loved it
//We have 3 self balancing TreeSet
//1825. Finding MK Average
//https://leetcode.com/problems/finding-mk-average
public class MKAverage {
    TreeMap<Integer, Integer> smallest;
    TreeMap<Integer, Integer> middle;
    TreeMap<Integer, Integer> largest;
    int sCount = 0;
    int lCount = 0;
    int M;
    int K;
    int middleSum = 0;
    int middleSize;
    int totalCount = 0;
    HashMap<Integer, Integer> indexToNum = new HashMap<>();

    public MKAverage(int m, int k) {
        // contains max at the top
        smallest = new TreeMap<Integer, Integer>((a, b) -> b - a);
        // contains min at the top
        largest = new TreeMap<Integer, Integer>();
        // contains max at the top
        middle = new TreeMap<Integer, Integer>((a, b) -> b - a);
        M = m;
        K = k;
        middleSize = 0;
    }
    // Always add the element to the smallest
    // if it is full then remove the top and add to middle
    // remove the top from middle and add to largest
    // if largest is full remove from largest and add to small

    // For Removing an element
    // if the element is in middle then remove it directly no issues
    // if its in largest remove if and move the top from middle to large if it exist
    // if element is in smallest then remove from it
    // if middle is not empty remove the smallest from middle and add to smallest
    // remove the top from largest and add to small
    public void addElement(int num) {
        totalCount++;
        indexToNum.put(totalCount, num);
        if (totalCount > M) {
            int remove = totalCount - M;
            int elem = indexToNum.get(remove);
            remove(elem);
        }
        smallest.put(num, smallest.getOrDefault(num, 0) + 1);
        sCount++;
        // if the element should be removed from smallest
        if (sCount > K) {
            int top = smallest.firstKey();
            // decrement count of top element
            decrement(smallest, top);
            sCount--;
            increment(middle, top);
            middleSize++;
            middleSum += top;
            // now move the top element from mid to large
            top = middle.firstKey();
            decrement(middle, top);
            middleSize--;
            middleSum -= top;
            increment(largest, top);
            lCount++;
            if (lCount > K) {
                top = largest.firstKey();
                decrement(largest, top);
                increment(middle, top);
                lCount--;
                middleSum += top;
                middleSize++;
            }

        }
    }

    public void decrement(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1)
            map.remove(key);
        else
            map.put(key, count - 1);
    }

    public void increment(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public void remove(int elem) {
        // if the element is in mid good for us just fricking get rid of it
        if (middle.containsKey(elem)) {
            decrement(middle, elem);
            middleSum -= elem;
            middleSize--;
        }
        // if its in largest remove and if possible move one from mid to large
        else if (largest.containsKey(elem)) {
            decrement(largest, elem);
            lCount--;
            if (middleSize > 0) {
                int top = middle.lastKey();
                decrement(middle, top);
                increment(largest, top);
                lCount++;
                middleSize--;
                middleSum -= top;
            }
        }
        // its in smallest and we are fricking screwed
        else {
            decrement(smallest, elem);
            sCount--;
            if (middleSize > 0) {
                int top = middle.firstKey();
                remove(top);
                increment(smallest, top);
                sCount++;
            } else if (lCount > 0) {
                // top element in the largest should be removed and added to smallest
                int top = largest.firstKey();
                remove(top);
                sCount++;
            }
        }
    }

    public int calculateMKAverage() {
        if (totalCount < M)
            return -1;
        double average = middleSum * 1.0 / middleSize;
        // System.out.println("*********************");
        // System.out.println(smallest);
        // System.out.println(middle);
        // System.out.println(largest);
        // System.out.println(average);
        // System.out.println("*********************");
        return (int) (average);
    }

    public static void print(List<?> list) {
        for (Object element : list) {
            System.out.print(element + ", ");
        }
        System.out.println("");
    }
}
