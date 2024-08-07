package org.example.crackingCoding.threads;

class RunnableThreadExample implements java.lang.Runnable {
    public int count = 0;
    @Override
    public void run() {
        System.out.println("RunnableThread starting.");
        try {
            while(count < 5) {
                Thread.sleep(500);
                count++;
            }
        } catch (InterruptedException exc) {
            System.out.println("RunnableThread interrupted");
        }
        System.out.println("RunnableThread terminating.");
    }

    public static void main(String[] args) {
        RunnableThreadExample instance = new RunnableThreadExample();
        Thread thread = new Thread(instance);
        thread.start();
    }
}


