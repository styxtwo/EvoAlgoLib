package main;

import java.util.List;

public interface Generation {
	public Generation next();
	public void evaluate();
	public List<Creature> getCreatures();
}
