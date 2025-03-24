package week1;

import java.util.Random;
import java.util.Arrays;

public class ShuffleDeck {
    public static void main(String[] args) {
        int[] deck = new int[52];
        
        // Step 1: Fill the array with ordered values
        for (int i = 0; i < 52; i++) {
            deck[i] = i;
        }

        shuffle(deck);  // Shuffle the deck

        // Print the shuffled deck
        System.out.println(Arrays.toString(deck));
    }

    public static void shuffle(int[] deck) {
        Random rand = new Random();

        // Step 2: Swap each element with a random index
        for (int i = 0; i < deck.length; i++) {
            int swapIdx = i + rand.nextInt(deck.length - i);
            int temp = deck[i];
            deck[i] = deck[swapIdx];
            deck[swapIdx] = temp;
        }
    }
}

