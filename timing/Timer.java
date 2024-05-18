package timing;

public class Timer implements ITimer
{

    private boolean pauseFlag=false;

    private long startTime=0;
    private long stopTime=0;
    private long elapsedTime=0;
    private long resumeTime=0;

    public void start()
    {
        this.stopTime=0;
        this.elapsedTime=0;
        this.startTime=System.nanoTime();
    }

    public long stop()
    {
        if(pauseFlag==false)
        {
            this.stopTime=System.nanoTime()-startTime;
        }
        else
        {
            this.stopTime=stopTime+(System.nanoTime()-resumeTime);
        }

        return stopTime;
    }

    public long pause()
    {
        if(pauseFlag==false)
            this.elapsedTime=elapsedTime+(System.nanoTime()-startTime);
        else
        {
            this.elapsedTime=elapsedTime+(System.nanoTime()-resumeTime);
        }

        pauseFlag=true;
        this.stopTime=stopTime+this.elapsedTime;

        return elapsedTime;
    }

    public void resume()
    {
        this.resumeTime=System.nanoTime();
    }
    
}
