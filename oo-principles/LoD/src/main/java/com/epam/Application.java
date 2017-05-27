package com.epam;

import com.epam.domain.Document;
import com.epam.domain.Line;
import com.epam.domain.Paragraph;

public class Application {

	private void start() {
		Document document = (new DocumentCreator()).createDocument();
		printDocument(document);
	}

	private void printDocument(Document document) {
		for (Paragraph paragraph : document.getParagraphs()) {
			printParagraph(paragraph);
		}
	}

	private void printParagraph(Paragraph paragraph) {
		for (Line line : paragraph.getLines()) {
			printLine(line);
		}

		System.out.println();
	}

	private void printLine(Line line) {
		System.out.println(line.getContent());
	}

	public static void main(String[] args) {
		new Application().start();
	}

}
