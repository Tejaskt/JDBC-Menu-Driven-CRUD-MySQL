package dbConnection;

import java.sql.*;
import java.util.Scanner;

class con {
    public static Connection config() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            System.out.println("Connected");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Crud {
    static Connection connection = con.config();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 0 => exit & 1 => Continue: ");
        int out = sc.nextInt();

        while (out != 0) {

            System.out.print("Enter 1 => Insert || " +
                    "Enter 2 => Select:\n" +
                    "Enter 3 => Delete || " +
                    "Enter 4 => Update:\n \t Here=>");

            int in = sc.nextInt();
            switch (in) {

                case 1: // Insert

                    con.config();
                    Fun_insert();

                    break;

                case 2: // Select
                    con.config();
                    Fun_select();
                    break;

                case 3: // Delete
                    con.config();
                    Fun_Delete();
                    break;

                case 4: // Update
                    con.config();
                    Fun_Update();
                    break;


                default:
                    throw new IllegalStateException("Unexpected value: " + in);
            }


            System.out.print("Enter 0 => exit & 1 => Continue: ");
            out = sc.nextInt();

        }


    }

    private static void Fun_Update() {
        try {
            Scanner read1 = new Scanner(System.in);
            Scanner read2 = new Scanner(System.in);
            Scanner read3 = new Scanner(System.in);


            System.out.print("Enter Id: ");
            int id = read1.nextInt();

            System.out.print("Enter Username: ");
            String unm = read3.nextLine();

            System.out.print("Enter Password: ");
            String pwd = read2.nextLine();

            String update = "UPDATE admin set unm='" + unm + "' , pwd='" + pwd + "' where id=" + id;
            Statement st = connection.createStatement();
            st.executeUpdate(update);
            st.close();
            System.out.println("Record Updated!!!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void Fun_Delete() {
        try {
            Scanner inId = new Scanner(System.in);

            System.out.print("Enter Id: ");
            int id = inId.nextInt();

            String delete = "delete from admin where id=" + id;
            Statement st = connection.createStatement();
            st.executeUpdate(delete);
            st.close();
            System.out.println("Record Deleted!!!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Fun_select() {
        try {
            Statement st = connection.createStatement();
            String select = "Select * from admin";
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                int id = rs.getInt("id");
                String unm = rs.getString("unm");
                String pwd = rs.getString("pwd");

                System.out.println("Id: " + id + "\t\t User Name: " + unm + "\t\t Password: " + pwd);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void Fun_insert() {
        try {
            Scanner read = new Scanner(System.in);

            System.out.print("Enter Username: ");
            String unm = read.nextLine();
            System.out.print("Enter Password: ");
            String pwd = read.nextLine();

            String insert = "insert into admin(unm , pwd) values('" + unm + "' , '" + pwd + "')";
            Statement st = connection.createStatement();
            st.executeUpdate(insert);
            st.close();
            System.out.println("Record inserted!!!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
