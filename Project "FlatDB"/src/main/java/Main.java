import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/Flats?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Helgen123";

    static Connection connection;

    public static void main(String[] args) {
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

                while (true) {
                    System.out.println("1. Add new flat");
                    System.out.println("2. Change flat information");
                    System.out.println("3. Delete flat");
                    System.out.println("4. View flats table");
                    System.out.println("5. View flats by number of rooms");
                    System.out.println("6. View flats by price");
                    System.out.print("->");

                    String s = scanner.nextLine();
                    selectMode(s, scanner);
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void selectMode(String s, Scanner scanner) throws SQLException {
        switch (s) {
            case "1" -> addFlat(scanner);
            case "2" -> changeFlat(scanner);
            case "3" -> deleteFlat(scanner);
            case "4" -> showFlats();
            case "5" -> showFlatsByRooms(scanner);
            case "6" -> showFlatsByPrice(scanner);
        }
    }

    private static void addFlat(Scanner scanner) throws SQLException {
        System.out.println("Enter the district: ");
        String district = scanner.nextLine();
        System.out.println("Enter the address: ");
        String address = scanner.nextLine();
        System.out.println("Enter the flat area: ");
        double area = scanner.nextDouble();
        System.out.println("Enter the amount of rooms: ");
        int rooms = scanner.nextInt();
        System.out.println("Enter flat price: ");
        int price = scanner.nextInt();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO FlatsDB (District, Address, Area, Rooms, price) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, district);
            preparedStatement.setString(2, address);
            preparedStatement.setDouble(3, area);
            preparedStatement.setInt(4, rooms);
            preparedStatement.setInt(5, price);
            preparedStatement.executeUpdate();
        }
    }

    private static void changeFlat(Scanner scanner) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE FlatsDB SET District = ?, Address = ?, Area = ?, Rooms = ?, Price = ? WHERE id = ?"
        )) {
            System.out.println("Enter the id of flat: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter new district: ");
            String district = scanner.nextLine();
            System.out.println("Enter new address: ");
            String address = scanner.nextLine();
            System.out.println("Enter new flat area: ");
            double area = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter new amount of rooms: ");
            int rooms = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter new flat price: ");
            int price = Integer.parseInt(scanner.nextLine());

            preparedStatement.setString(1, district);
            preparedStatement.setString(2, address);
            preparedStatement.setDouble(3, area);
            preparedStatement.setInt(4, rooms);
            preparedStatement.setInt(5, price);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        }
    }

    private static void deleteFlat(Scanner scanner) throws SQLException {
        System.out.println("Enter the id of flat: ");
        int id = Integer.parseInt(scanner.nextLine());

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE from FlatsDB where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private static void showFlatsByRooms(Scanner scanner) throws SQLException {
        System.out.println("Enter number of rooms: ");
        int rooms = Integer.parseInt(scanner.nextLine());

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from FlatsDB where Rooms = ?")) {
            preparedStatement.setInt(1, rooms);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t\t");
                }
                System.out.println();

                while (resultSet.next()) {
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        System.out.print(resultSet.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void showFlatsByPrice(Scanner scanner) throws SQLException {
        System.out.println("Enter price: ");
        int price = Integer.parseInt(scanner.nextLine());

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from FlatsDB where Price >= ?")) {
            preparedStatement.setInt(1, price);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t\t");
                }
                System.out.println();

                while (resultSet.next()) {
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        System.out.print(resultSet.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void showFlats() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from FlatsDB")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t\t");
                }
                System.out.println();

                while (resultSet.next()) {
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        System.out.print(resultSet.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
