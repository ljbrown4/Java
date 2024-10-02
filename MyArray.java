/* File: MyArrays
 *
 * Author:  CS112 
 *
 * Purpose: To create a class that allows you to
 * manipulate an array of integers.
 */

//import java.util.Arrays;
import java.util.Scanner;               

public class MyArray  {

    // the sentinel value used to indicate end of input, initialized to -999
    private int SENTINEL = -999;
    // the default size of the array if one is not specified, initialized to 20
    private int DEFAULT_SIZE = 20;
    // the lower bound of the range of integer elements, initialized to 10
    private int LOWER = 10;
    // the upper bound of the range of integer elements, initialized to 50
    private int UPPER = 50;
    // a data member to reference an array of integers
    int [] arr;
    // a data member to represent the number of elements entered into the array
    int numElements;

    int sum = 0;
    int min = 0;
    int max = 0;
    double avg = 0;

// CONSTRUCTORS
    // Initializes a MyArray object using default members
    public MyArray() {
       arr = new int[DEFAULT_SIZE];
       numElements = 0;
       sum = 0;
       min = 0;
       max = 0;
       avg = 0;
    }
    // initializes a MyArray object 
    public MyArray(int n) {
        int[] newArr = new int[n];
        //numElements += n;
        this.arr = newArr;
        sum = 0;
        min = 0;
        max = 0;
        avg = 0;
    }
    // initializes a MyArray object 
    public MyArray(int [] arr) {
        int [] newArr = new int[arr.length];
        int newElement = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (arr[i] >= LOWER && arr[i] <= UPPER) {
                newArr[newElement] = arr[i];
                numElements += 1;
                newElement += 1;
            }
        }
        this.arr = newArr;
        computeStatistics();
    }
    //prompts the user to enter integer elements and stores them in the object's array (protects against invalid input)
    public void inputElements() {
        Scanner scan = new Scanner(System.in);
        boolean inputMore = true; 
        System.out.print("Enter up to `" + DEFAULT_SIZE + "` integers between `" + LOWER + "` and `" + UPPER + " inclusive. Enter " + SENTINEL + "` to end user input: ");
        while (inputMore) {
            int input = scan.nextInt();
            if (input == SENTINEL) {
                break; 
            } else if (validInput(input)) {
                this.arr[numElements] = input;
                numElements += 1;
            }
            }
        computeStatistics(); 
        scan.close();
        }
    // determines whether input is valid or not (within the lower and upper bounds)
    public boolean validInput( int num ) {
        if ((numElements >= this.arr.length) || (num > UPPER) || (num < LOWER)) {
            return false;
        }
        return true;
    }
    //creates and returns a String representing the contents of the array
    public String toString() {
        //System.out.println(Arrays.toString(this.arr));
        String s = "[";
        if (arr.length >= 1 ){
        for (int i = 0; i < numElements; i ++) {
            s +=  this.arr[i];
            if (i != numElements - 1) {
                s+= ",";
            }
        }
    }
        s += "]";
        return s;
        
    }
    // computes the min,max,sum, and avg of the object
    public void computeStatistics() {
        min = this.arr[0];
        max = this.arr[0];
        sum = 0;
        for (int i = 0; i < numElements; i++) {
            sum += this.arr[i];
            if (this.arr[i] > max) {
                max = this.arr[i];
            }
            if (this.arr[i] < min) {
                min = this.arr[i];
            }
        }
        if (sum > 0) {
            avg = (double) sum/numElements;
        }
        //System.out.println("max "+max);
        //System.out.println("min "+min);
        //System.out.println("avg "+avg);
        //System.out.println("sum "+avg);
    } 
    //returns the index of the last occurence of the number in the array
    public int lastIndex(int n) {
        int index = -1;
        for (int i = 0; i < numElements; i++) {
            if (this.arr[i] == n) {
                index = i;
            }
        }
        return index;
    }
    // insert a new integer at a specified position within the current range of elements
    public boolean insert(int n, int position) {
        if ((numElements == this.arr.length) || (position < 0) || (position > numElements) || (validInput(n) == false)) {
            return false;
        } else {
            for (int i = numElements - 1; i >= position; i--) {
                this.arr[i+1] = this.arr[i];
            }
            this.arr[position] = n;
        }
        numElements ++;
        computeStatistics();
        return true;
    }
    //removes the smallest element in the array
    public int removeSmallest() {
        if (numElements <= 0) {
            return -1;
        }
        int small = 0;
        int store = this.arr[small];
        for (int i = 1; i < numElements; i++) {
            if (this.arr[i] < store) {
                store = this.arr[i];
                small = i;
                
                System.out.println(small);
                //System.out.println(result);
            }
        }
                for (int j = small; j < numElements - 1; j++) {
                    this.arr[j] = this.arr[j+1];
               
            }
        numElements --;
        
       computeStatistics();
        return store;
    }
    // increases the length of the array by the integer n
    public boolean grow(int n) {
        if (n < 0) {
            return false;
        } else {
            int [] newArr = new int[this.arr.length + n];
            for (int i = 0; i < numElements; i++) {
                newArr[i] = this.arr[i];
            }
            this.arr = newArr;
        }
        return true;
    }
    //shrinks the array to the number of elements only, only if there is at least one element and the number of elements don't equal the length of the array
    public boolean shrink() {
        int [] newArr = new int[numElements];
        if (numElements == 0 || numElements == 1 || numElements == this.arr.length) {
            return false;
        }
        for (int i = 0; i < numElements; i++) {
            newArr[i] = this.arr[i];
        }
        this.arr = newArr;
        return true;
    }
//accessor methods
    //allows access to the sum variable
    public int getSum() {
        return this.sum;
    }
    //allows access to the min variable
    public int getMin() {
        return this.min;
    }
     //allows access to the max variable   
    public int getMax() {
        return this.max;
    }
     //allows access to the avg variable   
    public double getAvg() {
       return this.avg;
    }
    //allows access to the numElements variable
    public int getNumElements() {
        return this.numElements;
    }
    //allows access to the length of the array
    public int getArrLength() {
        // return the current physical size (i.e. length) of the object's array
        return this.arr.length;
    }
        // The following accessor method is needed by the autograder to test the contents of the
    // the object's array.
    // 
    // In general, however, it is not good practice to return a reference to an array as
    // arrays are mutable and therefore can be changed. You do not want external code to be able to
    // alter the contents of an array that is private to an object.
    //
    public int[] getArr() {
        return this.arr;
    }
    //creates and returns s string which is a histogram like asterisk reprenstation of the array
    public String computeHistogram() {
        String s = "";
        for (int i = 0; i < this.numElements; i++) {
            for (int j = 0; j < this.arr[i]; j++) {
                s += "*";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String [] args) {

        System.out.println("\nUnit Test for MyArray.\n");
        //MyArray t1 = new MyArray();
        //t1.inputElements();
        //System.out.println(t1.toString());

        //t1.insert(6, 2);
        //t1.insert(24,3);
        //System.out.println(t1.toString());

        //System.out.println(t1.getMax());
        //System.out.println(t1.getMin());
       //System.out.println(t1.getAvg());
        //System.out.println(t1.getSum());
        //System.out.println(t1.getNumElements());
        //System.out.println(t1.getArrLength());

        //t1.removeSmallest();
        //System.out.println(t1.toString());
        //t1.shrink();
        //System.out.println(t1.toString());
        //t1.grow(5);
        //t1.grow(-2);
       // System.out.println(t1.toString());
        
        //test 2
        //System.out.println("this starts test 2");
     //   MyArray t2 = new MyArray(15);
    //    t2.inputElements();
      //  System.out.println(t2.toString());

       // t2.insert(6, 2);
      //  t2.insert(24,3);
      //  t2.insert(3, 24);
      //  System.out.println(t2.toString());

     //   System.out.println(t2.getMax());
      //  System.out.println(t2.getMin());
     //  System.out.println(t2.getAvg());
     //  System.out.println(t2.getSum());
      //  System.out.println(t2.getNumElements());
      //  System.out.println(t2.getArrLength());
        

        //t2.removeSmallest();
      //  System.out.println(t2.toString());
      //  t2.shrink();
      //  System.out.println(t2.toString());


        //test 3
        System.out.println("this starts test 3");
        int [] array = {1,11,60,40};
        MyArray t3 = new MyArray(array);
        t3.inputElements();
        System.out.println(t3.toString());

        t3.insert(6, 2);
        t3.insert(24,3);
        t3.insert(16, 20);
        System.out.println(t3.toString());

        System.out.println(t3.toString());
        System.out.println(t3.getMax());
          System.out.println(t3.getMin());
          System.out.println(t3.getSum());
          System.out.println(t3.getAvg());
        System.out.println(t3.getNumElements());
        System.out.println(t3.getArrLength());

        t3.removeSmallest();
        System.out.println(t3.toString());
        t3.shrink();
        System.out.println(t3.toString());

        

	// Fill in your unit tests

    }
}
