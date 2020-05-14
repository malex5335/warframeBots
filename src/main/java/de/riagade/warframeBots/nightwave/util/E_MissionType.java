package de.riagade.warframeBots.nightwave.util;

public enum E_MissionType {
    DAILY(0), WEEKLY(1), ELITE(2);
    private int value;

    E_MissionType(int value){
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }
}
