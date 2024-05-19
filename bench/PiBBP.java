package bench;
import timing.*;

import java.math.RoundingMode;
import java.math.BigDecimal;

public class PiBBP implements IBenchmark
{

    public BigDecimal run(int n)
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
            return pi;
    }


    public void warmUp()
    {
        this.run(50);
    }

    public void run(){}
    public void initialize(){}
    public void clean(){}
    public void cancel()
    {
        System.exit(-1);
    }    


    public static void main(String[] args) 
    {
        PiBBP A=new PiBBP();
        timing.Timer Timer=new Timer();

        A.warmUp();
        Timer.start();
        BigDecimal pi=A.run(100);
        long totalTime=Timer.stop();

        System.out.println(pi);
        System.out.println("time = " + totalTime + "ns");
        
    }

}