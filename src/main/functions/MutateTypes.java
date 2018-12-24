package main.functions;

import main.Gene;
import util.MathUtil;

public class MutateTypes {

	public static final double DEFAULT_MUTATION_RATE = 0.003;

	public static final double HIGH_MUTATION_RATE = 0.01;
	
	public static Mutate NONE = (c) -> {};

	public static Mutate ALL = (c) -> {
		for (Gene p : c.genes()) {
			p.setValue(p.getValue() + Math.random()*2 - 1);
		}
	};
	
	public static Mutate ALL_INT = (c) -> {
		for (Gene p : c.genes()) {
			int offset = MathUtil.randomBoolean() ? 1 : -1;
			p.addValue(offset);
		}
	};

	public static Mutate SINGLE = (c) -> {
		int index = MathUtil.randomInt(0, c.genes().size());
		Gene gene = c.genes().get(index);
		gene.setValue(MathUtil.random(gene.getMin(), gene.getMax()));
	};
	
	public static Mutate DEFAULT = (c) -> {
		for (Gene gene : c.genes()) {
			if (MathUtil.random() < DEFAULT_MUTATION_RATE) {
				gene.setValue(MathUtil.random(gene.getMin(), gene.getMax()));
			}
		}
	};
	
	public static Mutate DEFAULT_INT = (c) -> {
		rateInt(DEFAULT_MUTATION_RATE).mutate(c);
	};
	
	public static Mutate rateInt(double rate) {
		return c -> {
			for (Gene gene : c.genes()) {
				if (MathUtil.random() < rate) {
					gene.setValue(MathUtil.randomInt((int)gene.getMin(), (int)gene.getMax()));
				}
			}
		};
	}
}
