import java.math.RoundingMode;
import java.math.BigDecimal;
import java.math.MathContext;


public class Main {
    public static void main(String[] args) {
        final int precision = 10000;
        long start = System.nanoTime();
        BigDecimal pi = BBP(precision);
        long end = System.nanoTime();
        long time = (end - start) / 1_000_000;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        System.out.println(pi.round(mc) + "\n" + time);
    }
    public static BigDecimal BBP(int n){
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
}
