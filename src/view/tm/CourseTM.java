package view.tm;

import com.jfoenix.controls.JFXButton;

public class CourseTM {
    private String id;
    private String name;
    private String type;
    private String duration;
    private JFXButton button;

    public CourseTM() {
    }

    public CourseTM(String id, String name, String type, String duration, JFXButton button) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.button = button;
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

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", button=" + button +
                '}';
    }
}
