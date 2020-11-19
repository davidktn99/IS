package repository.client;

public abstract class ClientRepositoryDecorator implements ClientRepository {

    protected ClientRepository decoratedRepository;

    public ClientRepositoryDecorator(ClientRepository repo) {
        this.decoratedRepository = repo;
    }
}
