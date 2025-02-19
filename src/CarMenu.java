import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CarMenu {
    private Scanner scanner = new Scanner(System.in);
    CarManager cm = new CarManager();

    public void Car_Menu() {

        while (true) {
            System.out.println(" ");
            System.out.println(" ██████  █████  ██████      ███    ███ ███████ ███    ██ ██    ██ \n" +
                    "██      ██   ██ ██   ██     ████  ████ ██      ████   ██ ██    ██ \n" +
                    "██      ███████ ██████      ██ ████ ██ █████   ██ ██  ██ ██    ██ \n" +
                    "██      ██   ██ ██   ██     ██  ██  ██ ██      ██  ██ ██ ██    ██ \n" +
                    " ██████ ██   ██ ██   ██     ██      ██ ███████ ██   ████  ██████  \n" +
                    "                                                                  \n" +
                    "                                                                  ");

            System.out.println("                 Press 1 - See cars");
            System.out.println("                 Press 2 - Add a car");
            System.out.println("                 Press 3 - Remove a car");
            System.out.println("                 Press 4 - See all information about a car");
            System.out.println("                 Press 5 - Adjust the odometer");
            System.out.println("                 Press 6 - Change the registration number");
            System.out.println(" ");
            System.out.println("                 Press 9 - Back to main menu");
            System.out.println("                 Press 0 - exit the program\n");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    seeCarsMenu();
                    break;

                case 2:
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();

                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();

                    System.out.print("Enter fuel type (Gasoline/Diesel/Electric/Hybrid): ");
                    String fuelTypeInput = scanner.nextLine();
                    FuelType fuelType = FuelType.valueOf(fuelTypeInput.toUpperCase());

                    System.out.print("Is the car automatic? (true/false): ");
                    boolean isAutomatic = scanner.nextBoolean();

                    System.out.print("Does it have leather seats? (true/false): ");
                    boolean leatherSeats = scanner.nextBoolean();

                    System.out.print("Does it have air conditioning? (true/false): ");
                    boolean airCondition = scanner.nextBoolean();

                    System.out.print("Does it have cruise control? (true/false): ");
                    boolean cruiseControl = scanner.nextBoolean();

                    System.out.print("Enter number of seats: ");
                    int seats = scanner.nextInt();

                    System.out.print("Enter horsepower: ");
                    int horsepower = scanner.nextInt();

                    System.out.print("Enter engine size (cc): ");
                    int cc = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter registration number: ");
                    String regNumber = scanner.nextLine();

                    System.out.println("Enter registration date: (YYYY-MM-DD): ");
                    String regDateInput = scanner.nextLine();
                    Date regDate = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        regDate = dateFormat.parse(regDateInput);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format, remember to use YYYY-MM-DD");
                        return;
                    }

                    System.out.print("Enter odometer reading: ");
                    int odometer = scanner.nextInt();

                    boolean available = true;

                    System.out.println("The car has successfully been added to the system!");

                    Car car = new Car(0, brand, model, fuelType, isAutomatic, airCondition, cruiseControl,
                            leatherSeats, horsepower, cc, seats, regNumber, regDate, odometer, available);

                    cm.addCar(car);
                    break;

                case 3:
                    System.out.println("Enter the car id of the car you want to delete");
                    int deleteCarId = scanner.nextInt();
                    cm.deleteCar(deleteCarId);
                    break;

                case 4:
                    cm.showCarInfo();
                    break;

                case 5:
                    cm.updateOdometer();
                    break;

                case 6:
                    cm.editRegNumber();
                    break;

                case 9:
                    Menu menu = new Menu();
                    menu.displayMenu();
                case 0:
                    System.exit(1);
            }
        }
    }

    public void seeCarsMenu() {
        while (true) {
            System.out.println("\nChoose a category: ");
            System.out.println("Press 1 - see all cars");
            System.out.println("Press 2 - see all luxury cars");
            System.out.println("Press 3 - see all family cars");
            System.out.println("Press 4 - see all sports cars");
            System.out.println("Press 9 - return");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    cm.showAllCars();
                    break;

                case 2:
                    cm.showLuxuryCars();
                    break;

                case 3:
                    cm.showFamilyCars();
                    break;

                case 4:
                    cm.showSportsCars();
                    break;

                case 9:
                    Car_Menu();
                    break;
            }
        }
    }
}