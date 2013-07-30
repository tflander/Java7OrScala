package javaKinds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BasicPolymorphismTest {

	@Test
	public void itShouldMakeAnimalSounds() {
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Donkey());

		for (Animal animal : animals) {
			System.out.println(animal.speak());
		}

		/*
		 * Output:
		 * 
		 * woof 
		 * mew mew 
		 * hee-haw
		 */
	}

	class Zerg extends Animal {

		@Override
		public String speak() {
			return "for the swarm";
		}
	}

	@Test
	public void itShouldAllowYouToDefineNewAnimals() {
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Donkey());
		animals.add(new Zerg());

		for (Animal animal : animals) {
			System.out.println(animal.speak());
		}
		/*
		 * Output:
		 * 
		 * woof 
		 * mew mew 
		 * hee-haw
		 * for the swarm
		 */
	}
}
