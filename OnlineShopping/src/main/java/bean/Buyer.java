package bean;

public class Buyer {
    private int id;
    private String appointment_time;
    private String address;
    private String buyer_name;
    private String buyer_gender;
    private String buyer_identification;
    private String buyer_phone_number;
    private String text;

    public Buyer() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Buyer [id=" + id + ", appointment_time=" + appointment_time + ", address=" + address + ", buyer_name="
                + buyer_name + ", buyer_gender=" + buyer_gender + ", buyer_identification=" + buyer_identification
                + ", buyer_phone_number=" + buyer_phone_number + ", text=" + text + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_gender() {
        return buyer_gender;
    }

    public void setBuyer_gender(String buyer_gender) {
        this.buyer_gender = buyer_gender;
    }

    public String getBuyer_identification() {
        return buyer_identification;
    }

    public void setBuyer_identification(String buyer_identification) {
        this.buyer_identification = buyer_identification;
    }

    public String getBuyer_phone_number() {
        return buyer_phone_number;
    }

    public void setBuyer_phone_number(String buyer_phone_number) {
        this.buyer_phone_number = buyer_phone_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
