package bench;

import timing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


 public class DummyBenchmark implements IBenchmark  // a dummy sort
{
    int[] Arr;
    int N;
   
    public void run()
    {
        for(int i=0; i<N-1; i++)
            for(int j=i+1; j<N; j++)
            {
                if(Arr[i] > Arr[j])
                {
                    int aux=Arr[i];
                    Arr[i]=Arr[j];
                    Arr[j]=aux;
                }
            }      
    }

    public void initialize(String FilePath) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(FilePath));
        this.N = scanner.nextInt();

        this.Arr = new int[N];
        for (int i = 0; i < N; i++) {
            Arr[i] = scanner.nextInt();
        }
        scanner.close();
    }

    public void initialize(){}

    public void clean(){}
    
    
    public void cancel()
    {
        System.exit(1);
    }


    public static void main(String[] args) 
    {
        DummyBenchmark A=new DummyBenchmark();

        try
        {
            A.initialize("InputFiles/int_1000.txt");
            timing.Timer Timer= new timing.Timer();
            Timer.start();
            A.run();
            System.out.println("time = " + Timer.stop() + "ns");;
        } catch (FileNotFoundException e)
          {
            System.err.println("could not open the input file :(");
          }

    }
}
