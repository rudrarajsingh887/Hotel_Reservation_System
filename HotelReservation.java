import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelReservation
{
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username  = "root";
    private static final String password = "PASSWORD";

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            while (true) {
                System.out.println();
                System.out.println("Welcome to Hotel Reservation Management System");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Reserve A Room: 1 ");
                System.out.println("View Reservations: 2 ");
                System.out.println("Get Room Number: 3 ");
                System.out.println("Update Reservations: 4");
                System.out.println("Delete Reservations: 5");
                System.out.println("Exit: 0");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, scanner);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, scanner);
                        break;
                    case 4:
                        updateReservation(connection, scanner);
                        break;
                    case 5:
                        deleteReservation(connection, scanner);
                        break;
                    case 0:
                        exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static void reserveRoom(Connection connection,Scanner scanner)
    {
        try
        {
            System.out.print("Enter Guest Name:  ");
            String guestName=scanner.next();
            scanner.nextLine();
            System.out.print("Enter Room Number:  ");
            int roomNumber = scanner.nextInt();
            System.out.print("Enter Contact Number:  ");
            String contactNumber = scanner.next();

            String sql = "Insert Into reservations(guest_name,room_number,contact_number)"+"Values('"+guestName+"',"+roomNumber+",'"+contactNumber+"')";

            try(Statement statement = connection.createStatement())
            {
                int affectedRows = statement.executeUpdate(sql);

                if(affectedRows>0)
                {
                    System.out.println("Reservation Successful !");
                }
                else{
                    System.out.println("Reservation failed !");
                }
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection connection ) throws SQLException
    {
        String sql = "Select reservation_id,guest_name,room_number,contact_number,reservation_date From reservations";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql))
        {
            System.out.println("Current Reservations:");
            System.out.println("+__________________+__________________+__________________+___________________+");
            System.out.println("| Reservation ID   | Guest            | Room Number      | Reservation Date |");
            System.out.println("+__________________+__________________+__________________+___________________+");

            while (resultSet.next())
            {
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-50s | \n" , reservationId,guestName , roomNumber , contactNumber , reservationDate);

            }
            System.out.println("+__________________+__________________+__________________+___________________+");
        }
    }
    private static void getRoomNumber(Connection connection , Scanner scanner)
    {
        try{
            System.out.print("Enter Reservation ID: ");
            int reservationId = scanner.nextInt();
            System.out.print("Enter Guest Name: ");
            String guestName = scanner.next();

            String sql = "Select room_number From reservations "+" Where reservation_id = " + reservationId + " And guest_name = '" + guestName + "'";

            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)){
                if (resultSet.next())
                {
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room number for Reservation ID" + reservationId + " and Guest " + guestName + " is: " + roomNumber);

                }
                else {
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        }catch(SQLException e){
            e.fillInStackTrace();

        }
    }
    private static void updateReservation(Connection connection , Scanner scanner)
    {
        try{
            System.out.print("Enter reservation ID to update: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();

            if(!reservationExists(connection,reservationId))
            {
                System.out.println("Reservation not found for the given ID");
                return;
            }
            System.out.print("Enter new guest name: ");
            String newGuestName = scanner.nextLine();
            System.out.print("Enter new room number: ");
            int newRoomNumber = scanner.nextInt();
            System.out.print("Enter new contact number: ");
            String newContactNumber = scanner.next();

            String sql = "Update reservations Set guest_name = '" + newGuestName + "'," + " room_number = "+ newRoomNumber + ", " + " contact_number = '" + newContactNumber + "' " + " Where reservation_id = " + reservationId ;
            try(Statement statement  = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);
                if (affectedRows > 0) {
                    System.out.println("Reservation updated successfully ! ");
                } else {
                    System.out.println("Reservation update failed.");
                }
            }

        }
        catch(SQLException e)
        {
            e.fillInStackTrace();
        }
    }
    private static void deleteReservation(Connection connection ,Scanner scanner)
    {
        try{
            System.out.print("Enter reservation ID to Delete: ");
            int reservationId = scanner.nextInt();

            if(!reservationExists(connection , reservationId)){
                System.out.println("Reservation not found for the given ID. ");
                return;
            }
            String sql = "Delete From reservations Where reservation_id = "+reservationId;

            try(Statement statement = connection.createStatement())
            {
                int affectedRows = statement.executeUpdate(sql);
                if (affectedRows > 0)
                {
                    System.out.println("Reservation deleted successfully !");

                }
                else {
                    System.out.println("Reservation deletion failed !");
                }
            }
        }catch(SQLException e)
        {

            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection connection, int reservationId)
    {
        try{
            String sql = "Select reservation_id From reservations Where reservation_id = "+reservationId;
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql))
            {
                return resultSet.next();
            }
        }
        catch (SQLException e)
        {
            e.fillInStackTrace();
            return false;
        }
    }

    private static void exit() throws InterruptedException
    {
        System.out.print("Exiting the system");
        int i = 5;
        while(i!=0)
        {
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("Thank You for using Hotel Reservation System !!!!");
    }
}
