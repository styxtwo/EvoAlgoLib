package main;

import java.util.List;

public interface Creature {
	Gene get(String name);
	void set(Gene property);
	List<Gene> genes();
	double fitness();

	default void set(String name, double min, double max, double value) {
		set(new GeneImpl(name, min, max, value));
	}
}
