package com.nextdest.model;

public enum  ReactionType {
    INTERESTED('I'),
    MAY_GO('M'),
    NO_WAY_TO_GO('N');

    private char type;

    ReactionType(char type){
        this.type = type;
    }

    public char value(){
        return this.type;
    }


    /*public ReactionType valueOf(String value){
        ReactionType[] reactions = values();
        if(value==null) return null;
        for (int i = 0; i < reactions.length; i++) {
            if(reactions[i].value()==value.charAt(0))return reactions[i];
        }
        return null;
    }*/


}
