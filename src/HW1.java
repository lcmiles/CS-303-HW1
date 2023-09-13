import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class HW1 {

    public static void main(String[] arg) {
        try {
            int[] keyArray = new int[1000]; //initializes array to size of 1000 since key file contains 1000 keys
            File file = new File("input_1000.txt"); //reads specified key file
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String intString = reader.nextLine();
                String[] keystringArray = intString.split("\\s*,\\s*"); //splits the string at each " , " and adds each individual string to an array
                for(int i = 0; i < keystringArray.length; i++) { //converts each string in the array to an int and adds to a new array
                    String keyString = keystringArray[i]; 
                    keyArray[i] = Integer.parseInt(keyString);
                }
            }
            reader.close();
            for (int n = 4; n <= 20; n++) { //iterates through the powers of 2^4 to 2^20
                int size = (int) Math.pow(2, n); //size = 2^n
                int[] intArray = new int[size];
                for (int i = 0; i <= size-1; i++) { //counts through array and adds each number to it in numerical order, replacing the 0s
                    intArray[i] = i+1;
                }
                long timeInit = System.nanoTime(); 
                for (int i = 0; i < keyArray.length; i++) { //iterates through the key array and runs a binary search for each
                    int key = keyArray[i];
                    binarySearch(intArray, key);
                }
                long timeFinal = System.nanoTime();
                long time = timeFinal - timeInit; //calculates time taken for binary search algorithm
                System.out.println("Binary Search Time:" + time);
                System.out.println("Binary Search Data Set:" + size);
                Random rand = new Random();
		        for (int i = 0; i < intArray.length; i++) { //randomizes the indexes in the array
			        int randomIndexToSwap = rand.nextInt(intArray.length);
			        int temp = intArray[randomIndexToSwap];
			        intArray[randomIndexToSwap] = intArray[i];
			        intArray[i] = temp;
		        }
                long timeInit2 = System.nanoTime();
                for (int i = 0; i < keyArray.length; i++) { //iterates through the key array and runs a linear search for each
                    int key = keyArray[i];
                    linearSearch(intArray, key);
                }
                long timeFinal2 = System.nanoTime();
                long time2 = timeFinal2 - timeInit2;
                System.out.println("Linear Search Time:" + time2);
                System.out.println("Linear Search Data Set:" + size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int linearSearch(int[] intArray, int key) {
        for (int i = 0; i < intArray.length; i++) { //iterates through each index through the entire array one-by-one
            if (intArray[i] == key) { //if the int at the index matches the key, return the index
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] intArray, int key) {
        int start = 0; //starting index
        int end = intArray.length - 1; //ending index
        while (start<=end) {
            int mid = (start+end)/2; //calculates middle index my averaging the start and end indexes
            if (key == intArray[mid]) { //returns the index of the int that matches the key 
                return mid;
            }
            if (key < intArray[mid]) { //if the key is less than the middle value then use first half
                end = mid-1;
            }
            else {
                start = mid + 1; //if the key is more than the middle value then use the second half
            }

        }
        return -1;
    }

}
