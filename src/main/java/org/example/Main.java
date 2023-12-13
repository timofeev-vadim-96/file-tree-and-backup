package org.example;

import org.example.model.UtilBackup;
import org.example.model.UtilFilesTree;
import org.example.model.UtilMyBackup;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        //задание #2
        Path path = Paths.get(".");
        try {
            UtilFilesTree.drawFileTree(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //задание # 1 - 1 способ
        Path backup = Paths.get("../backup"); //создастся новая директория на уровне с текущей
        try {
            UtilBackup.makeBackup(path, backup);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //задание # 1 - 2 способ
        Path backup2 = Paths.get("../backup2"); //создастся новая директория на уровне с текущей
        try {
            UtilMyBackup.makeBackup(path, backup2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}