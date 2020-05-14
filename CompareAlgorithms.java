import java.util.*;

public class CompareAlgorithms {

    public class QuickSort {

        public void swap(int array[], int i, int j) {

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

        }

        public int partition(int[] array, int lower_bound, int upper_bound){

            int i = lower_bound, j = upper_bound;
            int pivot = array[(lower_bound + (upper_bound-lower_bound)/2)];

            while (i <= j) {

                while (array[i] < pivot) {
                    i++;
                }

                while (array[j] > pivot) {
                    j--;
                }

                if (i <= j) {

                    swap(array, i, j);
                    i++;
                    j--;

                }
            }

            return i;
        }

        public void quickSort(int[] array, int lower_bound, int upper_bound) {

            if(lower_bound < upper_bound){
                int i = partition(array, lower_bound, upper_bound);

                if (lower_bound < i){
                    quickSort(array, lower_bound, i-1);
                }

                if (i < upper_bound){
                    quickSort(array, i, upper_bound);
                }

            }
        }

    }

    public class HeapSort {

        public void heapSort(int[] array){

            int count = array.length;
            heapify(array, count);
            int upper_bound = count - 1;

            while(upper_bound > 0){
                
                int temp = array[upper_bound];
                array[upper_bound] = array[0];
                array[0] = temp;
               
                downHeap(array, 0, upper_bound - 1);
               
                upper_bound--;
            }
        }

        public void heapify(int[] array, int count){
            
            int lower_bound = (count - 2) / 2;

            while(lower_bound >= 0){
                
                downHeap(array, lower_bound, count - 1);
                lower_bound--;

            }
        
        }
        public void downHeap(int[] array, int lower_bound, int upper_bound){

            int minimum = lower_bound;

            while((minimum * 2 + 1) <= upper_bound){    

                int child = minimum * 2 + 1; 

                if(child + 1 <= upper_bound && array[child] < array[child + 1])
                    child = child + 1; 

                if(array[minimum] < array[child]){ 

                    int temp = array[minimum];
                    array[minimum] = array[child];
                    array[child] = temp;
                    minimum = child; 

                }else
                    return;
            }

    }
    }

    public static class RadixSort{

        public static void radixSort(int[] array) {
            RadixSort.radixSort(array, 10);
        }

        public static void radixSort(int[] array, int base) {
            if (array.length == 0) {
                return;
            }

            int lower_bound = array[0];
            int upper_bound = array[0];

            for (int i = 1; i < array.length; i++) {

                if (array[i] < lower_bound) {
                    lower_bound = array[i];
                }
                else if (array[i] > upper_bound) {
                    upper_bound = array[i];
                }

            }

            int power = 1;
            while ((upper_bound - lower_bound) / power >= 1) {

                RadixSort.sortByDigitFrequency(array, base, power, lower_bound);
                power *= base;

            }
        }

        private static void sortByDigitFrequency(

            int[] array, int base, int power, int lower_bound) {

            int containerIndex;
            int[] containers = new int[base];
            int[] result = new int[array.length];

            for (int i = 0; i < base; i++) {
                containers[i] = 0;
            }

            for (int i = 0; i < array.length; i++) {

                containerIndex = (int)(((array[i] - lower_bound) / power) % base);
                containers[containerIndex]++;

            }

            for (int i = 1; i < base; i++) {
                containers[i] += containers[i - 1];
            }

            for (int i = array.length - 1; i >= 0; i--) {

                containerIndex = (int)(((array[i] - lower_bound) / power) % base);
                result[--containers[containerIndex]] = array[i];

            }

            for (int i = 0; i < array.length; i++) {
                array[i] = result[i];
            }

        }
    }

    public static boolean isArraySorted (int [] array) {

        for(int i=0; i < (array.length - 2) ; i++){

            if(array[i]>array[i+1]){
                return false;
            }

        }
        return true;

    }

    public static void main(String[] args) {

        int i,s,n,t;
        Random rannum = new Random();

        double qTime = 0, hTime = 0, rTime = 0;
        int[] Size = {10, 50, 100, 500, 1000};
        n = 4; // n is the number of iterations for averaging

        double[] qTimes = new double[n];
        double[] hTimes = new double[n];
        double[] rTimes = new double[n];


        for(s=0; s<Size.length; s++){

            int[] array = new int[Size[s]];
            int[] arrayQ = new int[Size[s]];
            int[] arrayH = new int[Size[s]];
            int[] arrayR = new int[Size[s]];

            for(t=0; t<n; t++){

                for(i=0; i<arrayQ.length; i++){

                    array[i] = rannum.nextInt(10000);
                    arrayQ[i] = array[i];
                    arrayH[i] = array[i];
                    arrayR[i] = array[i];

                }

                //for(int p: array){System.out.println(p);};

                CompareAlgorithms CA = new CompareAlgorithms();
                QuickSort QS = CA.new QuickSort();
                HeapSort HS = CA.new HeapSort();
                RadixSort RS = new RadixSort();

                long qStartTime = System.nanoTime();
                QS.quickSort(arrayQ ,0,arrayQ.length-1);
                long qStopTime = System.nanoTime();

                long hStartTime = System.nanoTime();
                HS.heapSort(arrayH);
                long hStopTime = System.nanoTime();

                long rStartTime = System.nanoTime();
                RS.radixSort(arrayR);
                long rStopTime = System.nanoTime();

                qTimes[t] = qStopTime - qStartTime;
                hTimes[t] = hStopTime - hStartTime;
                rTimes[t] = rStopTime - rStartTime;

            }
            for(int a=0;a<n;a++){

                qTime += qTimes[a];
                hTime += hTimes[a];
                rTime += rTimes[a];

            }

            qTime /= n;
            hTime /= n;
            rTime /= n;
            if(isArraySorted(arrayQ)==true){
                System.out.println("Time to QuickSort an array of size "+ arrayQ.length + " is " + qTime/1000 + " microseconds" );
            }
            //for(int p: arrayQ){System.out.println(p);};
            if(isArraySorted(arrayH)==true){
                System.out.println("Time to Heap sort an array of size "+ arrayQ.length + " is " + hTime/1000 + " microseconds" );
            }
            //for(int p: arrayH){System.out.println(p);};
            if(isArraySorted(arrayQ)==true){
                System.out.println("Time to Radix sort an array of size "+ arrayQ.length + " is " + rTime/1000 + " microseconds" );
            }
            //for(int p: arrayR){System.out.println(p);};

        }

    }
}