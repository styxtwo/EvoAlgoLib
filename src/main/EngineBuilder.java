package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import main.functions.Crossover;
import main.functions.CrossoverTypes;
import main.functions.Fitness;
import main.functions.Mutate;
import main.functions.MutateTypes;
import main.functions.Selection;
import main.functions.SelectionTypes;
import main.functions.Termination;
import main.functions.TerminationTypes;
import util.MathUtil;

public class EngineBuilder {
	private List<CreatureImpl> creatures = new ArrayList<>();

	private Fitness fitness 		= Fitness.NONE;
	private Crossover crossover 	= CrossoverTypes.SINGLE;
	private Mutate mutate 			= MutateTypes.SINGLE;
	private Selection selection 	= SelectionTypes.RANKED;
	private Termination terminate 	= TerminationTypes.NONE;
	
	private List<Consumer<Info>> roundCallbacks = new ArrayList<>();
	
    public EngineBuilder fitness(Fitness fitness) {
		this.fitness = fitness;
		return this;
	}
	
	public EngineBuilder crossover(Crossover crossover) {
		this.crossover = crossover;
		return this;
	}
	
	public EngineBuilder mutate(Mutate mutate) {
		this.mutate = mutate;
		return this;
	}
	
	public EngineBuilder selection(Selection selection) {
		this.selection = selection;
		return this;
	}
	
	public EngineBuilder termination(Termination terminate) {
		this.terminate = terminate;
		return this;
	}
	
	public EngineBuilder addCallback(Consumer<Info> roundCallback) {
		roundCallbacks.add(roundCallback);
		return this;
	}
	
    public CreatureBuilder creatures(int creatureCount) {
		return new CreatureBuilder(this, creatureCount);
	}
    
	public Engine create() {
		Consumer<Info> consumer = info -> {
			roundCallbacks.forEach(c -> c.accept(info));
		};
		return new EngineImpl(fitness, 
				crossover, 
				mutate, 
				selection, 
				terminate, 
				consumer, 
				creatures);
	}
    
    private void addCreatures(List<CreatureImpl> creatures) {
    	this.creatures.addAll(creatures);
    }
	

	public static class CreatureBuilder {
		private List<CreatureImpl> creatures = new ArrayList<>();
		private EngineBuilder worldBuilder;

		public CreatureBuilder(EngineBuilder worldBuilder, int creatureCount) {
			this.worldBuilder = worldBuilder;
			for (int i = 0 ; i < creatureCount; i++) {
				creatures.add(new CreatureImpl());
			}
		}
		
		public CreatureBuilder addProperty(String name, double min, double max) {
			for (Creature creature : creatures) {
				creature.set(new GeneImpl(name, min, max, MathUtil.random(min, max)));
			}
			return this;
		}
		
		public CreatureBuilder addPropertyInt(String name, double min, double max) {
			for (Creature creature : creatures) {
				creature.set(new GeneImpl(name, min, max, MathUtil.randomInt((int)min, (int)max)));
			}
			return this;
		}
		
		public EngineBuilder create() {
			worldBuilder.addCreatures(creatures);
			return worldBuilder;
		}
	}
}
