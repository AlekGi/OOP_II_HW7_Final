package org.homework.viewer;


import org.homework.presenter.NotebookPresenter;

public interface NotebookView extends DisplayNotebook, DisplayMessage, InputView, DateTimeInput{

    void setPresenter(NotebookPresenter presenter);
}
