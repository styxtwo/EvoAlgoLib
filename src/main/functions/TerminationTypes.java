package main.functions;

import main.Creature;

public class TerminationTypes {
	public static final Termination NONE = (e) -> false;
	public static final Termination BEST_ZERO = bestFitness(0);
	public static final Termination NO_IMPROVEMENT_1000 = noImprovement(1000);

	public static Termination generation(int finalRound) {
		return (e) -> {
			return e.getInfo().getCurrentGenIndex() > finalRound;
		};
	}

	public static Termination avgFitness(double maxFitness) {
		return (e) -> {
			return e.getInfo().getCurrentGenAverageFitness() >= maxFitness;
		};
	}

	public static Termination bestFitness(double maxFitness) {
		return (e) -> {
			Creature creature = e.getInfo().getCurrentGenBest();
			return creature.fitness() >= maxFitness;
		};
	}

	public static Termination noImprovement(int maxRounds) {
		return (e) -> {
			int currentGen = e.getInfo().getCurrentGenIndex();
			int overallBestGen = e.getInfo().getOverallBestGenIndex();
			return maxRounds < (currentGen - overallBestGen);
		};
	}
}
