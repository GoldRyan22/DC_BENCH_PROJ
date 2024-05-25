import bench.*;
import logging.*;
import timing.*;
import java.util.Scanner;


public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("<---------------- WELCOME TO OUR CPU AND RAM BENCHMARK ------------------>");

        System.out.println("PRESS: 1 FOR CPU BENCHMARK");
        System.out.println("PRESS: 2 FOR RAM BENCHMARK");
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();

        switch (option) 
        {
            case 1:
            {
                System.out.println("PRESS: 1 FOR PIPBB BENCHMARK");
                System.out.println("PRESS: 2 FOR FIXEDPOINT BENCHMARK");
                System.out.println("PRESS: 3 FOR FLOATING POINT BENCHMARK");
                System.out.println("PRESS: 4 FOR ");
                break;
            }
                
        
            default:
                break;
        }
        
    }
    
}
