public class Zip {
    private String zipCode;
    private String city;

    public Zip(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    // Getters and setters
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Zip{" +
                "zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
