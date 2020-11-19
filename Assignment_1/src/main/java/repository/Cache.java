package repository;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public class Cache<T> {

    private List<T> storage;

    public void save(List<T> entities) {
        this.storage = entities;
    }

    public boolean hasResult() {
        return storage != null;
    }

    public List<T> load() {
        System.out.println("Loaded from cache");
        return storage;
    }

    public void invalidateCache() {
        storage = null;
    }

}
