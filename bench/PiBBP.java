package bench;
import timing.*;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.math.MathContext;



public class PiBBP implements IBenchmark
{

    public void run(int n)
    {
        
            BigDecimal pi = BigDecimal.ZERO;
            for(int i = 0; i < n; i++){
                BigDecimal nmb = BigDecimal.ONE
                .divide(BigDecimal.valueOf(16).pow(i), n, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8 * i + 1), n, RoundingMode.HALF_UP)
                .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8 * i + 4), n, RoundingMode.HALF_UP))
                .subtract(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8 * i + 5), n, RoundingMode.HALF_UP))
                .subtract(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8 * i + 6), n, RoundingMode.HALF_UP)));
                pi = pi.add(nmb);
            }
            System.out.println(pi);
        }

    public void run(){}
    public void initialize(){}
    public void clean(){}
    public void cancel(){}    


    public static void main(String[] args) 
    {
        PiBBP A=new PiBBP();
        timing.Timer Timer=new Timer();
        Timer.start();
        A.run(400);
        System.out.println("time = " + Timer.stop() + "ns");
        
    }

}