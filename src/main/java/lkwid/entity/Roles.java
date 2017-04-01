package lkwid.entity;

public enum Roles {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	
	private String name;

	private Roles(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
	
}