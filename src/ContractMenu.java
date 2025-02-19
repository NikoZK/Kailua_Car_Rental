import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ContractMenu {
Scanner scanner = new Scanner(System.in);
ContractManager ctm = new ContractManager();
RenterManager rm = new RenterManager();
CarManager cm = new CarManager();

    public void Contract_Menu() {

        while (true) {
            System.out.println(" ");
            System.out.println(" ██████  ██████  ███    ██ ████████ ██████   █████   ██████ ████████     ███    ███ ███████ ███    ██ ██    ██ \n" +
                    "██      ██    ██ ████   ██    ██    ██   ██ ██   ██ ██         ██        ████  ████ ██      ████   ██ ██    ██ \n" +
                    "██      ██    ██ ██ ██  ██    ██    ██████  ███████ ██         ██        ██ ████ ██ █████   ██ ██  ██ ██    ██ \n" +
                    "██      ██    ██ ██  ██ ██    ██    ██   ██ ██   ██ ██         ██        ██  ██  ██ ██      ██  ██ ██ ██    ██ \n" +
                    " ██████  ██████  ██   ████    ██    ██   ██ ██   ██  ██████    ██        ██      ██ ███████ ██   ████  ██████  \n" +
                    "                                                                                                               \n" +
                    "                                                                                                               ");

            System.out.println("                                    Press 1 - See all active contracts");
            System.out.println("                                    Press 2 - Write a contract");
            System.out.println("                                    Press 3 - Delete a contract");
            System.out.println("                                    Press 4 - Conclude a contract");
            System.out.println("                                    Press 5 - See contract history");
            System.out.println(" ");
            System.out.println("                                    Press 9 - Back to main menu");
            System.out.println("                                    Press 0 - exit the program\n");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    ctm.seeAllActiveContracts();
                    break;

                case 2:
                    System.out.println("Enter the renters id: ");
                    int renter_id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter the car id: ");
                    int car_id = scanner.nextInt();
                    scanner.nextLine();

                    Renter renter = rm.getRenterById(renter_id);
                    Car car = cm.getCarById(car_id);

                    System.out.println("Enter start date date: (YYYY-MM-DD): ");
                    String start_date_timeInput = scanner.nextLine();
                    Date start_date_time = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        start_date_time = dateFormat.parse(start_date_timeInput);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format, remember to use YYYY-MM-DD");
                        return;
                    }

                    System.out.println("Enter the end date: (YYYY-MM-DD): ");
                    String end_date_timeInput = scanner.nextLine();
                    Date end_date_time = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        end_date_time = dateFormat.parse(end_date_timeInput);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format, remember to use YYYY-MM-DD");
                        return;
                    }

                    System.out.println("Enter the max kilometers: ");
                    int max_km = scanner.nextInt();
                    scanner.nextLine();

                    int odometer_start = car.getOdometer();

                    cm.updateCarAvailability(car_id, false);

                    Contract contract = new Contract(0, renter, car, start_date_time, end_date_time, max_km, odometer_start, 0, Status.ACTIVE);

                    ctm.writeContract(contract);
                    break;

                case 3:
                    System.out.println("Enter the the contract id you want to delete: ");
                    int contractid = scanner.nextInt();
                    ctm.deleteContract(contractid);
                    break;

                case 4:
                    System.out.println("Enter contract id: ");
                    int contractId = scanner.nextInt();

                    System.out.println("Enter the odometer reading: ");
                    int odometer_end = scanner.nextInt();

                    ctm.concludeContract(odometer_end, contractId);
                    break;

                case 5:
                    ctm.seeAllContracts();
                    break;

                case 9:
                    Menu menu = new Menu();
                    menu.displayMenu();

                case 0:
                    System.exit(0);
            }
        }
    }
}