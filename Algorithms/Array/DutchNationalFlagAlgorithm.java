package Algorithms.Array;

//75. Sort Colors
//https://leetcode.com/problems/sort-colors/
//To sort an array with 0 1 and 2
//Edsger W. Dijkstra
public class DutchNationalFlagAlgorithm {
    public void SortColors(int[] arr) {
        int n = arr.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                Swap(arr, mid, low);
                mid++;
                low++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                Swap(arr, mid, high);
                high--;
            }
        }
    }

    public void Swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
