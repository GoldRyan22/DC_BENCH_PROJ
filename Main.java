import bench.*;
import bench.ram.VirtualMemoryBenchmark;
import logging.*;
import timing.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;


public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("<---------------- WELCOME TO OUR CPU AND RAM BENCHMARK ------------------>");

        System.out.println("PRESS: 1 FOR CPU BENCHMARK");
        System.out.println("PRESS: 2 FOR RAM BENCHMARK");
        System.out.println("PRESS: 0 FOR EXIT");
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        while(true)
        {
            
        
            switch (option) 
            {
                case 1:
                {
                    System.out.println("PRESS: 1 FOR PIPBB BENCHMARK");
                    System.out.println("PRESS: 2 FOR FIXEDPOINT BENCHMARK");
                    System.out.println("PRESS: 3 FOR FLOATING POINT BENCHMARK");
                    System.out.println("PRESS: 0 FOR EXIT");

                    int option2 = scanner.nextInt();

                    switch (option2) 
                    {
                        case 1:
                        {
                            System.out.println("INTRODUCE THE PRECISION LEVEL: EX 100 for 100 decimals");
                            int precision=scanner.nextInt();
                            PiBBP A=new PiBBP();
                            timing.Timer Timer=new Timer();

                            A.warmUp();
                            Timer.start();
                            BigDecimal pi=A.run(precision);
                            long totalTime=Timer.stop();

                            System.out.println(pi);
                            System.out.println("time = " + totalTime + "ns");
                            break;
                        }

                        case 2:
                        {
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
                            break;
                        }

                        case 3:
                        {
                            FloatingPointArithmeticBench A = new FloatingPointArithmeticBench();
                            Timer timer = new Timer();
                            try {
                                A.initialize("InputFiles/float_1000.txt");
                            } catch (FileNotFoundException e) {
                                System.err.println("could not open the input file :<");
                            }
                            
                            timer.start();
                            int iterations = 200;
                            A.run(iterations, "branching");
                            long totalTime = timer.stop();
                            System.out.println("time = " + totalTime + "ns");
                            long MOPS = (12 * A.size * iterations) / (totalTime / 1000);
                            System.out.println("MOPS = " + MOPS);

                            break;
                        }
                            
                        case 0:
                        {
                            System.exit(0);
                            break;
                        }
                        
                        default:
                            break;
                    }

                    break;
                }

                case 2:
                {
                    System.out.println("PRESS: 1 FOR STACK RECURSION BENCHMARK");
                    System.out.println("PRESS: 2 FOR VIRTUAL MEMORY BENCHMARK");
                    System.out.println("PRESS: 0 FOR EXIT");

                    int option2= scanner.nextInt();

                    switch (option2) 
                    {
                        case 0:
                        {
                            System.exit(0);
                        }

                        case 1:
                        {
                            CPURecursionLoopUnrolling benchmark = new CPURecursionLoopUnrolling();

                            System.out.println("no unrolling");
                            benchmark.run(false); 
                            System.out.println();
                            System.out.println("unrolling of level 1");
                            benchmark.run(true, 1); 
                            System.out.println();
                            System.out.println("unrolling of level 5");
                            benchmark.run(true, 5); 
                            System.out.println();
                            System.out.println("unrolling of level 15");
                            benchmark.run(true, 15); 
                            System.out.println();
                            break;
                        }
                        
                        case 2:
                        {
                            System.out.println("Running, wait...");

                            VirtualMemoryBenchmark bench = new VirtualMemoryBenchmark();

                            long fileSize = 1L * 1024 * 1024 * 1024; // 2GB
                            int bufferSize = 4 * 1024; // 4KB

                            // Initialize the benchmark
                            bench.initialize(fileSize, bufferSize);

                            // Run the benchmark
                            Timer timer =new Timer();
                            timer.start();
                            bench.run(fileSize, bufferSize);
                            long totalTime=timer.stop();

                            // Print the results
                            System.out.println("Done in " + totalTime + "ns");
                            System.out.println(bench.getResult());

                            // Clean up
                            bench.clean();
                        }
                    
                        default:
                            break;
                    }

                    break;
                }

                case 0:
                {
                    System.exit(0);
                }
                
                default:
                {
                    break;
                }
                    
            }
        
        }
    }
    
}
