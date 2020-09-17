package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {


    public List<List<String>> findDuplicates(String startPath) {

        Path pp = Paths.get(startPath);

        List<Path> full = walker(pp);
        List<List<String>> res = new ArrayList();


        for (Path x : full) {
            Path xP = x.getFileName();
            Set<String> temp = new HashSet<>();

            Iterator<Path> iterator = full.iterator();
            while (iterator.hasNext()) {
                Path y = iterator.next();
                Path yP = y.getFileName();
                System.out.println(y.toString());
                if (xP.equals(yP)) {
                    try {
                        if (x.equals(y)) continue;
                        if (Files.size(x) == Files.size(y)
                                && Files.getLastModifiedTime(x).equals(Files.getLastModifiedTime(y))
                                && Files.readString(x).equals(Files.readString(y))) {
                            System.out.println("полное соотвествие  " + x + " == " + y);
                            temp.add(x.toAbsolutePath().toString());
                            temp.add(y.toAbsolutePath().toString());
                            full.remove(y);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            res.add(new ArrayList<>(temp));

        }


        return res;
    }

    public static List<Path> walker(Path start) {
        List<Path> paths = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(start.relativize(path)))
                        paths.add(path);


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
        return paths;
    }
    /*
    В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению), дате-времени последнего изменения,
    размеру и по содержимому. Сигнатура метода public List<List<String>> findDuplicates(String startPath),
    результат - список, содержащий списки строк с именами и полными путями совпадающих файлов.
     */

    public static void main(String[] args) {
        FindDuplicates test = new FindDuplicates();
        test.findDuplicates("src");
    }
}
