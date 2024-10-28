import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        String databaseURL = "jdbc:mysql://localhost:3306/test?user=root&password=";
        String databaseName = "Mahasiswa";
        Connection conn = null;
        try { //if connected
            conn = DriverManager.getConnection(databaseURL);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
            Statement stmt = conn.createStatement();
            String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + databaseName + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Database " + databaseName + " exists");
            } else {
                System.out.println("Database " + databaseName + " does not exist");
                ArrayList<String> createDatabaseQuery = new ArrayList<String>();
                createDatabaseQuery.add("CREATE DATABASE " + databaseName);
                createDatabaseQuery.add("USE " + databaseName);
                createDatabaseQuery.add("CREATE TABLE Mahasiswa (\n" + "NPM int auto_increment primary key,\n" + "nama_lengkap varchar (255),\n" + "email varchar(255),\n" + "alamat text,\n" + "no_telp varchar(255)\n" + ");");
                for (int i = 0; i < createDatabaseQuery.size(); i++) {
                    stmt.executeUpdate(createDatabaseQuery.get(i));
                }
                System.out.println("Database " + databaseName + " created successfully");
            }
            System.out.println("Welcome to Mahasiswa Database⚙️");
            boolean programRun = true;
            while (programRun) {
                System.out.println("Input Command");
                System.out.println("1. Create mahasiswa profile");
                System.out.println("2. Show Mahasiswa table");
                System.out.println("3. Update Mahasiswa profile");
                System.out.println("4. Delete Mahasiswa profile");
                System.out.println("5. Exit");
                System.out.print("Enter Your Choice: ");
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println("Change Profile 🧍‍♂️");
                            System.out.print("Enter Nama_lengkap: ");
                            Scanner nama_lengkap_input = new Scanner(System.in);
                            String nama_lengkap = nama_lengkap_input.nextLine();
                            System.out.print("Enter Email: ");
                            Scanner email_input = new Scanner(System.in);
                            String email = email_input.nextLine();
                            System.out.print("Enter Alamat: ");
                            Scanner alamat_input = new Scanner(System.in);
                            String alamat = alamat_input.nextLine();
                            System.out.print("Enter No_telp: ");
                            Scanner no_telp_input = new Scanner(System.in);
                            String no_telp = no_telp_input.nextLine();
                            System.out.print("\n");
                            String query1 = "use mahasiswa;";
                            String query2 = "insert into mahasiswa (nama_lengkap, email, alamat, no_telp) values (" + "'" + nama_lengkap + "'" + ", " + "'" + email + "'" + ", " + "'" + alamat + "'" + ", " + "'" + no_telp + "'" + ");";
                            stmt.executeUpdate(query1);
                            stmt.executeUpdate(query2);
                            System.out.println("Mahasiswa profile created successfully");
                            System.out.println("Do you want to add another?\n (y) yes\n (n) no");
                            Scanner addAnother = new Scanner(System.in);
                            String add_another = addAnother.nextLine();
                            if (add_another.equals("y")) {
                            } else {
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Mahasiswa table 🧑‍🎓");
                        stmt.executeUpdate("use mahasiswa;");
                        query = "select * from Mahasiswa";
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            int npm = rs.getInt("NPM");
                            String nama_lengkap = rs.getString("Nama_lengkap");
                            String email = rs.getString("email");
                            String alamat = rs.getString("alamat");
                            String no_telp = rs.getString("no_telp");

                            System.out.println("NPM: " + npm);
                            System.out.println("Nama_lengkap: " + nama_lengkap);
                            System.out.println("Email: " + email);
                            System.out.println("Alamat: " + alamat);
                            System.out.println("No_telp: " + no_telp);
                            System.out.println("-------------------------------------");
                        }
                        break;
                    case 3:
                        System.out.println("Changing Profile 🪪");
                        System.out.print("Enter NPM to Change: ");
                        Scanner NPM_input = new Scanner(System.in);
                        String NPM = NPM_input.nextLine();
                        System.out.println("What do you want to change?\n(1) nama_lengkap\n(2) email\n(3) alamat\n(4) no_telp");
                        System.out.print("Enter Your Choice: ");
                        Scanner change_input = new Scanner(System.in);
                        int change = change_input.nextInt();
                        switch (change) {
                            case 1:
                                System.out.print("Enter new name: ");
                                Scanner name_input = new Scanner(System.in);
                                String name = name_input.nextLine();
                                stmt.executeUpdate("use mahasiswa;");
                                stmt.executeUpdate("update mahasiswa set nama_lengkap = '" + name + "'" + "where npm = " + NPM + ";");
                                System.out.println(NPM + "name changed to" + name + "\n");
                                break;
                            case 2:
                                System.out.print("Enter new email: ");
                                Scanner email_input = new Scanner(System.in);
                                String email = email_input.nextLine();
                                stmt.executeUpdate("use mahasiswa;");
                                stmt.executeUpdate("update mahasiswa set email = '" + email + "' where npm = " + NPM + ";");
                                System.out.println(NPM + "email changed to " + email + "\n");
                                break;
                            case 3:
                                System.out.print("Enter new alamat: ");
                                Scanner alamat_input = new Scanner(System.in);
                                String alamat = alamat_input.nextLine();
                                stmt.executeUpdate("use mahasiswa;");
                                stmt.executeUpdate("update mahasiswa set alamat = '" + alamat + "' where npm = " + NPM + ";");
                                System.out.println(NPM + "alamat changed to '" + alamat + "\n");
                                break;
                            case 4:
                                System.out.print("Enter new no_telp: ");
                                Scanner no_telp_input = new Scanner(System.in);
                                String no_telp = no_telp_input.nextLine();
                                stmt.executeUpdate("use mahasiswa;");
                                stmt.executeUpdate("update mahasiswa set no_telp = '" + no_telp + "' where npm = " + NPM + ";");
                                System.out.println("no_telp for NPM" + NPM + "has been changed to" + no_telp + "\n");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Delete Profile ❌");
                        System.out.print("Enter NPM to delete: ");
                        Scanner NPMtoDelete_input = new Scanner(System.in);
                        String NPMtoDelete = NPMtoDelete_input.nextLine();
                        stmt.executeUpdate("use mahasiswa;");
                        stmt.executeUpdate("delete from mahasiswa where NPM = " + NPMtoDelete + ";");
                        System.out.println("Mahasiswa profile with NPM " + NPMtoDelete + " deleted successfully\n");
                        break;
                    case 5:
                        System.out.print("Exiting program... 😇");
                        System.exit(0);
                }
            }


        } catch (SQLException ex) { //if error
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally { //if connection closed
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
