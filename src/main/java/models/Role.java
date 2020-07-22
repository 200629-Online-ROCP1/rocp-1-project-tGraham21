package models;

public class Role {
	private int roleId;
	private String role;
	
	public Role (int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
	
	public Role () { }
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int id) {
		this.roleId = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
