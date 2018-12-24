package main.functions;

import main.Creature;
import main.Generation;

public interface Selection {
	Creature select(Generation generation);
	
	default Creature select(Generation generation, Creature exclude) {
		Creature selected = select(generation);
		while (selected == exclude) {
			selected = select(generation);
		}
		return selected;
	}
}
