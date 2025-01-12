package Solutions.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class OptimalAccountBalancing {
    // This is an npc problem so no polynomial time solution exist
    // find cumulative balance of all users and remove zero balance users
    // settle each users balance with another user and try recursively
    public int minTransfers(int[][] transactions) {
        int[] balance = new int[12];
        for (int[] tx : transactions) {
            int a = tx[0];
            int b = tx[1];
            balance[a] += tx[2];
            balance[b] -= tx[2];
        }
        List<Integer> nonzero = new ArrayList<Integer>();
        for (int num : balance) {
            if (num != 0) {
                nonzero.add(num);
            }
        }
        int res = recursive(nonzero, 0);
        return res;
    }

    public int recursive(List<Integer> nonzero, int index) {
        int n = nonzero.size();
        if (index >= nonzero.size())
            return 0;
        if (nonzero.get(index) == 0) {
            return recursive(nonzero, index + 1);
        }
        int min = Integer.MAX_VALUE;
        int elem = nonzero.get(index);
        for (int next = index + 1; next < n; ++next) {
            int old = nonzero.get(next);
            // This is mandatory if someone already owes a lot doesnt make sense to increase
            if ((old > 0 && elem > 0) || (old < 0 && elem < 0))
                continue;
            nonzero.set(next, old + elem);
            int res = recursive(nonzero, index + 1);
            min = Math.min(res, min);
            nonzero.set(next, old);
        }
        return 1 + min;
    }
}
