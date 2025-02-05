package week_1;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
class implicit_rec {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // Function call
        int secondLargest = findSecondLargest(numbers);
        System.out.println(secondLargest);
    }
    public static int findLargest(List<Integer> numbers) {
        int max = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > max) {
                max = numbers.get(i);
            }
        }
        return max;
    }
    public static int findSecondLargest(List<Integer> numbers) {
        int largest = findLargest(numbers);
        ArrayList<Integer> temp = new ArrayList<>(numbers);
        temp.remove(Integer.valueOf(largest));
        return findLargest(temp);

    }
}