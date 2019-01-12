package com.nextdest.model;

public enum  ReactionType {
    INTERESTED('I'),
    MAY_GO('M'),
    NO_WAY_TO_GO('N');

    private char type;

    ReactionType(char type){
        this.type = type;
    }




}
