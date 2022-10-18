
import communBetweenPublisherSubscribers.Employee;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MyReactiveApp {

    public static void main(String args[]) throws InterruptedException {

        // Создаю издателя
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Регистрирую подписчика
        MySubscriber subs = new MySubscriber();
        publisher.subscribe(subs);

        List<Employee> emps = EmpHelper.getEmps();

        // Публикую элементы
        System.out.println("Publishing Items to Subscriber");
        emps.stream().forEach(i -> publisher.submit(i));

        // Ожидание завершения обработки всех сообщений
        //while (emps.size() != subs.getCounter()  || !publisher.isSubscribed(subs)) {
        Thread.sleep(1000);
        //}
        // Закрыть издателя
        publisher.close();

        System.out.println("Exiting the app");

    }

}