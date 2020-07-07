package com.wcs.live.jparest.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum Achievement {
    GOLEM("Survive a hit of 500 damage or more"),
    THERE_IS_NO_SPOON("Finish the game within 8 hours"),
    MASS_PRODUCTION_1("Produce 10k electronic circuits"),
    ECO_UNFRIENDLY("Research oil processing (research)");

    private final String desc;
    Achievement(String desc) {
        this.desc = desc;
    };

    @JsonCreator
    public static Achievement fromText(String value) {
        System.out.println("value> " + value);
        Achievement achievement = Achievement.valueOf(value.toUpperCase());
        System.out.println("instance> " + achievement);
        return Achievement.valueOf(value);
    }
}
