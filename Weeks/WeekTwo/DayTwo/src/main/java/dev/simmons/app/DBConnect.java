package dev.simmons.app;

import java.sql.*;

public class DBConnect {
    public static void main(String[] args) {
        System.out.println("Hello");

        try {

            String password = "pwd";
            String username = "user";
            String urlAWS = "jdbc:postgresql://training.clctugyl5h5z.us-east-1.rds.amazonaws.com/postgres?username=" + username + "?password=" + password;
            String urlLocal = "jdbc:postgresql://localhost:5432/postgres?username=" + username + "?password=" + password;
            Connection db = DriverManager.getConnection(urlLocal);

            Statement st = db.createStatement();
            st.execute("insert into employee (first_name, last_name, salary) values ('Testing', 'Test', 4000);");
            st.close();

            st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee");
            

            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.println(rs.getString(4));
            }

            rs.close();
            st.close();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getStackTrace());
        }

    }

}