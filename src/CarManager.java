import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarManager {
    Scanner scanner = new Scanner(System.in);

    public void showAllCars() {
        String sql = "SELECT * FROM car";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nAll our available cars\n");
            while (rs.next()) {
                    System.out.println("[ Car ID " + rs.getString("car_id") + " ] [ Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + " ] [ Registration number: " + rs.getString("reg_number") + " ] " + (rs.getBoolean("available") ? "Available" : "Not Available"));
                }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteCar(int deleteCarId) {
        String sql = "DELETE FROM car WHERE car_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deleteCarId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The car has been deleted.");
            } else {
                System.out.println("No car with the id: " + deleteCarId + " was found.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void updateOdometer() {
        System.out.println("Enter the id of the car you would like to update:");
        String car_id = scanner.nextLine();

        System.out.println("The current odometer reading of the " + getCarById(Integer.parseInt(car_id)).getBrand() + " " + getCarById(Integer.parseInt(car_id)).getModel() + " is: " + getCarById(Integer.parseInt(car_id)).getOdometer());

        System.out.println("Enter the new odometer reading:");
        int newOdometer = Integer.parseInt(scanner.nextLine());

        String sql = "UPDATE car SET odometer = ? WHERE car_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newOdometer);
            stmt.setString(2, car_id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Odometer updated successfully.");
            } else {
                System.out.println("Car not found.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            ;
        }
    }


    public void editRegNumber() {
        System.out.println("Enter the registration number of the vehicle you want to change:");
        String oldRegNumber = scanner.nextLine();

        System.out.println("Enter the new registration number:");
        String newRegNumber = scanner.nextLine();

        String sql = "UPDATE car SET reg_number = ? WHERE reg_number = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newRegNumber);
            stmt.setString(2, oldRegNumber);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registration number updated.");
            } else {
                System.out.println("The registration number: " + oldRegNumber + "was not found.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showLuxuryCars() {
        String sql = "SELECT * FROM car WHERE cargroup_id = 1";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nAll of our luxury cars have automatic gear, air condition, cruise control and even leather seats.\n");

                while (rs.next()) {
                    String availableText = rs.getBoolean("available") ? " " : " [ Not Available ]";
                    System.out.println(" Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + ", Engine size: " + rs.getString("cc") + "." + availableText);
                }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showFamilyCars() {
        String sql = "SELECT * FROM car WHERE cargroup_id = 2";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nAll of our family cars have manual gear, air condition and 7 seats or more\n");

            while (rs.next()) {
                String availableText = rs.getBoolean("available") ? " " : " [ Not Available ]";
                    System.out.println("Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + ", seats: " + rs.getString("seats") + "." + availableText);
                }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showSportsCars() {
        String sql = "SELECT * FROM car WHERE cargroup_id = 3";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\nAll of our sports cars are manual and push beyond 200 horsepower\n");

            while (rs.next()) {
                String availableText = rs.getBoolean("available") ? " " : " [ Not Available ]";
                    System.out.println("Model: " + rs.getString("brand") + " " + rs.getString("model")
                            + ", Horsepower: " + rs.getString("horsepower") + "." + availableText);
            }
        } catch (SQLException e) {
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
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public void showCarInfo() {
        System.out.println("Enter the id of the car:");
        int car_id = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT * FROM car WHERE car_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, car_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("_________________________________");
                System.out.println("Brand: " + rs.getString("brand") +
                        "\nModel: " + rs.getString("model") +
                        "\nFuel Type: " + rs.getString("fuel_type") +
                        "\nIs it automatic: " + (rs.getBoolean("is_automatic") ? "Yes" : "No") +
                        "\nLeather seats: " + (rs.getBoolean("have_leatherseats") ? "Yes" : "No") +
                        "\nAir condition: " + (rs.getBoolean("have_aircondition") ? "Yes" : "No") +
                        "\nCruise control available: " + (rs.getBoolean("have_cruisecontrol") ? "Yes" : "No") +
                        "\nAmount of seats: " + rs.getInt("seats") +
                        "\nHorsepower: " + rs.getInt("horsepower") +
                        "\nEngine size: " + rs.getInt("cc") + " cc" +
                        "\nRegistration number: " + rs.getString("reg_number") +
                        "\nRegistration date: " + rs.getDate("reg_date") +
                        "\nOdometer: " + rs.getInt("odometer") + " km" +
                        "\nAvailability: " + (rs.getBoolean("available") ? "Available" : "Not available"));
                System.out.println("_________________________________\n");
            } else {
                System.out.println("No car found with ID: " + car_id);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public Car getCarById(int carId) {
        String sql = "SELECT * FROM car WHERE car_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        FuelType.valueOf(rs.getString("fuel_type").toUpperCase()), // String til ENUM
                        rs.getBoolean("is_automatic"),
                        rs.getBoolean("have_leatherseats"),
                        rs.getBoolean("have_aircondition"),
                        rs.getBoolean("have_cruisecontrol"),
                        rs.getInt("seats"),
                        rs.getInt("horsepower"),
                        rs.getInt("cc"),
                        rs.getString("reg_number"),
                        rs.getDate("reg_date"),
                        rs.getInt("odometer"),
                        rs.getBoolean("available")
                );
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public void updateCarAvailability(int carId, boolean available) {
        String sql = "UPDATE car SET available = ? WHERE car_id = ?";

    try (Connection conn = Database.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql)){

        stmt.setBoolean(1, available);
        stmt.setInt(2, carId);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Car availability updated successfully.");
        } else {
            System.out.println("Car not found.");
        }
    } catch (SQLException e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}


}
