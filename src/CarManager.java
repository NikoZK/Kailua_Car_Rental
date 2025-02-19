import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarManager {
Scanner scanner = new Scanner(System.in);

    public void showAllCars(){
        String sql = "SELECT * FROM car";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("\nAll our available cars\n");
            while(rs.next()){
                while(rs.next()){
                    System.out.println("Model: " + rs.getString("brand") + " " + rs.getString("model")
                    +" - Car ID " + rs.getString("car_id")+".");
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteCar(int deleteCarId){
        String sql = "DELETE FROM car WHERE car_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,deleteCarId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("The car has been deleted.");
            }else{
                System.out.println("No car with the id: " + deleteCarId + " was found.");
            }
        } catch (SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editRegNumber() {
        System.out.println("Enter the registration number of the vehicle you want to change.");
        String oldRegNumber = scanner.nextLine();

        System.out.println("Enter the new registration number");
        String newRegNumber = scanner.nextLine();

        String sql = "UPDATE car SET reg_number = ? WHERE reg_number = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newRegNumber);
            stmt.setString(2, oldRegNumber);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registration number updated");
            } else {
                System.out.println("The registration number: " + oldRegNumber + "was not found.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showLuxuryCars(){
        String sql = "SELECT * FROM car WHERE cargroup_id = 1";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("\nAll of our luxury cars have automatic gear, air condition, cruise control and even leather seats.\n");
            while(rs.next()){
                while(rs.next()){
                    System.out.println(" Model: " + rs.getString("brand") + " " + rs.getString("model")
                    + ", Engine size: " + rs.getString("cc"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showFamilyCars(){
        String sql = "SELECT * FROM car WHERE cargroup_id = 2";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("\nAll of our family cars have manual gear, air condition and 7 seats or more\n");
            while(rs.next()){
                while(rs.next()){
                    System.out.println("Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + ", seats: " + rs.getString("seats"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showSportsCars(){
        String sql = "SELECT * FROM car WHERE cargroup_id = 3";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("\nAll of our sports cars are manual and push beyond 200 horsepower\n");
            while(rs.next()){
                while(rs.next()){
                    System.out.println("Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + ", Horsepower: " + rs.getString("horsepower"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void addCar(Car car) {
        String sql = "INSERT INTO Car (brand, model, fuel_type, is_automatic, have_leatherseats, have_aircondition, have_cruisecontrol, seats, horsepower, cc, reg_number, reg_date, odometer, cargroup_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getFuelType().toString());
            stmt.setBoolean(4, car.isAutomatic());
            stmt.setBoolean(5, car.isLeatherSeats());
            stmt.setBoolean(6, car.isAirCondition());
            stmt.setBoolean(7, car.isCruiseControl());
            stmt.setInt(8, car.getSeats());
            stmt.setInt(9, car.getHorsepower());
            stmt.setInt(10, car.getCc());
            stmt.setString(11, car.getRegNumber());
            stmt.setDate(12, new java.sql.Date(car.getRegDate().getTime()));
            stmt.setInt(13, car.getOdometer());
            stmt.setInt(14, car.getCargroupId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   /* public void showCars(){
        String sql = "SELECT * FROM car";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("CARS: ");
            while(rs.next()){
                while(rs.next()){
                    System.out.println("Brand: " + rs.getString("brand") + "\nModel: " + rs.getString("model")
                            + "\nFuelType: " + rs.getString("fuel_type") + "\nIs it automatic: "
                            + rs.getString("is_automatic") + "\nLeather seats: "
                            + rs.getString("have_leatherseats") + "\nAir condition: "
                            + rs.getString("have_aircondition") + "\nCruise control available: :"+ rs.getString("have_cruisecontrol")
                            + "\nAmount of seats: "+ rs.getString("seats") + "\nHorsepower: " + rs.getString("horsepower")
                            + "\nEngine size: "+ rs.getString("cc") + "\nRegistation number: " + rs.getString("reg_number")
                            + "\nRegistration date: " + rs.getString("reg_date") + "\nOdometer: "+ rs.getString("odometer"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }*/
}
