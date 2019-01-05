package main;

import java.util.List;
import java.util.function.Consumer;

import main.functions.Crossover;
import main.functions.Fitness;
import main.functions.Mutate;
import main.functions.Selection;
import main.functions.Termination;

final class EngineImpl implements Engine {
	private final Termination terminate;
	
	private final Consumer<Info> roundCallback;
	private final InfoImpl info;

	private Generation generation;

	public EngineImpl(Fitness fitness,
             Crossover crossover,
			 Mutate mutate,
			 Selection selection,
			 Termination terminate,
			 Consumer<Info> roundCallback,
	         List<CreatureImpl> creatures) {
		this.terminate = terminate;
		this.roundCallback = roundCallback;
		generation = new GenerationImpl(fitness, crossover, mutate, selection, creatures);
		info = new InfoImpl();
		updateGeneration(generation);
	}

	@Override
	public void start() {
		roundCallback.accept(info);
		while (!terminate.isDone(this)) {
			generation = generation.next();
			updateGeneration(generation);
			roundCallback.accept(info);
		}
	}

	private void updateGeneration(Generation generation) {
		generation.evaluate();
		info.nextGeneration(generation);
	}

	@Override
	public Info getInfo() {
		return info;
	}
}
