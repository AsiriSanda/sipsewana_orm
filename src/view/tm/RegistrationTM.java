package view.tm;

public class RegistrationTM {
    private String regno;
    private String regdate;
    private String regfee;
    private String student;
    private String course;

    public RegistrationTM() {
    }

    public RegistrationTM(String regno, String regdate, String regfee, String student, String course) {
        this.regno = regno;
        this.regdate = regdate;
        this.regfee = regfee;
        this.student = student;
        this.course = course;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getRegfee() {
        return regfee;
    }

    public void setRegfee(String regfee) {
        this.regfee = regfee;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "RegistrationTM{" +
                "regno='" + regno + '\'' +
                ", regdate='" + regdate + '\'' +
                ", regfee='" + regfee + '\'' +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
