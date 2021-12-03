
import ChatClient.Messages.Message;
import ChatClient.Threads.ThreadsStart;
import ChatClient.Users.User;
import ChatClient.Utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatStart {
    private static final Scanner scanner = new Scanner(System.in);
    private static User user;

    public static void start() {
        try {
            logIn();
            System.out.println(Utils.chatCommands);
            String login = user.getLogin();
            ThreadsStart.threadStart(login);
            messageCycle(login);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void logIn() throws IOException {
        System.out.println("Welcome! Please login or register!");
        System.out.println("1.Login");
        System.out.println("2.Register");
        String answer = scanner.nextLine();
        if (answer.equals("1")) {
            login();
        } else if (answer.equals("2")) {
            register();
            System.out.println("Now login to chat!");
            login();
        }

    }

    private static void login() throws IOException {
        while (true) {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();
            System.out.println("Enter your password: ");
            String pass = scanner.nextLine();
            user = new User(login, pass);
            int res = loginAction(user);
            if (res == 400) {
                System.out.println("Wrong login or password. Try again!");
                continue;
            }
            if (res != 200) { // 200 OK
                System.out.println("HTTP error occurred: " + res);
                return;
            }
            break;
        }
    }

    private static int loginAction(User user) throws IOException {
        URL obj = new URL(Utils.getURL() + "/log");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = user.toGson();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            return conn.getResponseCode();
        }
    }

    private static void register() throws IOException {
        while (true) {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();
            System.out.println("Enter your password: ");
            String pass = scanner.nextLine();
            user = new User(login, pass);
            int res = regAction(user);
            if (res == 400) {
                System.out.println("Try again!");
                continue;
            }
            if (res != 200) { // 200 OK
                System.out.println("HTTP error occurred: " + res);
                return;
            }
            break;
        }
    }

    private static int regAction(User user) throws IOException {
        URL obj = new URL(Utils.getURL() + "/reg");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = user.toGson();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            return conn.getResponseCode();
        }
    }


    private static void logout(String login) throws IOException {
        URL obj = new URL(Utils.getURL() + "/logout?login=" + login);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.getResponseCode();
        System.out.println("Successfully logged out!");

    }

    public static void getUserList() throws IOException {
        URL url = new URL(Utils.getURL() + "/get_users");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        try (InputStream is = http.getInputStream()) {
            byte[] buf = Utils.requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            System.out.println(strBuf);
        }
    }

    private static void sendMessage(String login, String text) throws IOException {
        Message m = new Message(login, text);
        int res = m.send(Utils.getURL() + "/add");
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static void sendPrivateMessage(String login) throws IOException {
        System.out.println("To who: ");
        String to = scanner.nextLine();
        System.out.println("Enter private message");
        String text = scanner.nextLine();
        sendPrivateMessageAction(login, to, text);
    }

    private static void sendPrivateMessageAction(String login, String to, String text) throws IOException {
        Message m = new Message(login, to, text);
        int res = m.send(Utils.getURL() + "/add");
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static void messageCycle(String login) throws IOException {
        System.out.println("Enter your message: ");

        while (true) {
            String text = scanner.nextLine();
            if (text == null || "".equals(text)) {
                System.out.println("Empty message. Try again.");
            } else if (text.equals("/users")) {
                getUserList();
            } else if (text.startsWith("/private")) {
                sendPrivateMessage(login);
            } else if (text.equals("/exit")) {
                logout(login);
                break;
            } else if (text.startsWith("/new")) {
                addRoom();
            } else if (text.startsWith("/room")) {
                connectToRoom();
            } else if (text.startsWith("/listroom")) {
                getRoomList();
            } else if (text.startsWith("/leave")) {
                leaveRoom();
            } else if (text.startsWith("/rsend")) {
                sendMessageToRoom(login);
            } else if (text.startsWith("/commands")) {
                Utils.printCommands();
            } else {
                sendMessage(login, text);
            }
        }
    }

    private static void sendMessageToRoom(String login) throws IOException {
        System.out.println("To send message to room, enter the room name: ");
        String roomName = scanner.nextLine().trim();
        System.out.println("Enter the message: ");
        String text = scanner.nextLine();
        sendMessageToRoomAction(login, null, text, roomName);
    }

    private static void sendMessageToRoomAction(String login, String to, String text, String roomName) throws IOException {
        Message m = new Message(login, to, text, roomName);
        int res = m.send(Utils.getURL() + "/add");
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static void addRoom() throws IOException {
        System.out.println("To create room, please enter room name: ");
        String roomName = scanner.nextLine().trim();
        int res = addRoomAction(roomName);
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static int addRoomAction(String roomName) throws IOException {
        URL obj = new URL(Utils.getURL() + "/add_room");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = roomName + " " + user.toGson();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            return conn.getResponseCode();
        }
    }

    private static void connectToRoom() throws IOException {
        System.out.println("To connect to room, please enter room name: ");
        String roomName = scanner.nextLine().trim();
        int res = connectToRoomAction(roomName);
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static int connectToRoomAction(String roomName) throws IOException {
        URL obj = new URL(Utils.getURL() + "/room_connect");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = roomName + " " + user.toGson();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            return conn.getResponseCode();
        }
    }

    private static void getRoomList() throws IOException {
        URL url = new URL(Utils.getURL() + "/get_rooms");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        try (InputStream is = http.getInputStream()) {
            byte[] buf = Utils.requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            System.out.println(strBuf);
        }
    }

    private static void leaveRoom() throws IOException {
        System.out.println("To leave room, please enter room name: ");
        String roomName = scanner.nextLine().trim();
        int res = leaveRoomAction(roomName);
        if (res != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + res);
        }
    }

    private static int leaveRoomAction(String roomName) throws IOException {
        URL obj = new URL(Utils.getURL() + "/leave_room");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = roomName + " " + user.toGson();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
            return conn.getResponseCode();
        }
    }
}
