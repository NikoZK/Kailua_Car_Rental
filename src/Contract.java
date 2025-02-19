import java.util.Date;

public class Contract {
private int contract_id;
private Renter renter;
private Car car;
private Date start_date_time;
private Date end_date_time;
private int max_km;
private int odometer_start;
private int odometer_end;
private Status status;

public Contract(int contract_id, Renter renter, Car car, Date start_date_time, Date end_date_time, int max_km, int odometer_start, int odometer_end, Status status) {
    this.contract_id = contract_id;
    this.renter = renter;
    this.car = car;
    this.start_date_time = start_date_time;
    this.end_date_time = end_date_time;
    this.max_km = max_km;
    this.odometer_start = odometer_start;
    this.odometer_end = odometer_end;
    this.status = status;
}

    public Status getStatus() {
        return status;
    }

    public int getOdometer_end() {
        return odometer_end;
    }

    public int getOdometer_start() {
        return odometer_start;
    }

    public int getMax_km() {
        return max_km;
    }

    public Date getEnd_date_time() {
        return end_date_time;
    }

    public Date getStart_date_time() {
        return start_date_time;
    }

    public Car getCar() {
        return car;
    }

    public Renter getRenter() {
        return renter;
    }

    public int getContract_id() {
        return contract_id;
    }

    @Override
    public String toString() {
        return "Contract: " +
                "Contract ID: " + contract_id +
                ", Renter: " + renter.getF_name() + " " + renter.getL_name() + " (ID: " + renter.getRenter_id() + ")" +
                ", Car: " + car.getBrand() + " " + car.getModel() + " (ID: " + car.getCarId() + ")" +
                ", Start Time: " + start_date_time +
                ", End Time: " + end_date_time +
                ", Maximum KM: " + max_km +
                ", Odometer Start: " + odometer_start +
                ", Odometer End: " + odometer_end +
                ", Status: " + status;
    }
}
