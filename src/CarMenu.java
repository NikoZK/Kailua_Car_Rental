import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CarMenu {
    private Scanner scanner = new Scanner(System.in);
    CarManager cm = new CarManager();

    public void Car_Menu(){

        while(true){
            System.out.println(" ██████  █████  ██████      ███    ███ ███████ ███    ██ ██    ██ \n" +
                    "██      ██   ██ ██   ██     ████  ████ ██      ████   ██ ██    ██ \n" +
                    "██      ███████ ██████      ██ ████ ██ █████   ██ ██  ██ ██    ██ \n" +
                    "██      ██   ██ ██   ██     ██  ██  ██ ██      ██  ██ ██ ██    ██ \n" +
                    " ██████ ██   ██ ██   ██     ██      ██ ███████ ██   ████  ██████  \n" +
                    "                                                                  \n" +
                    "                                                                  ");

            System.out.println("Press 1 to see all available cars");
            System.out.println("Press 2 to add a car to the system");
            System.out.println("Press 3 to remove a car from the system");
            System.out.println("Press 4 adjust the odometer");
            System.out.printf("Press 0 to exit\n");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch(valg){
                case 1:
                    System.out.println("List of all our cars: ");
                    cm.showAllCars();
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
                    try{
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        regDate = dateFormat.parse(regDateInput);
                    } catch(ParseException e){
                        System.out.println("Invalid date format, remember to use YYYY-MM-DD");
                        return;
                    }

                    System.out.print("Enter odometer reading: ");
                    int odometer = scanner.nextInt();

                    Car car = new Car(0, brand, model, fuelType, isAutomatic, airCondition, cruiseControl,
                            leatherSeats, horsepower, cc, seats, regNumber, regDate, odometer);

                    cm.addCar(car);
                    break;

                case 0:
                    Menu menu = new Menu();
                    menu.displayMenu();
            }
        }
    }
}
