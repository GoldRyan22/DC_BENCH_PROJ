package logging;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger
{
    FileWriter File;

    public void closeFile() throws IOException
    {
        this.File.close();
    }

    public FileLogger(String Filename) throws IOException
    {
        File=new FileWriter(Filename);
    }

    public void write(String result)
    {
        try
        {
            this.File.write(result);
        } catch(IOException e)
          {
            System.err.println("error could not write string " + result);
          }
        
    }

    public void write(long result)
    {
        try
        {
            this.File.write(Long.toString(result));
        } catch(IOException e)
          {
            System.err.println("error could not write long " + result);
          }
    }

    public void write(Object result)
    {
        try
        {
            this.File.write(result.toString());
        } catch(IOException e)
          {
            System.err.println("error could not write Object" + result.toString());
          }
    }
}
