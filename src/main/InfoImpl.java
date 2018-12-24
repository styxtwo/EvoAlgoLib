package main;

import java.util.ArrayList;
import java.util.List;

public class InfoImpl implements Info {
	private Generation currentGen;
	private int generationCount;
	private int overallBestGen;
	private Creature overallBest;
	List<Double> bestFitnesses = new ArrayList<>();
	List<Double> averageFitnesses = new ArrayList<>();

	public void nextGeneration(Generation next) {
		if(currentGen != null) {
			generationCount++;
		}
		currentGen = next;
		updateBest();
		bestFitnesses.add(getCurrentGenBest().fitness());
		bestFitnesses.add(getCurrentGenAverageFitness());
	}

	@Override
	public int getCurrentGenIndex() {
		return generationCount;
	}

	@Override
	public int getCreatureCount() {
		return currentGen.getCreatures().size();
	}

	@Override
	public Creature getOverallBest() {
		return overallBest;
	}

	@Override
	public List<Creature> getCurrentCreatures() {
		return currentGen.getCreatures();
	}

	@Override
	public Creature getCurrentGenBest() {
		return currentGen.getCreatures().get(0);
	}

	@Override
	public double getCurrentGenAverageFitness() {
		return getAverageFitness(currentGen);
	}

	@Override
	public double getCurrentGenFitnessVariance() {
		return getCurrentGenBest().fitness() - getCurrentCreatures().get(getCreatureCount() - 1).fitness();
	}
	
	@Override
	public List<Double> getBestFitnesses() {
		return bestFitnesses;
	}

	@Override
	public List<Double> getAverageFitnesses() {
		return averageFitnesses;
	}
	
	private double getAverageFitness(Generation generation) {
		double average = 0;
		for (Creature creature: generation.getCreatures())  {
			average += creature.fitness()/generation.getCreatures().size();
		}
		return average;
	}
	
	private void updateBest() {
		if (overallBest == null) {
			overallBest = getCurrentGenBest();
			overallBestGen = getCurrentGenIndex();
		}
		if (getCurrentGenBest().fitness() > overallBest.fitness()) {
			overallBest = getCurrentGenBest();
			overallBestGen = getCurrentGenIndex();
		}
	}

	@Override
	public int getOverallBestGenIndex() {
		return overallBestGen;
	}
}
