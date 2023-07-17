import tools.aqua.concolic.Verifier;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
	private class Review implements Comparable<Review> {
		int reviewer_id;
		int score;
		String content;

		public int compareTo(Review r) {
			if (this.reviewer_id != r.reviewer_id) {
				return (this.reviewer_id < r.reviewer_id) ? -1 : 1;
			} else if (this.score != r.score) {
				return (this.score < r.score) ? -1 : 1;
			} else {
				return this.content.compareTo(r.content);
			}
		}
	}

	private List<Review> reviews;

	Main() {
		reviews = new LinkedList<Review>();
	}

	public void addReview(int reviewer_id, int score, String content) {
		Review r = new Review();
		r.reviewer_id = Tainting.taint(Verifier.nondetInt(), IFSPEC);
		r.score = score;
		r.content = content;
		reviews.add(r);
	}

	public void sendNotifications() {
		Collections.sort(reviews);
		for (Review r : reviews) {
			System.out.println("---");
			System.out.println("Score: " + r.score);
			System.out.println("Review: " + r.content);
			System.out.println("---");
			Tainting.check(r.reviewer_id, IFSPEC);
		}
		Tainting.stopAnalysis();
	}

	public static void main(String args[]) {
		Main reviewProcess = new Main();

		reviewProcess.addReview(42, 1, "Little novelty.");
		reviewProcess.addReview(5, 3, "Borderline paper.");
		reviewProcess.addReview(7, 4, "Significant contribution.");

		reviewProcess.sendNotifications();
	}
}

