package section1;

import java.util.*;

public class FractionalKnapsack {
    static class Item {
        int weight, value;
        Item(int w, int v) { weight = w; value = v; }
    }

    static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(
            (double)b.value / b.weight, (double)a.value / a.weight));

        double total = 0;
        for (Item i : items) {
            if (capacity >= i.weight) {
                capacity -= i.weight;
                total += i.value;
            } else {
                total += (double)i.value * capacity / i.weight;
                break;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };
        int capacity = 50;
        System.out.println("Max value: " + getMaxValue(items, capacity));
    }
}

