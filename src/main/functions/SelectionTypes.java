package main.functions;

import java.util.ArrayList;
import java.util.List;

import main.Creature;
import util.MathUtil;

public class SelectionTypes {
	public static Selection RANDOM = generation -> {
		List<Creature> creatures = generation.getCreatures();
		return creatures.get(MathUtil.randomInt(0, creatures.size()));
	};
	
	public static Selection RANKED = generation -> {
		List<Creature> creatures = generation.getCreatures();
		int size = creatures.size();
		int lots = size*(size + 1)/2;
		int randomLot = MathUtil.randomInt(0, lots);
		for (int i = 0; i < size; i++) { 
			randomLot -= size - i;
			if (randomLot < 0) {
//				System.out.println("RETURNING " + i);
				return creatures.get(i);
			}
		}
		return creatures.get(0);
	};
	
	public static Selection FITNESS = generation -> {
		List<Creature> creatures = generation.getCreatures();
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (Creature creature: creatures ) {
			double fitness = creature.fitness();
			if (fitness < min) {
				min = fitness;
			}	
			if (fitness > max) {
				max = fitness;
			}
		}
		
		double sum = 0;
		List<Double> fitnesses = new ArrayList<>();
		for (Creature creature: creatures ) {
			double fitness = (creature.fitness() - min) / max;
			sum += fitness;
			fitnesses.add(fitness);
		}
		
		double randomVal = MathUtil.random(0, sum);
		int i = 0;
		for (double fitness : fitnesses) { 
			randomVal -= fitness;
			if (randomVal < 0) {
//				System.out.println("RETURNING " + i);
				return creatures.get(i);
			}
			i++;
		}
		return creatures.get(0);
	};
	
	public static Selection TOP_HALF = topFraction(0.5);
	
	public static Selection topFraction(double fraction) {
		return generation -> {
			List<Creature> creatures = generation.getCreatures();
			int index = MathUtil.randomInt(0, (int) (creatures.size()*fraction));
			return creatures.get(index);
		};
	}

}
