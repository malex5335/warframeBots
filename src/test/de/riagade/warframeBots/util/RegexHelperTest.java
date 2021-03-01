package de.riagade.warframeBots.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexHelperTest {

    @Test
    void containsExactSubstring() {
        assertTrue(RegexHelper.containsExactSubstring(
                "+ /Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump\n+ 7,000 Standing",
                "/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        assertFalse(RegexHelper.containsExactSubstring(
                "+ /Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJumpHeight\n+ 2,000 Standing",
                "/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
        assertFalse(RegexHelper.containsExactSubstring(
                "+ /Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJum\n+ 1,000 Standing",
                "/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyBulletJump"));
    }
}