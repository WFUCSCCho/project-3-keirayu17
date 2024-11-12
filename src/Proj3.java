import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public static <T extends Comparable<T>> void merge(ArrayList<T> a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        ArrayList<T> L = new ArrayList<>(n1);
        ArrayList<T> R = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++) {
            L.add(a.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            R.add(a.get(mid + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
                a.set(k++, L.get(i++));
            }
            else{
                a.set(k++, R.get(j++));
            }
        }

        while (i < n1){
            a.set(k++, L.get(i++));
        }
        while (j < n2){
            a.set(k++, R.get(j++));
        }
    }

    // Quick Sort
    public static <T extends Comparable<T>> void quickSort(ArrayList<T> a, int left, int right) {
        if (left < right) {
            int pivot = partition(a, left, right);
            quickSort(a, left, pivot - 1);
            quickSort(a, pivot + 1, right);
        }
    }

    public static <T extends Comparable<T>> int partition (ArrayList<T> a, int left, int right) {
        T pivot = a.get(right);
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (a.get(j).compareTo(pivot) < 0) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, right);

        return i + 1;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable<T>> void heapSort(ArrayList<T> a, int left, int right) {
        int n = right - left + 1;
        for (int i = left + n / 2 - 1; i >= left; i--) {
            heapify(a, i, right);
        }
        for (int i = right; i > left; i--) {
            swap(a, left, i);
            heapify(a, left, i-1);
        }
    }

    public static <T extends Comparable<T>> void heapify (ArrayList<T> a, int left, int right) {
        int largest = left;
        int leftChild = 2 * left + 1;
        int rightChild = 2 * left + 2;

        if (leftChild <= right && a.get(leftChild).compareTo(a.get(largest)) > 0) {
            largest = leftChild;
        }

        if (rightChild <= right && a.get(rightChild).compareTo(a.get(largest)) > 0) {
            largest = rightChild;
        }

        if (largest != left) {
            swap(a, left, largest);
            heapify(a, largest, right);
        }
    }

    // Bubble Sort
    public static <T extends Comparable<T>> int bubbleSort(ArrayList<T> a, int size) {
        int comparisons = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                comparisons++;
                if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                    swap(a, j, j + 1);
                }
            }
        }
        return comparisons;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable<T>> int transpositionSort(ArrayList<T> a, int size) {
        boolean isSorted = false;
        int comparisons = 0;

        while (!isSorted) {
            isSorted = true;

            // Sort odd indexes
            for (int i = 1; i < size - 1; i += 2) {
                comparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
            }

            // Sort even indexes
            for (int i = 0; i < size - 1; i += 2) {
                comparisons++;
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
            }
        }
        return comparisons;
    }

    public static void main(String [] args)  throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: <input file> <algorithm type> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String algorithmType = args[1];
        int numLines = Integer.parseInt(args[2]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // Ignore first line
        inputFileNameScanner.nextLine();

        // Store data into original ArrayList
        ArrayList<Movie> movieList = new ArrayList<>();

        while (inputFileNameScanner.hasNextLine() && movieList.size() < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] data = line.split(",");
            String title = data[0];
            int totalGross = Integer.parseInt(data[1]);
            String releaseDate = data[2];
            String distributor = data[3];

            Movie movie = new Movie(title, totalGross, releaseDate, distributor);
            movieList.add(movie);
        }
        inputFileNameScanner.close();

        ArrayList<Movie> sortedList = new ArrayList<>(movieList);
        ArrayList<Movie> shuffledList = new ArrayList<>(movieList);
        ArrayList<Movie> reversedList = new ArrayList<>(movieList);

        Collections.sort(sortedList);
        Collections.shuffle(shuffledList);
        Collections.sort(reversedList, Collections.reverseOrder());

        FileWriter analysisWriter = new FileWriter("analysis.txt", true);

        for (String state : new String[]{"Sorted", "Shuffled", "Reversed"}) {
            ArrayList<Movie> list;
            switch (state) {
                case "Sorted":
                    list = new ArrayList<>(sortedList);
                    break;
                case "Shuffled":
                    list = new ArrayList<>(shuffledList);
                    break;
                case "Reversed":
                    list = new ArrayList<>(reversedList);
                    break;
                default:
                    throw new IllegalStateException("Unexpected state: " + state);
            }

            long startTime = System.nanoTime();
            int comparisons = 0;
            switch (algorithmType.toLowerCase()) {
                case "bubble":
                    comparisons = bubbleSort(list, list.size());
                    break;
                case "merge":
                    mergeSort(list, 0, list.size() - 1);
                    break;
                case "quick":
                    quickSort(list, 0, list.size() - 1);
                    break;
                case "heap":
                    heapSort(list, 0, list.size() - 1);
                    break;
                case "transposition":
                    comparisons = transpositionSort(list, list.size());
                    break;
                default:
                    System.err.println("Unrecognized algorithm type");
                    analysisWriter.close();
                    return;
            }
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            // Print to screen
            System.out.printf("%s Sort on %s list: Time = %d ns, Comparisons = %d\n", algorithmType, state, duration, comparisons);

            // Append to analysis.txt
            analysisWriter.write(String.format("%s,%s,%d,%d,%d\n", algorithmType, state, duration, comparisons, list.size()));

            // Write the sorted list to sorted.txt for each state
            try (FileWriter sortedWriter = new FileWriter("sorted.txt", false)) {
                for (Movie movie : list) {
                    sortedWriter.write(movie.getTitle() + "\n");
                }
            }

        }
        analysisWriter.close();
    }
}
