package com.demo.bank.entity;

public enum LoanType {
    CAR, HOME;

    public String toString() {
        switch (this) {
            case CAR:
                return "CAR";
            case HOME:
                return "HOME";
        }
        return "";
    }
}