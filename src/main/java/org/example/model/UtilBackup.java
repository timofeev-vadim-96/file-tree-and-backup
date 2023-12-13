package org.example.model;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Метод создания бэкапа всех файлов из указанной директории в новую указанную директорию
 *
 * @from - путь к директории, из которой копируем
 * @to - путь к директории, в которую копируем
 */
public class UtilBackup {
    public static void makeBackup(Path from, Path to) throws IOException {
        FileUtils.copyDirectory(from.toFile(), to.toFile());
    }
}
