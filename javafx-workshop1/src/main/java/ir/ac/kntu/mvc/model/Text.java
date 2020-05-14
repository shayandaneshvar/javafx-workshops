package ir.ac.kntu.mvc.model;

import ir.ac.kntu.observerdp.model.Observable;
import ir.ac.kntu.observerdp.presenter.Observer;

/**
 * @author S.Shayan Daneshvar
 * <p>
 * Immutable Design
 */
public final class Text implements Observable, Cloneable {
    //Mutable Version of String Class
    private final StringBuilder text = new StringBuilder();
    //It doesn't make sense to clone an observer in an observable's clone method
    //also it is not considered an important field, (Association Relation)
    private Observer observer = null;


    public Text(String text) {
        this.text.append(text);
    }

    public String getText() {
        return text.toString();
    }

    @Override
    public Text clone() throws CloneNotSupportedException {
        Text newText = (Text) super.clone();
        newText.text.append(text.toString());
        return newText;
    }

    public void append(String text) {
        this.text.append(text);
    }

    public static Integer countNumberOfCharacters(StringBuilder string) {
        return countNumberOfCharacters(string.toString());
    }

    public static Integer countNumberOfCharacters(String string) {
        int counter = 0;
        for (char character : string.toCharArray()) {
            if (Character.isLetter(character)) {
                counter++;
            }
        }
        return counter;
    }

    //this is called a hyper method - it does it's own responsibility and then
    //returns the current object
    public Text clear() {
        text.delete(0, text.length());
        return this;
    }

    @Override
    public void addObserver(Observer observer) {
        if (this.observer == null) {
            this.observer = observer;
        }
    }

    @Override
    public void updateAllObservers() {
        if (observer != null) {
            observer.update(this);
        }
    }
}
//Immutable Classes Should Have the following Characteristics:
//1 - The Class Should Be Final thus it cannot be extended
//2 - There Should be No Setters (Mutator)
//3 - The Clone Method Should Be Implemented and return a deep copy
//4 - All Class Fields Should be private and Final
//5 - There should be an all args constructor
//6 - Getters Should return a Copy of the wanted property