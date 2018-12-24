package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.functions.Crossover;
import main.functions.Fitness;
import main.functions.Mutate;
import main.functions.Selection;

final class GenerationImpl implements Generation {	
	
	private final Fitness fitness;
	private final Crossover crossover;
	private final Mutate mutate;
	private final Selection selection;
	private final List<CreatureImpl> creatures;
	
	public GenerationImpl(Fitness fitness,
			              Crossover crossover,
						  Mutate mutate,
						  Selection selection,
						  List<CreatureImpl> creatures) {
		this.fitness = fitness;
		this.crossover = crossover;
		this.mutate = mutate;
		this.selection = selection;
		this.creatures = creatures;
	}

	@Override
	public void evaluate() {
		for (CreatureImpl creature: creatures) {
			double fitnessVal = fitness.calculate(creature);
			creature.setFitness(fitnessVal); //TODO ugly cast!
		}
		Collections.sort(creatures, 
			(lhs, rhs) -> {
				double lhsFit = lhs.fitness();
				double rhsFit = rhs.fitness();
				return lhsFit > rhsFit ? -1 : (lhsFit < rhsFit) ? 1 : 0;
			});
	}
	
	@Override
	public Generation next() {
		List<CreatureImpl> children = new ArrayList<>();
		for (int i = 0; i < creatures.size(); i++) {
			Creature mother = selection.select(this);
			Creature father = selection.select(this, mother);
			children.add(breed(mother, father));
		}
		return new GenerationImpl(fitness, crossover, mutate, selection, children);
	}
	
	private CreatureImpl breed(Creature mother, Creature father) {
		CreatureImpl child = new CreatureImpl();
		crossover(mother, father, child);
		mutate.mutate(child);
		return child;
	}
	
	private void crossover(Creature mother, Creature father, Creature child) {
		List<Gene> genes = mother.genes();
		genes = crossover.crossover(mother.genes(), father.genes());
		for (Gene gene : genes) {
			child.set(new GeneImpl(gene));
		}
	}
	
	@Override
	public List<Creature> getCreatures() {
		return Collections.unmodifiableList(creatures);
	}
}
