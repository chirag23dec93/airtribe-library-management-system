package Notifications;

// this observer pattern makes sure to notify patrons waiting in the queue for their fav book
public class PatronObserver implements Observer {
    private String name;

    public PatronObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
        sendEmailNotification(message);
        sendSMSNotification(message);
    }


    private void sendEmailNotification(String message) {
        System.out.println("Email sent to " + name + ": " + message);
    }

    private void sendSMSNotification(String message) {
        System.out.println("SMS sent to " + name + ": " + message);
    }
}