package main;


public interface Gene { 
	String getName();
	double getMin();
	double getMax();
	double getValue();
	void setValue(double value);
	
	default void addValue(double value) {
		this.setValue(this.getValue() + value);
	}

	public static Gene NULL = new Gene() {
		
		@Override
		public void setValue(double value) {
			//Ignore.
		}
		
		@Override
		public double getValue() {
			return 0;
		}
		
		@Override
		public String getName() {
			return "NULL_PROPERTY";
		}
		
		@Override
		public double getMin() {
			return 0;
		}
		
		@Override
		public double getMax() {
			return 0;
		}
	};
}
