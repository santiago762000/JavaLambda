import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by javiersantipanchi on 14/9/17.
 */
public class Util {
	public static void main(String... args) {
		List<Person> people = new ArrayList<>();
		people.add(new Person("AAA", "aaa", 10));
		people.add(new Person("BBB", "bbb", 11));
		people.add(new Person("CCC", "ccc", 12));
		people.add(new Person("DDD", "ddd", 13));
		people.add(new Person("EEE", "eee", 14));
		people.add(new Person("FFF", "fff", 15));
		people.add(new Person("GGG", "ggg", 16));
		simpleIteration(people, 11);

	}

	public static void simpleIteration(List<Person> people, Integer value) {
		Stream<Person> filteredValues = people.stream().filter(p -> p.getAge() > value);
		filteredValues.forEach(p -> System.out.println("Filtering" + p.toString()));

		//MAP to sum
		int sum = people.stream().mapToInt(p -> p.getAge()).sum();
		System.out.println(sum);

		//MAP to transform to another object
		Stream<Member> members = people.stream().map(Member::new);
		System.out.println("Members:");
		members.forEach(p -> System.out.println(p));

		//To transform to an ArrayList
		List<Person> list = people.stream().collect(Collectors.toCollection(ArrayList::new));
		list.forEach(p -> System.out.println(p));

		//Creating a map:
		Map<String, Person> peopleMap = people.stream().collect(Collectors.toMap(Person::getLastName, p -> p));
		System.out.println(peopleMap);

		//Changing values in the map:
		Map<String, Person> peopleMap2 = people.stream().collect(Collectors.toMap(Person::getLastName, p -> {
			p.setAge(p.getAge() * value);
			return p;
		}));
		System.out.println(peopleMap2);

		//Getting just one property to Array.
		String[] names = people.stream().map(Person::getLastName).toArray(String[]::new);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}

		//Changing values to a new collection of Strings.
		String[] names2 = people.stream().map(p -> p.getFirstName()+"aaa").toArray(String[]::new);
		for (int i = 0; i < names2.length; i++) {
			System.out.println(names2[i]);
		}

		//To modify values of the original collection
		people.forEach(p -> p.setAge(p.getAge() * value));


		//Without creating a new object
		Stream.of(Locale.getAvailableLocales())
				.map(Locale::getDisplayLanguage)
				.forEach(System.out::println);

		//Comparing reverse order
		people.stream().sorted((p1,p2) -> Integer.compare(p2.getAge(),p1.getAge())).forEach(System.out::println);

		//Getting the first element with optional
		Optional<Person> firstPerson=people.stream().sorted((p1,p2) -> Integer.compare(p2.getAge(),p1.getAge())).findFirst();
		System.out.println("First Person:"+firstPerson);

		//Ordering with String
		Comparator<Person> byFirstName=(p1,p2)->p2.getFirstName().compareTo(p1.getFirstName());
		Comparator<Person> byLastNameName=(p1,p2)->p2.getLastName().compareTo(p1.getLastName());
		people.stream().sorted(byFirstName.thenComparing(byLastNameName)).forEach(System.out::println);


	}
}
