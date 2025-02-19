import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractManager {
CarManager cm = new CarManager();

    public void writeContract(Contract contract) {
        String sql = "INSERT INTO contract (renter_id, car_id, start_date_time, end_date_time, max_km, odometer_start, odometer_end, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, NULL, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contract.getRenter().getRenter_id());
            stmt.setInt(2, contract.getCar().getCarId());
            stmt.setDate(3, new java.sql.Date(contract.getStart_date_time().getTime()));
            stmt.setDate(4, new java.sql.Date(contract.getEnd_date_time().getTime()));
            stmt.setInt(5, contract.getMax_km());
            stmt.setInt(6, contract.getOdometer_start());
            stmt.setString(7, Status.ACTIVE.toString());

            stmt.executeUpdate();
            System.out.println("Contract successfully created.");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void concludeContract(int odometer_end, int contractId) {
        String sql = "UPDATE contract SET odometer_end = ?, status = ? WHERE contract_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, odometer_end);
            stmt.setString(2, Status.COMPLETED.toString());
            stmt.setInt(3, contractId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contract " + contractId + " successfully concluded.");
                AutoupdateCarOdometer(contractId, odometer_end);
                int carid = getCarIdFromContract(contractId);
                cm.updateCarAvailability(carid, true);


            } else {
                System.out.println("Contract " + contractId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void AutoupdateCarOdometer(int contractId, int odometer_end) {
        String sql = "UPDATE car SET odometer = ? WHERE car_id = (SELECT car_id FROM contract WHERE contract_id = ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, odometer_end);
            stmt.setInt(2, contractId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" \n");
            } else {
                System.out.println("Car not found or update failed.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private int getCarIdFromContract(int contractId) {
        String sql = "SELECT car_id FROM contract WHERE contract_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contractId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("car_id");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return -1;
    }

    public void seeAllActiveContracts(){
        String sql = "SELECT * FROM contract WHERE status = 'Active'";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nActive contracts\n");
            while (rs.next()) {
                    System.out.println("[ Contract ID: " + rs.getInt("contract_id") + ", Renter ID: " + rs.getInt("renter_id")
                            + ", Car ID: " + rs.getInt("car_id")  + " ] [ Start date: "
                            + rs.getDate("start_date_time") + " ] [ Max kilometers given: " + rs.getInt("max_km")
                            + ", Odometer: " + rs.getInt("odometer_start") + " ]");

            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public void seeAllContracts(){
        String sql = "SELECT * FROM contract";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nContract history\n");
            while (rs.next()) {

                    System.out.println("[ Contract ID: " + rs.getInt("contract_id") + ", Renter ID: " + rs.getInt("renter_id")
                            + ", Car ID: " + rs.getInt("car_id")  + " ] [ Start and end dates: "
                            + rs.getDate("start_date_time") + " to " + (rs.getDate("end_date_time"))
                            + " ] [ Max kilometers given: " + rs.getInt("max_km") + ", kilometers driven: "
                            + rs.getInt("odometer_start") + " to " + (rs.getInt("odometer_end")) + " ]");

            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteContract(int deleteContractId) {
        String sql = "DELETE FROM contract WHERE contract_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deleteContractId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The contract has been deleted.");
            } else {
                System.out.println("No contract with the id: " + deleteContractId + " was found.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    }






