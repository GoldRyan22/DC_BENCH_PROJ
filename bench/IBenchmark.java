package bench;
public interface IBenchmark 
{
    void run();
    void initialize();
    void clean();
    void cancel();
}
