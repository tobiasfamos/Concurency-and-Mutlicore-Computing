public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = (args.length >= 1 ? Integer.parseInt(args[0]) : 4);
        long maxNumberToCheckPrime = (args.length >= 2 ? Long.parseLong(args[1]) : 10000000);

        long setSiz = maxNumberToCheckPrime/numberOfThreads;
        Thread[] threads = new Thread[numberOfThreads];
        for(int threadCount = 0; threadCount < numberOfThreads; threadCount++){
            long start = threadCount * setSiz;
            long end = (threadCount + 1) * setSiz;
            if(threadCount == numberOfThreads -1){
                end = maxNumberToCheckPrime;
            }
            Thread t = new Thread(new PrimeRunnable(start, end));
            threads[threadCount] = t;
        }

        Long start = System.currentTimeMillis();
        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads){
            t.join();
        }
        Long executionTime = System.currentTimeMillis() - start;
        System.out.println(String.format("Execution time: %d", executionTime));

    }

    public static void printAllPrimes(long start, long end){
        for(long i = start; i <= end; i++){
            if(Task1.isPrime(i)){
                System.out.println(i);
            }
        }
    }


    public static boolean isPrime(long inputNumber)  {
        {
            if (inputNumber <= 1) {
                return false;
            } else {
                boolean isPrime = true;
                for (long i = 2; i <= inputNumber / 2; i++) {
                    if ((inputNumber % i) == 0) {
                        isPrime = false;

                        break;
                    }
                }
                return isPrime;
            }
        }
    }

    public static class PrimeRunnable implements Runnable {
        long start;
        long end;
        public PrimeRunnable(long start, long end){
            this.start = start;
            this.end = end;
        }

        @Override
        public void run(){
            Task1.printAllPrimes(this.start, this.end);
        }
    }
}
