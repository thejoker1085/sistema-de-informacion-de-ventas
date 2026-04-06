package co.edu.uptc;

import co.edu.uptc.config.BusinessServiceFactory;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.BusinessOperationOrchestrator;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.AccountingSystemMainWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::runApp);
    }

    private static void runApp() {
        BusinessServiceFactory factory = new BusinessServiceFactory();
        ModelInterface model = new BusinessOperationOrchestrator(factory);
        ViewInterface view = new AccountingSystemMainWindow();
        
        Presenter presenter = new Presenter(model, view);
        view.setPresenter(presenter);
        view.start();
    }
}