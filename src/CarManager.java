import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CarManager {


    public void showAllCars(){
        String sql = "SELECT * FROM car";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            System.out.println("CARS: ");
            while(rs.next()){
                while(rs.next()){
                    System.out.println("Brand and model: " + rs.getString("brand") + " " + rs.getString("model"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showCars(){
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
}
