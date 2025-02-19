import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RenterMenu {
    private Scanner scanner = new Scanner(System.in);
    RenterManager rm = new RenterManager();

    public void renter_Menu() {

        while (true) {
            System.out.println(" ");
            System.out.println("██████  ███████ ███    ██ ████████ ███████ ██████      ███    ███ ███████ ███    ██ ██    ██ \n" +
                    "██   ██ ██      ████   ██    ██    ██      ██   ██     ████  ████ ██      ████   ██ ██    ██ \n" +
                    "██████  █████   ██ ██  ██    ██    █████   ██████      ██ ████ ██ █████   ██ ██  ██ ██    ██ \n" +
                    "██   ██ ██      ██  ██ ██    ██    ██      ██   ██     ██  ██  ██ ██      ██  ██ ██ ██    ██ \n" +
                    "██   ██ ███████ ██   ████    ██    ███████ ██   ██     ██      ██ ███████ ██   ████  ██████  \n" +
                    "                                                                                             \n" +
                    "                                                                                             ");

            System.out.println("                          Press 1 - See all renters");
            System.out.println("                          Press 2 - Add a renter");
            System.out.println("                          Press 3 - Remove a renter");
            System.out.println("                          Press 4 - Modify a renter");
            System.out.println(" ");
            System.out.println("                          Press 9 - Back to main menu");
            System.out.println("                          Press 0 - Exit the program\n");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    List<Renter> renters = rm.getAllRenters();
                    for (Renter r : renters) {
                        System.out.println(r.getRenter_id() + ". [ " + r.getF_name() + " " + r.getL_name() + " ]  [ Address: " + r.getAddress() + " - " + r.getZip()
                                + " ]  [ Contact information: " + r.getM_number() + "/" + r.getP_number() + " - "+ r.getEmail() + " ]  [ Driver information. ID: "
                        + r.getDriverslicence_id() + ", Driving since: " + r.getDriver_since() + " ]");
                    }
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
                        System.out.println("Renter successfully saved in system.");

                        Renter renter = new Renter(0, f_name, l_name, address, zip, m_number, p_number, email, driverslicence_id, driver_since);

                        rm.saveRenter(renter);
                        break;

                case 3:
                    List<Renter> rentersList = rm.getAllRenters();
                    System.out.println("List of renters: ");
                    for (Renter r : rentersList) {
                        System.out.println("ID: " + r.getRenter_id() + ". Name: " + r.getF_name() + " " + r.getL_name());
                    }
                    System.out.println("Enter the ID of the renter you want to delete: ");
                    int renterDelete = scanner.nextInt();
                    scanner.nextLine();

                    rm.deleteRenter(renterDelete);
                    System.out.println("Renter successfully deleted from the system.");
                     break;

                case 4:
                    rm.modifyRenter();
                    break;

                case 9:
                    Menu menu = new Menu();
                    menu.displayMenu();

                case 0:
                    System.exit(1);
            }
        }
    }
}