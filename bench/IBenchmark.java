package bench;
interface IBenchmark 
{
    void run();
    void initialize();
    void clean();
    void cancel();
}
