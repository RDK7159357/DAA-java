package internal;

import java.util.Scanner;
import java.util.Arrays;
public class mergesort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        sc.close();
        System.out.println("Unsorted arr: "+Arrays.toString(arr));
        MergeSort(arr,0,arr.length-1);
        System.out.println("Sorted arr: "+Arrays.toString(arr));
    }
    public static void MergeSort(int []arr,int left,int right){
        if(left<right){
            int mid =(left+right)/2;
            MergeSort(arr, left, mid);
            MergeSort(arr, mid +1, right);
            merge(arr,left,mid,right);
        }
    }
    public static void merge(int []arr,int left,int mid,int right){
        int n1=mid-left+1;
        int n2=right -mid;
        int []leftArray=new int[n1];
        int []rightArray=new int[n2];
        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid+1, rightArray, 0, n2);
        int i=0,j=0;
        int k=left;
        while(i<n1&&j<n2){
            if(leftArray[i]<=rightArray[j]){
                arr[k]=leftArray[i];
                i++;
            }
            else{
                arr[k]=rightArray[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            arr[k]=leftArray[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k]=rightArray[j];
            j++;
            k++;
        }
    }
}


