package de.riagade.warframeBots.nightwave.util;

public enum E_MissionType {
    DAILY(1000), WEEKLY(4500), ELITE(7000);
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