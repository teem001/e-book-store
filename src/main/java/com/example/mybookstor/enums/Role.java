package com.example.mybookstor.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.mybookstor.enums.Permission.*;


public enum Role {
    SUPER_ADMIN(Sets.newHashSet(BOOK_WRITE,BOOK_READ,BOOK_CREATE,BOOK_WRITE, ADMIN_CREATE,ADMIN_DELETE,ADMIN_WRITE,ADMIN_READ)),

    ADMIN(Sets.newHashSet(BOOK_WRITE,BOOK_READ,BOOK_CREATE,BOOK_WRITE, PRICE_CREATE, PRICE_DELETE, PRICE_READ,PRICE_WRITE)),

    USER(Sets.newHashSet(BOOK_READ, PRICE_READ));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    //TODO grant the each roles with the authorities in their respective sets
    public Set<GrantedAuthority> grantedAuthorities(){
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return permissions;

    }

}

