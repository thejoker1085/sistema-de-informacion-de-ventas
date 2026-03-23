package co.edu.uptc.interfaces;

public interface ViewInterface {
    void setPresenter(PresenterInterface presenter);
    void start();
    void showSuccess(String message);
    void showError(String message);
}