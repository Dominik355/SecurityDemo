package com.example.securityDemo.Security.authorization;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.example.securityDemo.Security.authorization.UserPermission.*;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    STUDENT(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
    TEACHER(Sets.newHashSet(TEACHER_READ, TEACHER_WRITE)),
    ADMIN(Sets.newHashSet(STUDENT_READ, TEACHER_READ, STUDENT_WRITE, TEACHER_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
