import communBetweenPublisherSubscribers.Employee;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriber implements Subscriber<Employee> {

    private Subscription subscription;//для хранения ссылок

    private int counter = 0;//для записи количества обработанных элементов

    @Override
    public void onSubscribe(Subscription subscription) {//Запрос на подписку
        System.out.println("Subscribed");
        this.subscription = subscription;
        this.subscription.request(1); //запрос данных у издателя
        System.out.println("onSubscribe requested 1 item");
    }

    @Override
    public void onNext(Employee item) {
        System.out.println("Processing Employee "+item);
        counter++;
        this.subscription.request(1);
    }
//

    @Override
    public void onError(Throwable e) {
        System.out.println("Some error happened");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("All Processing Done");
    }

    public int getCounter() {
        return counter;
    }

}