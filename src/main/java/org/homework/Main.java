package org.homework;

import org.homework.model.Notebook;
import org.homework.presenter.CommandHandler;
import org.homework.presenter.NotebookPresenter;
import org.homework.viewer.ConsoleNotebookView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Инициализируем Notebook(), ConsoleNotebookView(), NotebookPresenter(), CommandHandler()
        Notebook notebook = new Notebook();
        ConsoleNotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenter(notebook, view, view, view, view, view);
        CommandHandler commandHandler = new CommandHandler(presenter, view);

        // Старт программы
        commandHandler.handleCommand();
    }
}