package de.riagade.warframeBots.nightwave.util;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeHelperTest {

    @org.junit.jupiter.api.Test
    void getDescription() {
        System.out.println("text -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println("key -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println("text -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println("key -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println("text -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println("key -> " + ChallengeHelper.getDescription("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }

    @org.junit.jupiter.api.Test
    void isDaily() {
        System.out.println("true -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println("true -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println("false -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println("false -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println("false -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println("false -> " + ChallengeHelper.isDaily("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }

    @org.junit.jupiter.api.Test
    void isWeekly() {
        System.out.println("false -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println("false -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println("true -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println("true -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println("false -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println("false -> " + ChallengeHelper.isWeekly("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }

    @org.junit.jupiter.api.Test
    void isElite() {
        System.out.println("false -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println("false -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println("false -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println("false -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println("true -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println("true -> " + ChallengeHelper.isElite("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }

    @org.junit.jupiter.api.Test
    void getStanding() {
        System.out.println("1,000 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println("1,000 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println("4,500 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println("4,500 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println("7,000 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println("7,000 -> " + ChallengeHelper.getStanding("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }

    @org.junit.jupiter.api.Test
    void getMissionType() {
        System.out.println(E_ChallengeType.DAILY + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        System.out.println(E_ChallengeType.DAILY + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyRandomMission"));
        System.out.println(E_ChallengeType.WEEKLY + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteRescue"));
        System.out.println(E_ChallengeType.WEEKLY + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyRandomMission"));
        System.out.println(E_ChallengeType.ELITE + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardCompleteNightmareMissions"));
        System.out.println(E_ChallengeType.ELITE + " -> " + ChallengeHelper.getMissionType("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardRandomMission"));
    }
}