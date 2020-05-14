package ir.ac.kntu.observerdp.presenter;

import ir.ac.kntu.observerdp.model.Observable;
//Subscriber
public interface Observer {
    void update(Observable observable);
}
