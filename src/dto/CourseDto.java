package dto;


import java.time.LocalDate;

public class CourseDto {
    private String id;
    private String name;
    private String type;
    private String duration;

    public CourseDto() {

    }

    public CourseDto(String id, String name, String type, String duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
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
        return "CourseDto{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type='" + getType() + '\'' +
                ", duration='" + getDuration() + '\'' +
                '}';
    }
}
