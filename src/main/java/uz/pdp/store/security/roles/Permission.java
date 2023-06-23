package uz.pdp.store.security.roles;//package org.shop.system.security.roles;

public enum Permission {
    READ("READ"),
    UPDATE("UPDATE"),
    CREATE("CREATE"),
    DELETE("DELETE");

    private final String name;

    Permission(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
