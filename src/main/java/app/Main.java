package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

public class Main {

    public static void main(String[] args) throws Exception {
        textWordCount("/Users/maria_urevna/desctopTop/лабы/4 курс/СОА/src/main/resources/test.txt");
    }

    /**
     * @throws Exception
     */
    private static void textWordCount(String fileName) throws Exception {

        ConcurrentHashMap<String, LongAdder> wordCounts = new ConcurrentHashMap<>();

        Path filePath = Paths.get(fileName);
        Files.readAllLines(filePath)
                .stream()
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)// string[] -> string
                .filter(w -> w.matches("\\w+"))
                .map(String::toLowerCase)
                .forEach(word -> {
                    if (!wordCounts.containsKey(word))
                        wordCounts.put(word, new LongAdder());
                    wordCounts.get(word).increment();
                });

        wordCounts
                .keySet()
                .stream()
                .map(key -> String.format("%-10d %s", wordCounts.get(key).intValue(), key))
                .forEach(t -> System.out.println("\t"+t));
    }

}