package it.mbdev.meminiaward.security;

public enum Roles {

    ROLE_USER("User Role", "Regular user with basic permissions"),
    ROLE_VOTER("Voter role", "User who can vote an award"),
    ROLE_AWARDER("Award creator role", "User who can create an award"),
    ROLE_ADMIN("Admin Role", "Administrator with full access");

    private final String roleName;
    private final String roleDescription;

    Roles(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
