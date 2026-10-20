package model;

public class Sponsor {
	private int id;
	private String name;
	private double investment;
	private User user; // FK

	public Sponsor() {
		this(0);
	}

	public Sponsor(int id) {
		this.id = id;
		this.name = "";
		this.investment = 0.0;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}