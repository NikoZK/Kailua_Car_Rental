import java.sql.*;

public class RenterManager {


    public void showAllRenters(){
        String sql = "SELECT * FROM renter";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("Renter List: ");
                while(rs.next()){
                    System.out.println("Name: " + rs.getString("f_name") + " " + rs.getString("l_name")
                    + ". \uD83C\uDFE0Address: " + rs.getString("address") + ", " + rs.getString("zip")
                            + ". \uD83D\uDCC7Contact information, number: " + rs.getString("m_number") +" and email: " + rs.getString("email")
                            + ". \uD83E\uDEAADrivers ID: " + rs.getString("driverslicence_id") + ", Driving since: " + rs.getString("driver_since"));
                }

        }catch(SQLException e){
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return zip;
    }



}
