package com.demo.bank.dto;


public enum RoleList {
     ROLE_ADMIN, ROLE_CUSTOMER;

    public  String toString() {
        switch (this) {
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_CUSTOMER:
                return "ROLE_CUSTOMER";
        }
        return "";
    }
}