package internal;

import java.util.Arrays;
import java.util.Scanner;
public class quicksort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        sc.close();
        System.out.println("Unsorted arr: "+Arrays.toString(arr));
        Quicksort(arr,0,arr.length-1);
        System.out.println("Sorted arr: "+Arrays.toString(arr));
    }
    public static void Quicksort(int arr[], int low, int high) {
        // If the subarray has more than one element, sort it
        if (low < high) {
            // Partition the array around a pivot element
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort the left subarray
            Quicksort(arr, low, pivotIndex - 1);
            
            // Recursively sort the right subarray
            Quicksort(arr, pivotIndex + 1, high);
        }
    }
    
    public static int partition(int arr[], int low, int high) {
        // Choose the pivot element as the first element of the subarray
        int pivot = arr[low];
        // Initialize two pointers, i and j, to partition the array
        int i = low + 1; // Start from the next element after the pivot
        int j = high;
        // Partition the array around the pivot element
        while (i <= j) {
            // Move i to the right until we find an element greater than the pivot
            while (i <= j && arr[i] <= pivot) i++;
            
            // Move j to the left until we find an element less than or equal to the pivot
            while (i <= j && arr[j] > pivot) j--;
            
            // If i and j have not crossed each other, swap the elements at i and j
            if (i <= j) swap(arr, i, j);
        }
        System.out.println(Arrays.toString(arr));
        // Swap the pivot element with the element at j
        swap(arr, low, j);
        System.out.println(Arrays.toString(arr));
        // Return the index of the pivot element
        return j;
    } 
    public static void swap(int arr[], int i, int j) {
        // Swap the elements at indices i and j
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
