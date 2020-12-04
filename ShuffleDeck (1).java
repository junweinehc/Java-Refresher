package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algs4.*;
import week4inclass.LinkedListQueue;

public class ShuffleDeck {

	public static void main(String[] args) {
		try {
			// String cards = "data/deckofcards.txt";
			// StdIn.fromFile(cards);
			// String cards = StdIn.readAll();

			File file = new File("deckofcards.txt");

			Scanner scanner = new Scanner(file);

			LinkedListQueue<String> deck = new LinkedListQueue<>();

			while (scanner.hasNext()) {
				String card = scanner.nextLine();
				deck.enqueue(card);
			}

			LinkedListQueue<String> left = new LinkedListQueue<>();
			LinkedListQueue<String> right = new LinkedListQueue<>();
			int numLoops = (int) logBase2(deck.size());


			for (int i = 0; i < numLoops; i++) {
				shuffleDeck(deck, left, right);
			}
			while(!deck.isEmpty()) {
				System.out.println(deck.dequeue());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void shuffleDeck(LinkedListQueue<String> deck, LinkedListQueue<String> left,
			LinkedListQueue<String> right) {
		while (!deck.isEmpty()) {

			String card = deck.dequeue();
			int value = (int) (Math.random() * 2);
			if (value == 0) {
				left.enqueue(card);
			} else {
				right.enqueue(card);
			}
		}
		while (!left.isEmpty()) {
			String leftCard = left.dequeue();
			deck.enqueue(leftCard);
		}
		while (!right.isEmpty()) {
			String rightCard = right.dequeue();
			deck.enqueue(rightCard);
		}
	}

	public static double logBase2(int x) {
		return Math.log(x) / Math.log(2);
	}

}
