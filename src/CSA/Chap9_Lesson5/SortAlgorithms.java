package CSA.Chap9_Lesson5;
import java.util.Arrays;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JFrame;
import java.awt.*;

public class SortAlgorithms {

    // --- (All sorting algorithm implementations from previous response) ---
    // --- Insertion Sort ---
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // --- Bubble Sort ---
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // --- Selection Sort ---
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // --- Merge Sort ---
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // Avoid potential overflow

            // Sort first and second halves
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        // Merge the temp arrays

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // --- Utility Functions ---
    public static int[] generateTestArray(int size, String type) {
        Random random = new Random();
        int[] arr = new int[size];
        switch (type) {
            case "random":
                for (int i = 0; i < size; i++) arr[i] = random.nextInt(size * 2);
                break;
            case "nearly-sorted":
                for (int i = 0; i < size; i++) arr[i] = i;
                for (int i = 0; i < size / 10; i++) { // Introduce swaps
                    int index1 = random.nextInt(size);
                    int index2 = random.nextInt(size);
                    int temp = arr[index1];  arr[index1] = arr[index2]; arr[index2] = temp;
                }
                break;
            case "reverse":
                for (int i = 0; i < size; i++) arr[i] = size - i;
                break;
            case "few-unique":
                for (int i = 0; i < size; i++) arr[i] = random.nextInt(5);
                break;
            default:
                throw new IllegalArgumentException("Invalid array type: " + type);
        }
        return arr;
    }
    // --- Charting Function ---

    private static void createAndShowChart(XYSeriesCollection dataset, String title, String xAxisLabel, String yAxisLabel) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Customize lines and points (optional, but makes the chart look better)
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(2.0f)); // Thicker lines
            renderer.setSeriesShapesVisible(i, true);          // Show data points
        }
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Set preferred size
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        int[] sizes = {10, 100, 500, 1000, 2000, 3000, 4000, 5000}; // More sizes for smoother curves
        String[] types = {"random", "nearly-sorted", "reverse", "few-unique"};
        String[] algorithms = {"Insertion", "Bubble", "Selection", "Merge"};

        for (String type : types) {
            XYSeriesCollection dataset = new XYSeriesCollection();

            for (String algorithm : algorithms) {
                XYSeries series = new XYSeries(algorithm);

                for (int size : sizes) {
                    int[] arr = generateTestArray(size, type);
                    int[] arrCopy = arr.clone();
                    long startTime = System.nanoTime();

                    switch (algorithm) {
                        case "Insertion": insertionSort(arrCopy); break;
                        case "Bubble":    bubbleSort(arrCopy);    break;
                        case "Selection": selectionSort(arrCopy); break;
                        case "Merge":     mergeSort(arrCopy);     break;
                    }

                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime) / 1000; // Microseconds
                    series.add(size, duration);
                }
                dataset.addSeries(series);
            }
            createAndShowChart(dataset, "Sorting Algorithm Performance (" + type + ")", "Array Size", "Time (microseconds)");
        }
    }
}