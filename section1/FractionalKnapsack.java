package section1;

import java.util.*;

public class FractionalKnapsack {
    static class Item {
        int weight, value;

        Item(int w, int v) {
            weight = w;
            value = v;
        }
    }

    static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(
                (double) b.value / b.weight, (double) a.value / a.weight));

        double total = 0;
        List<String> includedItems = new ArrayList<>();

        for (Item i : items) {
            if (capacity >= i.weight) {
                capacity -= i.weight;
                total += i.value;
                includedItems.add("Full item: weight=" + i.weight + ", value=" + i.value);
            } else {
                double fraction = (double) capacity / i.weight;
                total += i.value * fraction;
                includedItems.add("Fractional item (" + String.format("%.2f", fraction * 100) + "%): weight=" + i.weight
                        + ", value=" + i.value);
                break;
            }
        }
        System.out.println("Items included in knapsack:");
        for (String item : includedItems) {
            System.out.println(item);
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
