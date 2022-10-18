import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import communBetweenPublisherSubscribers.Employee;
import communBetweenPublisherSubscribers.Freelancer;


public class PublishSubscribeFlowApiApplication {

    public static void main(String[] args) throws InterruptedException {
        // Создаю конечного издателя
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Создаю процессор
        MyProcessor transformProcessor = new MyProcessor(s -> {
            return new Freelancer(s.getId(), s.getId() + 100, s.getName());
        });

        //Создаю конечного подписчика
        MyFreelancerSubscriber subs = new MyFreelancerSubscriber();

        //Создаю цепочку из издателя, обработчика и подписчика
        publisher.subscribe(transformProcessor); // издатель процессору
        transformProcessor.subscribe(subs); // процессор подписчику

        List<Employee> emps = EmpHelper.getEmps();

        // публикация элементов
        System.out.println("Publishing Items to Subscriber");
        emps.stream().forEach(i -> publisher.submit(i));

        // Ожидания завершения обработки сообщений
        while (emps.size() != subs.getCounter()) {
            Thread.sleep(10);
        }

        // Закрытие издателя
        publisher.close();
        transformProcessor.close();

        System.out.println("Exiting the app");
    }

}