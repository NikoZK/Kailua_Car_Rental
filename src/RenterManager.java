import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RenterManager {


    public List<Renter> getAllRenters() {
        List<Renter> renters = new ArrayList<>();
        String sql = "SELECT renter.*, zip.city FROM renter " +
                "JOIN zip ON renter.`zip` = zip.`zip`";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Zip zip = new Zip(rs.getString("zip"), rs.getString("city")); // Create Zip object
                Renter renter = new Renter(
                        rs.getInt("renter_id"),
                        rs.getString("f_name"),
                        rs.getString("l_name"),
                        rs.getString("address"),
                        zip,
                        rs.getString("m_number"),
                        rs.getString("p_number"),
                        rs.getString("email"),
                        rs.getString("driverslicence_id"),
                        rs.getDate("driver_since")
                );
                renters.add(renter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renters;
    }

    public void deleteRenter(int renterId) {
        List<Renter> renters = new ArrayList<>();
        String sql = "DELETE FROM renter WHERE renter_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, renterId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                //Cays iterator
                Iterator<Renter> iterator = renters.iterator();
                while (iterator.hasNext()) {
                    Renter r = iterator.next();
                    if (r.getRenter_id() == renterId) {
                        iterator.remove();
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void modifyRenter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the renter ID you want to modify:");
        int renterId = Integer.parseInt(scanner.nextLine());


        System.out.println("Which field do you want to update?");
        System.out.println("1. First name\n2. Last name\n3. Address\n4. Mobile number\n5. Home phone number\n6. Email address\n7. Drivers licence ID\n8. Driver since date\n9. Return");
        int choice = Integer.parseInt(scanner.nextLine());

        String field = "";
        String newValue = "";
        switch (choice) {
            case 1:
                field = "f_name";
                System.out.println("Edit first name:");
                newValue = scanner.nextLine();
                break;
            case 2:
                field = "l_name";
                System.out.println("Edit last name:");
                newValue = scanner.nextLine();
                break;
            case 3:
                field = "address";
                System.out.println("Enter new address:");
                newValue = scanner.nextLine();
                break;
            case 4:
                field = "m_number";
                System.out.println("Enter new mobile number:");
                newValue = scanner.nextLine();
                break;
            case 5:
                field = "p_number";
                System.out.println("Enter new home phone number:");
                newValue = scanner.nextLine();
                break;
            case 6:
                field = "email";
                System.out.println("Enter new email address:");
                newValue = scanner.nextLine();
                break;
            case 7:
                field = "driverslicence_id";
                System.out.println("Enter new driver ID:");
                newValue = scanner.nextLine();
                break;
            case 8:
                field = "driver_since";
                System.out.println("Edit 'driver since' date (YYYY-MM-DD):");
                newValue = scanner.nextLine();
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        String sql = "UPDATE renter SET " + field + " = ? WHERE renter_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newValue);
            stmt.setInt(2, renterId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Renter updated successfully!");
            } else {
                System.out.println("Renter not found.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }




    public void saveRenter(Renter renter) {
        // Kalder på saveZipIfNotExists metoden
        String zip = saveZipIfNotExists(renter.getZip().getZipCode(), renter.getZip().getCity());

        // Gemmer nu zip uanset om den eksisterer eller ej
        String query = "INSERT INTO Renter (f_name, l_name, address, zip, m_number, p_number, email, driverslicence_id, driver_since) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, renter.getF_name());
            stmt.setString(2, renter.getL_name());
            stmt.setString(3, renter.getAddress());
            stmt.setString(4, zip);
            stmt.setString(5, renter.getM_number());
            stmt.setString(6, renter.getP_number());
            stmt.setString(7, renter.getEmail());
            stmt.setString(8, renter.getDriverslicence_id());
            //sql.date og java.date er ikke ens, så den her linje er påkrævet
            stmt.setDate(9, new java.sql.Date(renter.getDriver_since().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public String saveZipIfNotExists(String zipCode, String city) {
        String zip = null;

        // Check om zip findes i databasen
        String query = "SELECT zip FROM Zip WHERE zip = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, zipCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Hvis zip findes, brug den
                zip = rs.getString("zip");
            } else {
                // Hvis zip ikke findes, lav den og indsæt ind i databasen
                String insertQuery = "INSERT INTO Zip (zip, city) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, zipCode);
                    insertStmt.setString(2, city);
                    insertStmt.executeUpdate();
                    zip = zipCode; // Returner den nye zip
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return zip;
    }

    public Renter getRenterById(int renterId) {
        String sql = "SELECT r.*, z.city FROM renter r \n" +
                "JOIN zip z ON r.zip = z.zip\n" +
                "WHERE r.renter_id = ?;\n";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, renterId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Renter(
                        rs.getInt("renter_id"),
                        rs.getString("f_name"),
                        rs.getString("l_name"),
                        rs.getString("address"),
                        new Zip(rs.getString("zip"), rs.getString("city")),
                        rs.getString("m_number"),
                        rs.getString("p_number"),
                        rs.getString("email"),
                        rs.getString("driverslicence_id"),
                        rs.getDate("driver_since")
                );
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

}
