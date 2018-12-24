package main.functions;

import java.util.ArrayList;
import java.util.List;

import main.Gene;
import util.MathUtil;

public class CrossoverTypes {
	public static final double DEFAULT_CROSSOVER_RATE = 0.85;
	
	public static final Crossover SINGLE = (m,f) -> {
		if (MathUtil.random() < DEFAULT_CROSSOVER_RATE) {
			int geneCount = m.size();
			int crossIndex = MathUtil.randomInt(0, geneCount);
			
			List<Gene> genes = new ArrayList<>();
			genes.addAll(m.subList(0, crossIndex));
			genes.addAll(f.subList(crossIndex, geneCount));
			return genes;
		}
		return new ArrayList<>(m);
	};
	
	public static final Crossover NONE = (m,f) -> {
		return new ArrayList<>(m);
	};
}
