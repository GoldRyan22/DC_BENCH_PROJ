package logging;
interface ILogger
{
    void write(long result);
    void write(String result);
    void write(Object result);
}