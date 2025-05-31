package section1;

public class HeapSort {

    // Method to sort an array using Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build a max heap from the array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root (largest) to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[].
    // n is size of heap.
    public static void heapify(int[] arr, int n, int i) {
        int largest = i;      // Initialize largest as root
        int left = 2 * i + 1;   // left child index
        int right = 2 * i + 2;  // right child index

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.print("Input array: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        heapSort(arr);

        System.out.print("Sorted array is: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
