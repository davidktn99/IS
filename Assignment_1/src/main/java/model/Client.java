package model;

/**
 * Created by David Katona on 18/11/2020
 */
public class Client {

    private Long id;

    private Long pnc;
    private String name;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPnc() {
        return pnc;
    }

    public void setPnc(Long pnc) {
        this.pnc = pnc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
