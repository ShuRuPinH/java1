package ru.progwards.java2.lessons.classloader;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PathLoader extends ClassLoader {

    private static Path LOG_PATH = Paths.get("patchloader.log");
    private static String ROOT_PATH = "C:\\test\\";
    private static PathLoader loader = new PathLoader(ROOT_PATH);

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final String date;

    private static Map<String, Integer> errors = new HashMap<>();


    public PathLoader(String path) {
        super(ClassLoader.getSystemClassLoader());
        date = path;

    }


    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        String tmp = className.replace(".", "\\");
        Path path = Paths.get(ROOT_PATH + date + "\\" + tmp + ".class");
        //   System.out.println(path);
        if (Files.exists(path)) {
            try {
                byte[] file = Files.readAllBytes(path);
                return defineClass(className, file, 0, file.length);

            } catch (Throwable e) {
                try {
                    Files.writeString(LOG_PATH,
                            LocalDateTime.now().format(dtf) + " " + className + " ошибка загрузки " + e.getMessage() + "\n",
                            StandardOpenOption.APPEND);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return null;

            } finally {

            }
        } else return findSystemClass(className);
    }

    @Override
    protected Class<?> loadClass(String className, boolean resolve)
            throws ClassNotFoundException {

        // if (findClass(className)==null) return null;
        Class<?> c = findClass(className);
        if (resolve)
            resolveClass(c);
        return c;
    }


    private static void update(Map<String, Integer> classes) {

        try {
            Files.walkFileTree(Paths.get(ROOT_PATH), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".class")) {

                        //   System.out.println(file);
                        Integer date = Integer.parseInt(makeDateName(file).substring(0, 8));
                        String name = (makeDateName(file).substring(9));

                        //   System.out.println(date+"<date   name>"+name);
                        try {
                            if (classes.containsKey(name) || errors.containsKey(name)) {
                                if (classes.get(name) >= date || errors.get(name) == date)
                                    return FileVisitResult.CONTINUE;

                            }
                            loader = new PathLoader(String.valueOf(date));
                            Class newClass = loader.loadClass(name, true);


                            newClass.getDeclaredConstructor().newInstance();
                            classes.put(name, date);
/*
дд.мм.гггг чч.мм.сс <полное имя класса с пакетом> загружен из <полный путь до папки с пакетом> успешно
или
дд.мм.гггг чч.мм.сс <полное имя класса с пакетом> ошибка загрузки <описание ошибки>
 */
                            Files.writeString(LOG_PATH,
                                    LocalDateTime.now().format(dtf) + " " + name + " загружен из " + ROOT_PATH + date + "\n",
                                    StandardOpenOption.APPEND);

                        } catch (Exception e) {
                            errors.put(name, date);
                            e.getMessage();

                        }
                    }
                    return FileVisitResult.CONTINUE;

                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String makeDateName(Path path) throws IOException {
        path = path.toAbsolutePath().toRealPath();
        Path relPath = Paths.get(ROOT_PATH).relativize(path);
        String className = relPath.toString().replaceAll("[\\/\\\\]", ".");
        if (className.toLowerCase().endsWith(".class"))
            className = className.substring(0, className.length() - ".class".length());


        return className;
    }


    public static void main(String[] args) {
        int DELAY = 500;
        int PERIOD = 2000;

        Map<String, Integer> classes = new HashMap<>();


        if (Files.notExists(LOG_PATH)) {
            try {
                Files.createFile(LOG_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TimerTask reload = new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                update(classes);

                //     System.out.println(((double)DELAY/1000+PERIOD/1000*(count++)));

            }
        };

        Timer timer = new Timer();

        timer.schedule(reload, DELAY, PERIOD);


    }


}
