package org.homework.model.service;

import java.io.IOException;

public interface IFileOperations {
    void saveToFile(String path) throws IOException;
    void loadFromFile(String path) throws IOException, ClassNotFoundException;
}
