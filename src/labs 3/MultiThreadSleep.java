public class MultiThreadSleep {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("1");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    System.out.println("2");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    System.out.println("3");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}