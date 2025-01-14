package Solutions.Heap;

import java.util.PriorityQueue;

//295. Find Median from Data Stream
//https://leetcode.com/problems/find-median-from-data-stream
public class MedianOfSortedStream {
    class MedianFinder {
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;
        boolean isOdd = false;

        public MedianFinder() {
            min = new PriorityQueue<Integer>((a, b) -> b - a);
            max = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {
            isOdd = !isOdd;
            if (isOdd) {
                max.add(num);
                min.add(max.remove());
            } else {
                min.add(num);
                max.add(min.remove());
            }
        }

        public double findMedian() {
            if (!isOdd) {
                double sum = (double) min.peek() + max.peek();
                return sum / 2.0;
            }
            return (double) min.peek();
        }
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
