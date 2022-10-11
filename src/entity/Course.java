package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String type;
    private String duration;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Registration>registrations;

    public Course(String id, String name, String type, String duration) {
        this.setId(id);
        this.setName(name);
        this.setType(type);
        this.setDuration(duration);
    }

    public Course(String id) {
        this.id = id;
    }

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", duration='" + getDuration() + '\'' +
                '}';
    }

}
