package bench;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import timing.Timer;

public class FixedPointArithmeticBench implements IBenchmark{
    private final int N = 69;
    Integer[] list = new Integer[N];

    public void run(int n){
        int result = 0;
        
        for(int j=0; j<n; j++)
        {   
            for(int i = 0; i < N; i++){
                if(i % 2 == 0){
                    result += list[i] + list[list.length - 1 - i];
                    result += list[list.length - 1 - i] / list[i];
                }
                else{
                    result += list[i] - list[list.length - 1 - i];
                    result += list[list.length - 1 - i] * list[i];
                }
            }
        }    
    }

    public void initialize(String FilePath) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(FilePath));
        int size=scanner.nextInt();

        for (int i = 0; i < N; i++) {
            list[i] = scanner.nextInt();
        }
        scanner.close();
    }

    public void run() {}

    public void initialize() {}

    public void clean() {}

    public void cancel() {}
    public static void main(String[] args) {
        FixedPointArithmeticBench A = new FixedPointArithmeticBench();
        Timer Timer=new Timer();
        try
        {
            A.initialize("InputFiles/int_1000.txt");
        } catch(FileNotFoundException e)
        {
            System.err.println("could not open the input file :<");
        }
        
        Timer.start();
        int iterations=200;
        A.run(iterations);
        long totalTime= Timer.stop();
        System.out.println("time = " + totalTime + "ns");
        long MOPS=(12*69*iterations)/(totalTime/1000);
        System.out.println("MOPS = " + MOPS);
    }

}
