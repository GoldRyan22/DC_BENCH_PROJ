package bench;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import timing.*;

public class FloatingPointArithmeticBench implements IBenchmark {
    public int size = 100;
    Float[] list;

    public int run(int n, String type) {
        float res = 0;
        float k = 1.1f;
        float r;
        
        if(type.equals("arithmetic")) {
            for (int j = 0; j < n; j++) {   
                k = list[3] * (2 - list[1]);
                r = k / list[2] + list[6];
                res = k * r / (list[1] + r);
            }
            return 18;
        }
        else if(type.equals("branching")) {
            Float[] numbers = {3.0f, 4.0f, 5.5f, 6.7f, 1.1f};
            for(int j = 0; j < n; j++) {
                if (k == 1.1f)
                    k = list[2];
                else
                    k = list[3];
                if (k > 3)
                    k = list[4];
                else
                    k = list[2];
                if (k < 5)
                    k = list[4];
                else
                    k = list[0];
            }
            return 9;
        }
        else {
            Float[] a = new Float[n];
            for(int j = 1; j < n-2; j++) {
                a[j] = list[j];
                a[j-1] = list[j+1] + list[j];
                a[j+2] = list[j+2] + a[j] + a[j-1];
            }
            return 15;
        }

    }

    public void initialize(String FilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(FilePath));
        size = scanner.nextInt();
        this.list = new Float[size];
        for (int i = 0; i < size; i++) {
            list[i] = scanner.nextFloat();
        }
        scanner.close();
    }

    public void run() {}

    public void initialize() {}

    public void clean() {}

    public void cancel() {}

    public static void main(String[] args) {
        FloatingPointArithmeticBench A = new FloatingPointArithmeticBench();
        Timer timer = new Timer();
        try {
            A.initialize("inputFiles/float_1000.txt");
        } catch (FileNotFoundException e) {
            System.err.println("could not open the input file :<");
        }
        
        timer.start();
        int iterations = 200;
        int ops = A.run(iterations, "branching");
        long totalTime = timer.stop();
        System.out.println("time = " + totalTime + "ns");
        long MOPS = (ops * A.size * iterations) / (totalTime / 1000);
        System.out.println("MOPS = " + MOPS);
    }
}
