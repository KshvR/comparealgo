// 
// While implementing the quicksort algorithm,
// where the partitioning uses the value of the middle element of the array as pivot,
// a novice programmer left out mistake(s).
//
// You are required to identify any mistake(s), correct them and submit your code.
// 
public class Quicksort {
    
    // is_array_sorted takes in an array and checks whether the array sorted is sorted
    // if the array is sorted in ascending, it returns true. Otherwise it returns false.
    public static boolean is_array_sorted(int [] arr){
        for(int i=0; i < (arr.length - 2) ; i++){ 
            if(arr[i]>arr[i+1]){
                return false; // only false was being returned and now false is being returned anly after checking if the array is sorted that is each element is smaller than the next one in the array
            }
        }
        return true;//now returns true if the array is sorted
    }
    
    // Swap array elements at position i with j and vice versa.
    static void swap(int arr[], int i, int j) {
        int temp = arr[i]; // changed j to i as the element at position i and j were being set to the same value; they were not being swapped
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // The chosen pivot is the middle element of the array for partitioning.
    static int partition(int[] arr, int low, int high){
        int i = low, j = high;
        int pivot = arr[(low + (high-low)/2)];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i; 
    }
    
    // recursively partitioning the array and sorting the left and right part
    static void sort(int[] arr, int low, int high) {
        if(low < high){
            int i = partition(arr, low, high);
            if (low < i)
                sort(arr, low, i-1);
            if (i < high)
                sort(arr, i, high); //i+1 is incorrect as i also must be included in the sorting
        }       
    }
    

}
