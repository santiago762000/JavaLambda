/**
 * Created by javiersantipanchi on 14/9/17.
 */
public class Member {
	private String firstName;
	private String lastName;

	public Member(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Member(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
	}

	@Override
	public String toString() {
		return "Member{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
