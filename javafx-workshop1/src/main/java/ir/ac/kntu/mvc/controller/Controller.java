package ir.ac.kntu.mvc.controller;

import ir.ac.kntu.mvc.Command;
import ir.ac.kntu.mvc.model.Text;
import ir.ac.kntu.mvc.view.View;

public class Controller {
    private Text text = null;

    public Controller(View view) {
        text = new Text("");
        text.addObserver(view);
        Command calculateCommand = (o) -> {
            String string = (String) o;
            text.clear().append(string);
            text.updateAllObservers();
        };
        view.setCalculateCommand(calculateCommand);
    }

//    public void init() {
//        if (text != null) {
//
//        }
//    }
}
