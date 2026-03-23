package co.edu.uptc;

import co.edu.uptc.model.Model;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.MainView;

public class Runner {

    public static void main(String[] args) {
        Model model = new Model();
        MainView view = new MainView();
        Presenter presenter = new Presenter();

        presenter.setModel(model);
        presenter.setView(view);
        view.setPresenter(presenter);

        view.start();
    }
}
