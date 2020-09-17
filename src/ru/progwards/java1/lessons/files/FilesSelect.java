package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FilesSelect {

    private static String inFolder;
    private static String outFolder;
    private static List<String> keys;

    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        this.inFolder = inFolder;
        this.outFolder = outFolder;
        this.keys = keys;


        Path in = Paths.get(inFolder);
        walker(in);
    }


    public static void walker(Path start) {

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                String word = "";

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(start.relativize(path)))
                        word = check(path);
                    if (word.length() != 0) {
                        maker(word, path);

                    } else return FileVisitResult.CONTINUE;
                    ;


                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }


            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String check(Path chek) {
        try {
            String ch = Files.readString(chek);
            Scanner sc = new Scanner(ch);

            while (sc.hasNext()) {
                String tmp = sc.next();
                Iterator<String> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    if (key.contains(tmp)) {
                        return key;
                    }
                }

            }
            sc.close();


        } catch (IOException e) {
            return "";
        }
        return "";
    }


    public static void maker(String wrd, Path mak) {
        Path out = Paths.get(outFolder).resolve(wrd);
        try {
            if (Files.exists(out) == false) Files.createDirectory(out);
            else Files.copy(mak, out.resolve(mak.getFileName()));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/*
Реализовать метод с сигнатурой public void selectFiles(String inFolder, String outFolder, List<String> keys),
который сортирует файлы по их содержимому.
Нужно просмотреть содержимое всех файлов, с расширением txt, содержащихся в каталоге inFolder с подкаталогами,
и если файл содержит ключевое слово из коллекции keys, то скопировать его в подпапку с соответствующим именем,
этого элемента keys, все подпапки должны находиться в outFolder.


Например, вызвана функция с параметрами (“aaa”, “bbb”, {“check”, “files”} )

нужно проверить каталог aaa с подкаталогами, найти там все файлы txt, и если файл содержит “check”,
скопировать его в папку bbb/check, если файл содержит “files”, скопировать его в папку bbb/files.

Важно! Если, например, слово “files” ни разу не встретилось, пустую папку создавать не нужно
 */