package de.riagade.warframeBots.nightwave.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ChallengeHelper {
    public static final String DAILY_PREFIX = "/Lotus/Types/Challenges/Seasons/Daily/";
    public static final String WEEKLY_PREFIX = "/Lotus/Types/Challenges/Seasons/SeasonWeekly/";
    public static final String ELITE_PREFIX = "/Lotus/Types/Challenges/Seasons/WeeklyHard/";
    public static final Map<String, String> descriptionMap;
    static{
        descriptionMap = new TreeMap<String, String>();
        /* dailies */
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailySlideKills","Kill 20 Enemies while Sliding");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailyCompleteMission","Complete a Mission");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Deploy an Air Support Charge in a Mission");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Heat Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Magnetic Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Gas Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Play a game of Frame Fighter, Happy Zephyr, or Wyrmius");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Mark 5 Mods or Resources");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Suspend 5 or more enemies in the air at once with a Heavy Slam Melee Attack");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Cold Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Blast Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Deploy a Specter");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Pick up 20 Energy Orbs");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailyTransmuteMods","Complete 3 Transmutations");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 10 Enemies with Finishers");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailyPlayEmote","Play 1 Emote");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","While piloting a hijacked Crewship, destroy 3 enemy Fighters");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Collect 100 Resources");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailyAimGlide","Kill 20 Enemies while Aim Gliding");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Deploy a Glyph");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Hack 8 Consoles");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Complete a Mission with only a Primary Weapon equipped");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill a Kuva Thrall");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Visit a Featured Dojo");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Open 20 Lockers");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Interact with your Kubrow or Kavat");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 40 Enemies with Headshots");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Corrosive Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with a Primary Weapon");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Mercy Kill an Enemy");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Donate to the Leverian");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Toxin Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Abilities");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Radiation Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Clear a personal Kuva Lich Influenced Node");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Scan 25 Objects or Enemies");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Pick up 15,000 Credits");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Viral Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Pick up 8 Mods");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with Electricity Damage");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Complete a Mission with only a Secondary Weapon equipped");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with a Secondary Weapon");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Kill 150 Enemies with a Melee Weapon");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Complete a Mission with only a Melee Weapon equipped");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Find 5 Syndicate Medallions");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDaily","Bullet Jump 150 times");
        descriptionMap.put(ChallengeHelper.DAILY_PREFIX + "SeasonDailyKillEnemies","Kill 200 Enemies");
        /* weeklies */
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Fully socket 3 Ayatan Sculptures");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Assassination missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Find all caches in 3 Sabotage missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Hijack a Crewship from the enemy");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 6 different Perfect Animal Captures");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 12 Conduits in Disruption");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Kuva Siphon Missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 5 different Bounties in the Plains of Eidolon");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Catch 6 Rare Fish in the Plains of Eidolon");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Mine 6 Rare Gems or Ore in the Plains of Eidolon");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Exterminate missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Pick up 8 Rare Mods");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Kill 30 Eximus");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Railjack Missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Help Clem with his weekly mission");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Clear a Railjack Boarding Party without your Warframe taking damage");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Destroy a Crewship with Forward Artillery");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeeklyKillArchgunEnemies","Kill 500 enemies with an Arch Gun");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 9 Invasion missions of any type");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Capture missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Kill 500 Enemies");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeeklyCompleteVenusRace","Complete 3 different K-Drive races in Orb Vallis");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Spy missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Polarize with Forma 1 time (not in Simulacrum)");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeeklyCompleteMobileDefense","Complete 3 Mobile Defense missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Rescue missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Sabotage missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 5 Scans for Cephalon Simaris");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 3 Nightmare missions of any type");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 1 Sortie");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeeklyCompleteSyndicateMissions","Complete 10 Syndicate missions");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 8 waves of Sanctuary Onslaught");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Kill a Tusk Thumper in the Plains of Eidolon");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Unlock 3 Relics");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Unlock 4 Orokin Derelict Vaults");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Complete 5 different bounties in the Orb Vallis");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeeklyCatchRareVenusFish","Catch 6 Rare Servofish in the Orb Vallis");
        descriptionMap.put(ChallengeHelper.WEEKLY_PREFIX + "SeasonWeekly","Mine 6 Rare Gems or Ore in the Orb Vallis");
        /* elite weeklies */
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete 4 Halls of Ascension on Lua");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill or Convert a Kuva Lich");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Win 3 wagers in a row without letting the enemy score in one match of The Index");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete a Defense mission reaching at least wave 20");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete 8 Railjack Missions");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete 8 Zones of Elite Sanctuary Onslaught");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHardKillEximus","Kill 100 Eximus");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Defeat the Ropalolyst");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill The Exploiter Orb");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill 1 Silver Grove Specter");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Survive over 30 minutes in Kuva Survival");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill or Capture an Eidolon Hydrolyst");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill Profit-Taker");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill 1,500 Enemies");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete a Spy mission with 3 manual console hacks and no alarms");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete 10 Nightmare missions of any type");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete an Extermination mission with only level 30 or higher enemies without triggering alarms");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete 3 Sorties");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHardFastCapture","Finish a Capture mission in less than 90 seconds");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Complete a Survival mission reaching at least 30 minutes");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Unlock 10 Relics");
        descriptionMap.put(ChallengeHelper.ELITE_PREFIX + "SeasonWeeklyHard","Kill a Tusk Thumper Doma in the Plains of Eidolon");
    }

    public static String getDescription(String key){
        if(descriptionMap.containsKey(key)){
            return descriptionMap.get(key);
        }
        return key;
    }

    public static String getStanding(E_MissionType missionType) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        // DecimalFormatSymbols formatSymbols = format.getDecimalFormatSymbols();
        // formatSymbols.setGroupingSeparator('.');
        // format.setDecimalFormatSymbols(formatSymbols);
        return format.format(missionType.getValue());
    }
}
