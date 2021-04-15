public class start_mu_test {

    public static void main(String[] args) {

        // channel channel = new channel(2);

        // channel.startWorkerThread();



        Thread thread0 = new Thread(new mu_test());

        Thread thread1 = new Thread(new get_use());



        thread0.start();

        thread1.start();

    }

}

