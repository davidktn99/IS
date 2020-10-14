package good;

public class GoodEmployee {

    private String status;
    private String name;
    private int hours;

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GoodEmployee(String status, String name, int hours) {
        this.status = status;
        this.name = name;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "I am a employee";
    }

}
