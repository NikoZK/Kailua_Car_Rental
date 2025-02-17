import java.util.Date;

public class Car {
    private int carId;
    private String brand;
    private String model;
    private FuelType fuelType;
    private boolean isAutomatic;
    private boolean airCondition;
    private boolean cruiseControl;
    private boolean leatherSeats;
    private int horsepower;
    private int cc;
    private int seats;
    private String regNumber;
    private Date regDate;
    private int odometer;
    private int cargroupId;


    public Car(int carId, String brand, String model, FuelType fuelType, boolean isAutomatic,
               boolean airCondition, boolean cruiseControl, boolean leatherSeats,
               int horsepower, int cc, int seats, String regNumber, Date regDate, int odometer) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.isAutomatic = isAutomatic;
        this.airCondition = airCondition;
        this.cruiseControl = cruiseControl;
        this.leatherSeats = leatherSeats;
        this.horsepower = horsepower;
        this.cc = cc;
        this.seats = seats;
        this.regNumber = regNumber;
        this.regDate = regDate;
        this.odometer = odometer;
        this.cargroupId = determineCarGroup();
    }

    private int determineCarGroup() {
        if (cc > 3000 && isAutomatic && airCondition && cruiseControl && leatherSeats) {
            return 1; // Luxury
        } else if (!isAutomatic && airCondition && seats >= 7) {
            return 2; // Family
        } else if (!isAutomatic && horsepower > 200) {
            return 3; // Sport
        } else {
            return 0; // Unknown
        }
    }

    private String getCarGroupName() {
        return switch (cargroupId) {
            case 1 -> "Luxury";
            case 2 -> "Family";
            case 3 -> "Sport";
            default -> "Unknown";
        };
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public boolean isLeatherSeats() {
        return leatherSeats;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getCc() {
        return cc;
    }

    public int getSeats() {
        return seats;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Date getRegDate() {
        return regDate;
    }

    public int getOdometer() {
        return odometer;
    }

    public int getCargroupId() {
        return cargroupId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmission=" + (isAutomatic ? "Automatic" : "Manual") +
                ", airCondition=" + airCondition +
                ", cruiseControl=" + cruiseControl +
                ", leatherSeats=" + leatherSeats +
                ", horsepower=" + horsepower +
                ", cc=" + cc +
                ", seats=" + seats +
                ", regNumber='" + regNumber + '\'' +
                ", regDate=" + regDate +
                ", odometer=" + odometer +
                ", carGroup=" + cargroupId +
                '}';
    }
}
