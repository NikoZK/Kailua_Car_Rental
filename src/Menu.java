import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    CarMenu carMenu = new CarMenu();
    RenterMenu renterMenu = new RenterMenu();


    public void displayMenu(){
        System.out.println("██   ██  █████  ██ ██      ██    ██  █████       ██████  █████  ██████      ██████  ███████ ███    ██ ████████  █████  ██      \n" +
                "██  ██  ██   ██ ██ ██      ██    ██ ██   ██     ██      ██   ██ ██   ██     ██   ██ ██      ████   ██    ██    ██   ██ ██      \n" +
                "█████   ███████ ██ ██      ██    ██ ███████     ██      ███████ ██████      ██████  █████   ██ ██  ██    ██    ███████ ██      \n" +
                "██  ██  ██   ██ ██ ██      ██    ██ ██   ██     ██      ██   ██ ██   ██     ██   ██ ██      ██  ██ ██    ██    ██   ██ ██      \n" +
                "██   ██ ██   ██ ██ ███████  ██████  ██   ██      ██████ ██   ██ ██   ██     ██   ██ ███████ ██   ████    ██    ██   ██ ███████ \n" +
                "                                                                                                                               \n" +
                "                                                                                                                               ");
        System.out.println("Press 1 for car menu.");
        System.out.println("Press 2 for renter menu.");
        System.out.println("Press 3 for contract menu.");
        System.out.println("Press 0 to exit");
        int valg = scanner.nextInt();
        scanner.nextLine();

        switch(valg){
            case 1:
                carMenu.Car_Menu();

            case 2:
                renterMenu.renter_Menu();

            case 0:
                System.exit(0);
        }
    }
}
