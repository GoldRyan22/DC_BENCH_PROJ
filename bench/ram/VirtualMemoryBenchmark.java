package bench.ram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import bench.IBenchmark;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

class MemoryMapper {
    private MappedByteBuffer mappedByteBuffer;
    private RandomAccessFile file;

    public MemoryMapper(String fileName, long fileSize) throws IOException {
        file = new RandomAccessFile(fileName + ".mem", "rw");
        file.setLength(fileSize);
        mappedByteBuffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
    }

    public byte[] get(long offSet, int size) throws IOException {
        byte[] dst = new byte[size];
        for (int i = 0; i < size; i++) {
            dst[i] = mappedByteBuffer.get((int) offSet + i);
        }
        return dst;
    }

    public void put(long offSet, byte[] src) throws IOException {
        for (int i = 0; i < src.length; i++) {
            mappedByteBuffer.put((int) offSet + i, src[i]);
        }
    }

    public void close() throws IOException {
        file.close();
    }
}

public class VirtualMemoryBenchmark implements IBenchmark {
    private MemoryMapper memoryMapper;
    private long fileSize;
    private int bufferSize;
    private double writeSpeed;
    private double readSpeed;

    
    public void initialize(Object... params) {
        this.fileSize = (long) params[0];
        this.bufferSize = (int) params[1];
        try {
            this.memoryMapper = new MemoryMapper("tempfile", this.fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){};
    public void run(){};

    
    public void warmUp() {
        // No specific warm-up needed for this benchmark
    }

    
    public void run(Object... options) {
        run((long) options[0], (int) options[1]);
    }

    public void run(long fileSize, int bufferSize) {
        byte[] buffer = new byte[bufferSize];
        long startTime, endTime;

        // Write operation
        startTime = System.nanoTime();
        try {
            for (long i = 0; i < fileSize; i += bufferSize) {
                memoryMapper.put(i, buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        writeSpeed = (fileSize / 1e6) / ((endTime - startTime) / 1e9); // MB/s

        // Read operation
        startTime = System.nanoTime();
        try {
            for (long i = 0; i < fileSize; i += bufferSize) {
                memoryMapper.get(i, bufferSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        this.readSpeed = (fileSize / 1e6) / ((endTime - startTime) / 1e9); // MB/s

        // Clean up
        try {
            memoryMapper.close();
            Files.delete(Paths.get("tempfile.mem"));
        } catch (IOException e) {
           // e.printStackTrace();
        }
    }

    @Override
    public void clean() {
        try {
            memoryMapper.close();
            Files.delete(Paths.get("tempfile.mem"));
        } catch (IOException e) {
           // e.printStackTrace();
        }
        
    }

    @Override
    public void cancel() {
        System.exit(-1);
    }

    public String getResult() {
        return String.format("Write speed: %.2f MB/s, Read speed: %.2f MB/s", writeSpeed, readSpeed);
    }
}
