package uz.pdp.store.security.roles;



import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.apache.commons.compress.utils.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import static uz.pdp.store.security.roles.Permission.*;


public enum Role {


    CUSTOMER(Sets.newHashSet(READ)),

    ADMIN(Sets.newHashSet(READ, CREATE, UPDATE, DELETE));

    final Set<Permission> permissions;

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> set = permissions.stream().map(permission ->
                new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        set.add(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
        return set;
    }


    public Set<String> getGrantedAuthoritiesNames(){
        Set<String> set = permissions.stream().
                map(Permission::getName).collect(Collectors.toSet());
        set.add("ROLE_".concat(this.name()));
        return set;
    }


}
