import java.util.ArrayList;
import java.util.List;
import communBetweenPublisherSubscribers.Employee;

public class EmpHelper {
    //Создание списка сотрудников
    public static List<Employee> getEmps() {

        Employee e1 = new Employee(1, "Vlad");
        Employee e2 = new Employee(2, "Salat");
        Employee e3 = new Employee(3, "Andron");
        Employee e4 = new Employee(4, "Focus");
        Employee e5 = new Employee(5, "Pocus");

        List<Employee> emps = new ArrayList<>();
        emps.add(e1);
        emps.add(e2);
        emps.add(e3);
        emps.add(e4);
        emps.add(e5);

        return emps;
    }

}