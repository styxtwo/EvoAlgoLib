package main;

public interface Engine {
	void start();

	Info getInfo();
	
	public static EngineBuilder builder() {
		return new EngineBuilder();
	}
}
