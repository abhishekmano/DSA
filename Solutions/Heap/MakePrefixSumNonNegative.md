You are given a 0-indexed integer array nums. You can apply the following operation any number of times:

Pick any element from nums and put it at the end of nums.
The prefix sum array of nums is an array prefix of the same length as nums such that prefix[i] is the sum of all the integers nums[j] where j is in the inclusive range [0, i].

Return the minimum number of operations such that the prefix sum array does not contain negative integers. The test cases are generated such that it is always possible to make the prefix sum array non-negative.
