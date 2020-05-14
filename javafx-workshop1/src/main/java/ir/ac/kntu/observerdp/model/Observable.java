package ir.ac.kntu.observerdp.model;

import ir.ac.kntu.observerdp.presenter.Observer;
//publisher
public interface Observable {
    void addObserver(Observer observer);

    void updateAllObservers();
}
