package main;

import util.MathUtil;

final class GeneImpl implements Gene {

	private final String name;
	private final double min;
	private final double max;
	private double value;

	public GeneImpl(Gene property) {
		this(property.getName(), property.getMin(), property.getMax(), property.getValue());
	}

	public GeneImpl(String name, double min, double max, double value) {
		this.name = name;
		this.min = min;
		this.max = max;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getMin() {
		return min;
	}

	@Override
	public double getMax() {
		return max;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public void setValue(double value) {
		this.value = MathUtil.clamp(value, min, max);
	}

}
