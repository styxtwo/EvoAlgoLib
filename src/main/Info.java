package main;

import java.util.List;

public interface Info {

	int getCurrentGenIndex();
	int getOverallBestGenIndex();
	int getCreatureCount();
	
	Creature getOverallBest();

	List<Creature> getCurrentCreatures();
	Creature getCurrentGenBest();
	double getCurrentGenAverageFitness();
	double getCurrentGenFitnessVariance();

	List<Double> getBestFitnesses();
	List<Double> getAverageFitnesses();
	

	default List<Creature> getCurrentCreatures(int count) {
		List<Creature> creatures = getCurrentCreatures();
		count = count > creatures.size() ? creatures.size() : count;
		return creatures.subList(0, count);
	}
}
