package com.epam.training;

import com.epam.training.observer.impl.LongestWordObserver;
import com.epam.training.observer.impl.NumberCounterObserver;
import com.epam.training.observer.impl.ReverseWordObserver;
import com.epam.training.observer.impl.WordCounterObserver;
import com.epam.training.subject.Subject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Application {

    public static void main(String[] args) throws IOException {
        File sourceFile = new File("./src/main/resources/source.txt");

        String context = new String(Files.readAllBytes(sourceFile.toPath()));

        Application instance = new Application();
        instance.run(context, new Subject());
    }

    private void run(String context, Subject subject) {
        WordCounterObserver wordCounterObserver = new WordCounterObserver(subject);
        NumberCounterObserver numberCounterObserver = new NumberCounterObserver(subject);
        LongestWordObserver longestWordObserver = new LongestWordObserver(subject);
        ReverseWordObserver reverseWordObserver = new ReverseWordObserver(subject);

        for (String word : context.split("\\s+")) {
            subject.setText(word);
        }

        System.out.println("Words count: " + wordCounterObserver.getWordCount());
        System.out.println("Numbers count: " + numberCounterObserver.getNumberCount());
        System.out.println("Longest word: " + longestWordObserver.getLongestWord());
        System.out.println("Reversed text (0-100 characters): " + reverseWordObserver.getReverseText().substring(0, 100));
    }

}
