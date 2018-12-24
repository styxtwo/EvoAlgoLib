package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import util.LogUtil;

final class CreatureImpl implements Creature { 
	private final Map<String, Gene> genesByName = new LinkedHashMap<>();
	private final List<Gene> genes = new ArrayList<>();
	private double fitness;

	@Override
	public void set(Gene gene) {
		genesByName.put(gene.getName(), gene);
		genes.add(gene);
	}

	@Override
	public Gene get(String name) {
		if (genesByName.get(name) != null) {
			return genesByName.get(name);
		}
		LogUtil.warning("Accessed uninitialised property/quantity: " + name);
		return Gene.NULL;
	}

	@Override
	public List<Gene> genes() {
		return genes;
	}

	@Override
	public double fitness() {
		return fitness;
	}

	void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
