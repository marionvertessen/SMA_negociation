public class agent extends Thread{
    public int id;

    public agent (int id) {
        this.id = id;
    }
    @Override
    public void run() {
        int index = 1;

        for (int i = 0; i < 10; i++) {
            System.out.println("  - HelloThread running " + index++);

            try {
                // Sleep 1030 miliseconds.
                Thread.sleep(1030);
            } catch (InterruptedException e) {
            }

        }
        System.out.println("  - ==> HelloThread stopped");
    }

}
