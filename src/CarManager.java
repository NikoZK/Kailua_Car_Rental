import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarManager {

    private void addCar() {
        String sql = "INSERT INTO Car (brand, model, fuel_type, is_automatic, have_leatherseats, have_aircondition, have_cruisecontrol, seats, horsepower, cc, reg_number, reg_date, odometer, cargroup_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, brand);
            stmt.setString(2, model);
            stmt.setString(3, fuelType.toString());
            stmt.setBoolean(4, isAutomatic);
            stmt.setBoolean(5, leatherSeats);
            stmt.setBoolean(6, airCondition);
            stmt.setBoolean(7, cruiseControl);
            stmt.setInt(8, seats);
            stmt.setInt(9, horsepower);
            stmt.setInt(10, cc);
            stmt.setString(11, regNumber);
            stmt.setDate(12, new java.sql.Date(regDate.getTime()));
            stmt.setInt(13, odometer);
            stmt.setInt(14, cargroupId);  // Store the CarGroup ID here

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
