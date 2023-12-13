package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Метод создания бэкапа всех файлов из указанной директории в новую указанную директорию
 *
 * @from - путь к директории, из которой копируем
 * @to - путь к директории, в которую копируем
 */
public class UtilMyBackup {
    public static void makeBackup(Path from, Path to) throws IOException {
        to = to.toAbsolutePath().normalize(); //убираем точки
        from = from.toAbsolutePath().normalize(); //убираем точки
        if (!Files.exists(to)) Files.createDirectory(to); //создаем конечную директорию, если еще не существует
        if (Files.isDirectory(from)) {
            try (Stream<Path> dirFiles = Files.list(from)) {
                Path[] files = dirFiles.toArray(Path[]::new);
                if (files.length == 0) return;

                for (Path file : files) {
                    if (Files.isDirectory(file)) {
                        Path toDir = Paths.get(to + "/" + file.getFileName());
                        if (!Files.exists(toDir)) Files.createDirectory(toDir);
                        makeBackup(file, toDir);
                    } else {
                        Path newFile = Paths.get(to + "/" + file.getFileName());
                        if (!Files.exists(newFile)) Files.copy(file, newFile);
                    }
                }
            }
        }
    }
}

