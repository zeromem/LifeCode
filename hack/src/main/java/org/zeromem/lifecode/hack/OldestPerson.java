package org.zeromem.lifecode.hack;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by zeromem on 2017/9/8.
 */
public class OldestPerson {
	public static void main(String[] args) {
		Collection<Person> people = new LinkedList<>();
		people.add(new Person(10));
		people.add(null);
		people.add(null);
		people.add(new Person(12));
		people.add(new Person(8));
		people.add(null);

		System.out.println(getOldestPerson(people));
	}

	public static Person getOldestPerson(Collection<Person> people) {
		if (people.isEmpty()) {
			return null;
		}

		Person oldest = null;

		for (Person p : people) {
			if (p != null && (oldest == null || p.olderThan(oldest))) {
				oldest = p;
			}
		}

		return oldest;
	}
}

class Person {
	private int age;

	public Person(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"age=" + age +
				'}';
	}

	boolean olderThan(Person other) {
		return other == null || age > other.age;
	}
}
