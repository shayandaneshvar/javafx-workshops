package ir.ac.kntu.observerdp.model;

import ir.ac.kntu.observerdp.presenter.Observer;

import java.util.ArrayList;
import java.util.List;

public class Soldier implements Observable {
    private String name;
    private Integer health;
    private List<Observer> observers;

    public Soldier(String name) {
        this.name = name;
        this.health = 100;
        observers = new ArrayList<>();
    }

    public void getHurt(Integer damage) {
        if (health > 0) {
            health -= damage;
        }
        updateAllObservers();
    }

    public void getHurt() {
        getHurt(10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateAllObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
