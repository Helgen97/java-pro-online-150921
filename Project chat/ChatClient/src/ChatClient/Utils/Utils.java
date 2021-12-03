package ChatClient.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    private static final String URL = "http://localhost";
    private static final int PORT = 8080;
    public static String chatCommands = """
            /commands - print chat commands
            /users - get users list
            /private - send a private message
            /exit - logout from chat
            /new - create new chat room
            /room - connect to chat room
            /rsend - send message to room
            /listroom - get all available rooms
            /leave - leave chat room
            """;

    public static String getURL() {
        return URL + ":" + PORT;
    }

    public static byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

    public static void printCommands(){
        System.out.println(chatCommands);
    }
}
