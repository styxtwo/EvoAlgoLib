package main.functions;

import java.util.List;

import main.Gene;

public interface Crossover {
	List<Gene> crossover(List<Gene> mother, List<Gene> father);
}
