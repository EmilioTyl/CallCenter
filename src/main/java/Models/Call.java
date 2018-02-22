package Models;

import java.util.Random;

public class Call {
	private int id;
	private int duration;
	private static int MAX_DURATION = 10;
	private static int MIN_DURATION = 5;
	private static Random r = new Random();
	
	public Call(int id) {
		this.id = id;
		this.duration =  r.nextInt((MAX_DURATION - MIN_DURATION) + 1) + MIN_DURATION;
	}
	public Call(int id, int duration) {
		this.id = id;
		this.duration =  duration;
	}
	public int getDuration() {
		return duration;
	}

	public int getId() {
		return id;
	}
}
