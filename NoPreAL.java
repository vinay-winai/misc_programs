import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NoPreAL {
    public static void main(String[] args) {
        try {
            // Read numbers from file and insert into ArrayList
            BufferedReader numbersFile = new BufferedReader(new FileReader("numbers.txt"));
            ArrayList<Integer> numbers = new ArrayList<>();
            long startTime = System.nanoTime();
            String line;
            while ((line = numbersFile.readLine()) != null) {
                int num = Integer.parseInt(line);
                // Find the correct insertion index
                int index = 0;
                for (; index < numbers.size(); index++) {
                    if (numbers.get(index) >= num) {
                        break;
                    }
                }
                numbers.add(index, num);
            }
            numbersFile.close();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println("Total time taken to insert " + numbers.size() + " numbers: " + duration + " ms");

            // Delete elements from ArrayList by value
            BufferedReader deleteFile = new BufferedReader(new FileReader("delete.txt"));
            startTime = System.nanoTime();
            while ((line = deleteFile.readLine()) != null) {
                int numToDelete = Integer.parseInt(line);
                numbers.remove(Integer.valueOf(numToDelete)); // Important: Use Integer.valueOf
            }
            deleteFile.close();
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000;
            System.out.println("Deleted, " + numbers.size() + " numbers left. Time: " + duration + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Results 100k
// Total time taken to insert 100000 numbers: 4443 ms
// Deleted, 0 numbers left. Time: 3211 ms