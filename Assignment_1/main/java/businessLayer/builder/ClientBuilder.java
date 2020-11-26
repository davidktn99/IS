package businessLayer.builder;

import businessLayer.Client;

/**
 * Created by David Katona on 18/11/2020
 */
public class ClientBuilder {

    private final Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setId(Long id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder setPnc(Long pnc) {
        client.setPnc(pnc);
        return this;
    }

    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setAddress(String add) {
        client.setAddress(add);
        return this;
    }

    public Client build() {
        return client;
    }


}
