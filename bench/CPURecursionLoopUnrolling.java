package bench;


    public class CPURecursionLoopUnrolling {

        public static void main(String[] args) {
            CPURecursionLoopUnrolling benchmark = new CPURecursionLoopUnrolling();

            System.out.println("no unrolling");
            benchmark.run(false); 
            System.out.println();
            System.out.println("unrolling of level 1");
            benchmark.run(true, 1); 
            System.out.println();
            System.out.println("unrolling of level 5");
            benchmark.run(true, 5); 
            System.out.println();
            System.out.println("unrolling of level 15");
            benchmark.run(true, 15); 
            System.out.println();
        }

        public void run(Object... params) {
            boolean useUnrolling = params.length > 0 && (Boolean) params[0];
            int unrollLevel = params.length > 1 ? (Integer) params[1] : 1;


            long startTime = System.nanoTime();
            long primesFound = 0;

            int size = 4565;

            if (useUnrolling) {
                try {
                    primesFound = recursiveUnrolled(1, unrollLevel, size, 0);
                } catch (StackOverflowError e) {
                    System.out.println("Stack overflow occurred during unrolled recursion.");
                }
            } else {
                try {
                    primesFound = recursive(1, size, 0);
                } catch (StackOverflowError e) {
                    System.out.println("Stack overflow occurred during regular recursion.");
                }
            }

            System.out.printf("size: %d %n",size);
            long endTime = System.nanoTime();
            double elapsedTime = (endTime - startTime) / 1_000_000.0;
            System.out.printf("Finished in %.4f Milli%n", elapsedTime);
            double score = Scoring(primesFound, elapsedTime);
            System.out.printf("Performance Score: %.2f%n", score);
        }


        public void initialize(){}
        public void clean(){}

        public void cancel(){
            System.exit(-1);
        }


        private long recursive(long start, long size, int counter) {
            if (start > size) {
                return counter;
            }
            if (isPrime(start)) {
                counter++;
            }
            try {
                return recursive(start + 1, size, counter);
            } catch (StackOverflowError e) {
                System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
                return 0;
            }
        }

        private long recursiveUnrolled(long start, int unrollLevel, int size, int counter) {
            if (start > size) {
                return counter;
            }
            int primesFound = 0;
            for (int i = 0; i < unrollLevel && start <= size; i++, start++) {
                if (isPrime(start)) {
                    primesFound++;
                }
            }
            counter += primesFound;
            try {
                return recursiveUnrolled(start, unrollLevel, size, counter);
            } catch (StackOverflowError e) {
                System.out.println("Reached nr " + start + "/" + size + " at " + unrollLevel + " levels after " + counter + " calls.");
                return 0;
            }
        }

        private boolean isPrime(long x) {
            if (x <= 2) {
                return x == 2;
            }
            if (x % 2 == 0) {
                return false;
            }
            for (long i = 3; i <= Math.sqrt(x); i += 2) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }

        private double Scoring(long primesFound, double elapsedTime) {
            return (primesFound / elapsedTime) * 1000;
        }

    }
