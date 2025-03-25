package Solutions.Heap;

import java.util.TreeSet;

//https://leetcode.com/problems/max-stack/
//716. Max Stack
class MaxStack {
    TreeSet<Element> stack;
    TreeSet<Element> max;
    int id = 0;

    public MaxStack() {
        stack = new TreeSet<>((a, b) -> b.id - a.id);
        max = new TreeSet<>((a, b) -> b.value == a.value ? b.id - a.id : b.value - a.value);
    }

    public void push(int x) {
        Element elem = new Element(id, x);
        id++;
        stack.add(elem);
        max.add(elem);
    }

    public int pop() {
        Element top = stack.removeFirst();
        max.remove(top);
        return top.value;
    }

    public int top() {
        return stack.getFirst().value;
    }

    public int peekMax() {
        return max.getFirst().value;
    }

    public int popMax() {
        Element top = max.removeFirst();
        stack.remove(top);
        return top.value;
    }

    public record Element(int id, int value) {
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
