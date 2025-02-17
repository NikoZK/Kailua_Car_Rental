public class Menu {
    private CarManager carManager;
    private RenterManager renterManager;
    private ContractManager contractManager;

    public Menu(){
        carManager = new CarManager();
        renterManager = new RenterManager();
        contractManager = new ContractManager();
    }

    public void displayMenu(){
        System.out.println("Velkommen!!!!!");
        System.out.println("Press 1 for car menu.");
        System.out.println("Press 2 for renter menu.");
        System.out.println("Press 3 for contract menu.");
    }
}
