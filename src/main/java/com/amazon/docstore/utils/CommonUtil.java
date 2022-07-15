package com.amazon.docstore.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CommonUtil {
    private static List<String> stopWords;
    private static final String STOP_WORD_FILE_PATH=System.getProperty("user.dir") +"/src/main/resources/stopwords.txt";
    static {
        loadStopWords();
    }

    public static void loadStopWords() {
        try {
            stopWords = Files.readAllLines(Paths.get(STOP_WORD_FILE_PATH));
        } catch (IOException exp) {
            log.error("Cannot load stop words content");
        }
    }

    public static String removeStopWords(String original) {
        List<String> allWords =
                Stream.of(original.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);
        return String.join(" ", allWords);
    }

    @Deprecated
    public static boolean isValidTitle(String title, Set<String> queryWords) {
        for (String query : queryWords) {
            if (SearchUtil.search(title.toCharArray(), query.toCharArray())> 0)
                return true;
        }
        return false;
    }

    public static int countOccurrences(String title, Set<String> queryWords) {
        int count=0;
        for (String query : queryWords) {
            count += SearchUtil.search(title.toCharArray(), query.toCharArray());
        }
        return count;
    }
}
