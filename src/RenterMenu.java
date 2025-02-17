import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RenterMenu {
    private Scanner scanner = new Scanner(System.in);
    RenterManager rm = new RenterManager();

    public void renter_Menu() {

        while (true) {
            System.out.println("██████  ███████ ███    ██ ████████ ███████ ██████      ███    ███ ███████ ███    ██ ██    ██ \n" +
                    "██   ██ ██      ████   ██    ██    ██      ██   ██     ████  ████ ██      ████   ██ ██    ██ \n" +
                    "██████  █████   ██ ██  ██    ██    █████   ██████      ██ ████ ██ █████   ██ ██  ██ ██    ██ \n" +
                    "██   ██ ██      ██  ██ ██    ██    ██      ██   ██     ██  ██  ██ ██      ██  ██ ██ ██    ██ \n" +
                    "██   ██ ███████ ██   ████    ██    ███████ ██   ██     ██      ██ ███████ ██   ████  ██████  \n" +
                    "                                                                                             \n" +
                    "                                                                                             ");

            System.out.println("Press 1 to see all renters");
            System.out.println("Press 2 to add a renter to the system");
            System.out.println("Press 3 to remove a renter from the system");
            System.out.println("Press 4 to modify a renter in the system");
            System.out.println("Press 0 to exit\n");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    rm.showAllRenters();
                    break;

                    case 2:
                        System.out.print("Enter first name: ");
                        String f_name = scanner.nextLine();

                        System.out.print("Enter last name: ");
                        String l_name = scanner.nextLine();

                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();

                        System.out.print("Enter zip code: ");
                        String zipCodeInput = scanner.nextLine();
                        System.out.print("Enter city: ");
                        String cityInput = scanner.nextLine();
                        Zip zip = new Zip(zipCodeInput, cityInput);


                        System.out.print("Enter mobile phone number: ");
                        String m_number = scanner.nextLine();

                        System.out.print("Enter phone number *Not required*: ");
                        String p_number = scanner.nextLine();

                        System.out.print("Enter email address: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter drivers licence id: ");
                        String driverslicence_id = scanner.nextLine();

                        System.out.print("Enter when renter acquired licence (MMMM-DD-MM): ");
                        String driver_sinceInput = scanner.nextLine();
                        Date driver_since = null;
                        try{
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            driver_since = dateFormat.parse(driver_sinceInput);
                        } catch(ParseException e){
                            System.out.println("Invalid date format, remember to use YYYY-MM-DD");
                            return;
                        }



                        Renter renter = new Renter(0, f_name, l_name, address, zip, m_number, p_number, email, driverslicence_id, driver_since);

                        rm.saveRenter(renter);
                        break;

                case 0:
                    Menu menu = new Menu();
                    menu.displayMenu();
            }
        }
    }
}