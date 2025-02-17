import java.sql.*;

public class RenterManager {

    public void saveRenter(Renter renter) {
        // Kalder på saveZipIfNotExists metoden
        int zipId = saveZipIfNotExists(renter.getZip().getZipCode(), renter.getZip().getCity());

        // Gemmer nu zip uanset om den eksisterer eller ej
        String query = "INSERT INTO Renter (f_name, l_name, address, zip, m_number, p_number, email, driverslicence_id, driver_since) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, renter.getF_name());
            stmt.setString(2, renter.getL_name());
            stmt.setString(3, renter.getAddress());
            stmt.setString(4, renter.getZip().getZipCode());
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


    public int saveZipIfNotExists(String zipCode, String city) {
        int zipId = 0;

        // Tjek med databasen om zipkoden eksisterer
        String query = "SELECT zipCode FROM Zip WHERE zipCode = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, zipCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Hvis zip koden eksisterer, hent den tilsvarende zip
                zipId = rs.getInt("zipCode");
            } else {
                // Hvis zup ikke findes, insert den ind i databsen
                String insertQuery = "INSERT INTO Zip (zipCode, city) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setString(1, zipCode);
                    insertStmt.setString(2, city);
                    insertStmt.executeUpdate();
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        zipId = generatedKeys.getInt(1); // auto genereret zip id
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zipId;
    }

}
