package bench;

import bench.ram.VirtualMemoryBenchmark;

public class TestVirtualMemory {
    public static void main(String[] args) {
        VirtualMemoryBenchmark bench = new VirtualMemoryBenchmark();

        long fileSize = 1L * 1024 * 1024 * 1024; // 2GB
        int bufferSize = 4 * 1024; // 4KB

        // Initialize the benchmark
        bench.initialize(fileSize, bufferSize);

        // Run the benchmark
        bench.run(fileSize, bufferSize);

        // Print the results
        System.out.println(bench.getResult());

        // Clean up
        bench.clean();
    }
}