package bo;

import bo.custom.impl.CourseBoImpl;
import bo.custom.impl.RegistrationBoImpl;
import bo.custom.impl.StudentBoImpl;

public class BoFactory {

    private static BoFactory boFactoryInstance;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        if (boFactoryInstance == null) {
            boFactoryInstance = new BoFactory();
        }
        return boFactoryInstance;
    }
    public enum BoType{
        COURSE,STUDENT,REGISTRATION
    }
    public <T extends SuperBO> T getBo(BoType boType){
        switch (boType){
            case COURSE:
                return (T) new CourseBoImpl();
            case STUDENT:
                return (T) new StudentBoImpl();
            case REGISTRATION:
                return (T) new RegistrationBoImpl();
            default:
                return null;
        }
    }
}
