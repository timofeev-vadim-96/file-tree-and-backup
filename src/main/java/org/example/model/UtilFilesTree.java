package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Утильный класс с единственным методом по выводу дерева файлов
 */
public class UtilFilesTree {
    /**
     * Метод вывода дерева файлов от корневой папки и далее вглубь
     *
     * @param path путь к файлу
     * @throws IOException исключение ввода-вывода
     */
    public static void drawFileTree(Path path) throws IOException {
        drawFileTree(path, "", true);
    }

    /**
     * Метод вывода дерева файлов для перегрузки в основном методе
     *
     * @param path   путь к файлу
     * @param indent отступ
     * @param isLast проверка на крайний в директории файл или саму директорию (если она пуста)
     * @throws IOException исключение ввода-вывода
     */
    private static void drawFileTree(Path path, String indent, boolean isLast) throws IOException {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└");
            indent += "   ";
        } else {
            System.out.print("├");
            indent += "│  ";
        }
        System.out.println(path.getFileName());

        if (Files.isDirectory(path)) {
            try (Stream<Path> dirFiles = Files.list(path)) {
                Path[] files = dirFiles.toArray(Path[]::new);
                if (files.length == 0) return;
                int subDirTotal = 0;
                int subFile = 0;
                for (Path file : files) {
                    if (Files.isDirectory(file)) subDirTotal++;
                    else subFile++;
                }

                int currentSubDir = 0;
                int currentSubFile = 0;
                for (Path file : files) {
                    if (Files.isDirectory(file)) {
                        drawFileTree(file, indent, subDirTotal == ++currentSubDir);
                    } else drawFileTree(file, indent, subFile == ++currentSubFile);
                }
            }
        }
    }
}

