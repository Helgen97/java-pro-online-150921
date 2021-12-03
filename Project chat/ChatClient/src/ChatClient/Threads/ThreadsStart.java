package ChatClient.Threads;

public class ThreadsStart {

    public static void threadStart(String login) {
        GetThread getThread = new GetThread();
        getThread.setLogin(login);
        Thread th = new Thread(getThread, "messageThread");
        th.setDaemon(true);
        th.start();
    }
}
