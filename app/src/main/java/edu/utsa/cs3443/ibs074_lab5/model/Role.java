package edu.utsa.cs3443.ibs074_lab5.model;

/**
 * The Role class represents a role in a scene or act.
 * @author Yael Reyes ibs074
 */
public class Role {
    // The name of the role
    private String roleName;

    /**
     * Constructor for creating a Role instance with a specified role name.
     *
     * @param roleName The name of the role.
     */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets the name of the role.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Overrides the equals method to compare roles based on their names.
     *
     * @param obj The object to compare with.
     * @return True if the roles are equal (have the same name), false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return roleName.equals(role.roleName);
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the role's name.
     *
     * @return The hash code of the role.
     */
    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    /**
     * Overrides the toString method to provide a string representation of the role.
     *
     * @return A string representation of the role.
     */
    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
