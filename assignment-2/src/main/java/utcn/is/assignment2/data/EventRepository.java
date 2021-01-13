package utcn.is.assignment2.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utcn.is.assignment2.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
