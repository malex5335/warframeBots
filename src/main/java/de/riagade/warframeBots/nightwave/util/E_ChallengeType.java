package de.riagade.warframeBots.nightwave.util;

public enum E_ChallengeType {
    // @TODO: change the value of these for the future message template
    DAILY(0), WEEKLY(1), ELITE(2);
    private int value;

    E_ChallengeType(int value){
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

}