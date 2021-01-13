package utcn.is.assignment2.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {
    @Size(min = 1, max = 30)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Event> events = new ArrayList<>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getDisplayName() {
        return "#" + name + " ";
    }
}
