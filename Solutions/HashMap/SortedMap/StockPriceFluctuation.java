package Solutions.HashMap.SortedMap;

import java.util.HashMap;
import java.util.TreeMap;

//2034. Stock Price Fluctuation 
//https://leetcode.com/problems/stock-price-fluctuation

public class StockPriceFluctuation {
    class StockPrice {
        // we store the count of each price so that we get max and min
        // if count becomes zero remove it
        TreeMap<Integer, Integer> stockPrice;
        final HashMap<Integer, Integer> timeToPrice;
        int latestTime = 0;

        public StockPrice() {
            timeToPrice = new HashMap<Integer, Integer>();
            stockPrice = new TreeMap<Integer, Integer>();
        }

        public void update(int timestamp, int price) {
            if (timeToPrice.containsKey(timestamp)) {
                int oldPrice = timeToPrice.get(timestamp);
                timeToPrice.remove(timestamp);
                stockPrice.put(oldPrice, stockPrice.get(oldPrice) - 1);
                if (stockPrice.get(oldPrice) == 0) {
                    stockPrice.remove(oldPrice);
                }
            }
            timeToPrice.put(timestamp, price);
            stockPrice.put(price, stockPrice.getOrDefault(price, 0) + 1);
            latestTime = Math.max(latestTime, timestamp);
        }

        public int current() {
            return timeToPrice.get(latestTime);
        }

        public int maximum() {
            return stockPrice.lastKey();
        }

        public int minimum() {
            return stockPrice.firstKey();
        }
    }

    /**
     * Your StockPrice object will be instantiated and called as such:
     * StockPrice obj = new StockPrice();
     * obj.update(timestamp,price);
     * int param_2 = obj.current();
     * int param_3 = obj.maximum();
     * int param_4 = obj.minimum();
     */
}
