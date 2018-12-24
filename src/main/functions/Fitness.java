package main.functions;

import main.Creature;

public interface Fitness {
	double calculate(Creature creature);
	
	public static Fitness NONE = c -> 0;
}
