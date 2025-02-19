import java.util.Date;

public class Renter {
    private int renter_id;
    private String f_name;
    private String l_name;
    private String address;
    private Zip zip;
    private String m_number;
    private String p_number;
    private String email;
    private String driverslicence_id;
    private Date driver_since;

    public Renter(int renterId, String f_name, String l_name, String address, Zip zip,
                  String m_number, String p_number, String email, String driverslicence_id, Date driver_since) {
        this.renter_id = renterId;
        this.f_name = f_name;
        this.l_name = l_name;
        this.address = address;
        this.zip = zip; 
        this.m_number = m_number;
        this.p_number = p_number;
        this.email = email;
        this.driverslicence_id = driverslicence_id;
        this.driver_since = driver_since;
    }
    
    public Zip getZip() {
        return zip;
    }

    public Date getDriver_since() {
        return driver_since;
    }

    public String getDriverslicence_id() {
        return driverslicence_id;
    }

    public String getEmail() {
        return email;
    }

    public String getP_number() {
        return p_number;
    }

    public String getM_number() {
        return m_number;
    }

    public String getAddress() {
        return address;
    }

    public String getL_name() {
        return l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public int getRenter_id() {
        return renter_id;
    }

    @Override
    public String toString() {
        return "Renter ID: " + renter_id +
                ", Name: " + f_name + " " + l_name +
                ", Address: " + address +
                ", Zip: " + zip.getZipCode() + " (" + zip.getCity() + ")" +
                ", Mobile: " + m_number +
                (p_number.isEmpty() ? "" : ", Phone: " + p_number) +
                ", Email: " + email +
                ", License ID: " + driverslicence_id +
                ", Licensed Since: " + driver_since;
    }

}

