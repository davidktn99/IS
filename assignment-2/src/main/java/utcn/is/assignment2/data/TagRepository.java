package utcn.is.assignment2.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utcn.is.assignment2.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
