package uz.pdp.store.service.main;

public interface EmailSender {
    void send(String to, String email);
}