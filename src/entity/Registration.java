package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Registration implements SuperEntity {
    @Id
    private int regno;
    private String regdate;
    private double regfee;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    public Registration() {
    }

    public Registration(int regno, String regdate, double regfee, Student student, Course course) {
        this.regno = regno;
        this.regdate = regdate;
        this.regfee = regfee;
        this.student = student;
        this.course = course;
    }

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public double getRegfee() {
        return regfee;
    }

    public void setRegfee(double regfee) {
        this.regfee = regfee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regno=" + regno +
                ", regdate='" + regdate + '\'' +
                ", regfee=" + regfee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
