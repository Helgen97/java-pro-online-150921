package ChatClient.Threads;

import ChatClient.Messages.JsonMessages;
import ChatClient.Messages.Message;
import ChatClient.Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private String login;

    public GetThread() {
        gson = new GsonBuilder().create();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                try (InputStream is = openConnection()) {
                    getMessages(is);
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private InputStream openConnection() throws IOException {
        URL url = new URL(Utils.getURL() + "/get?from=" + n + "&login=" + login);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        return http.getInputStream();

    }

    private void getMessages(InputStream in) throws IOException {
        byte[] buf = Utils.requestBodyToArray(in);
        String strBuf = new String(buf, StandardCharsets.UTF_8);

        JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
        if (list != null) {
            for (Message m : list.getList()) {
                if (m.getTo() == null) {
                    System.out.println(m);
                    n++;
                } else {
                    if (m.getTo().equals(login)) {
                        System.out.println("Private message: ");
                        System.out.println(m);
                    }
                }
            }
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
