package edu.utsa.cs3443.ibs074_lab5.model;

public class Role {
    private String roleName;

    // Constructor
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getter
    public String getRoleName() {
        return roleName;
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return roleName.equals(role.roleName);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    // Override toString method
    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}