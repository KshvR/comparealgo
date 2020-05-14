// This program helps you with the running of Quicksort program
// FOR THIS ACTIVITY:
// YOU NEED NOT ALTER THIS PROGRAM, BUT YOU CAN DO SO TO PERFORM MORE TESTS IF NEED BE.
public class Main {
    
    // print all the elements of the array named arr
	public static void print_array(int [] arr){
		for(int i=0; i < arr.length;i++){
			System.out.print(String.valueOf(arr[i])+ ", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] test = { 15, 2, 6, 6, 25, 55, 564, 74, 18, 1, 400, 901, 0 };
		print_array(test);
		System.out.println(Quicksort.is_array_sorted(test)); // should print false
        Quicksort.sort(test, 0, test.length-1);
        print_array(test);
        System.out.println(Quicksort.is_array_sorted(test)); // should print true, if sorted ascending
	}

}
