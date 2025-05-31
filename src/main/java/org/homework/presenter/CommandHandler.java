package org.homework.presenter;

import org.homework.viewer.ConsoleNotebookView;

import java.io.IOException;

public class CommandHandler {
    private NotebookPresenter presenter;
    private ConsoleNotebookView view;

    public CommandHandler(NotebookPresenter presenter, ConsoleNotebookView view) {
        this.presenter = presenter;
        this.view = view;
    }

    public void handleCommand() throws IOException, ClassNotFoundException {
        while (true) {
            view.displayMessage("\nДобавить запись: 1\nЗаписи за день: 2" +
            "\nЗаписи за неделю: 3\nЗаписи за месяц: 4\nЗаписи за год: 5\n" +
                    "Сохранить в файл: 6\nЗагрузить из файла: 7" +
                    "\nВыход: 0\nВведите число команды:");
            String command = view.userInput();
            presenter.handleCommand(command);
        }
    }
}
