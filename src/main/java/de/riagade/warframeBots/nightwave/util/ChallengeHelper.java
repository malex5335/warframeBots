package de.riagade.warframeBots.nightwave.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ChallengeHelper {
    public static final Map<String, String> descriptionMap;
    static{
        descriptionMap = new TreeMap<String, String>();
        /* dailies */
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailySlideKills","Kill 20 Enemies while Sliding");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyCompleteMission","Complete a Mission");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Deploy an Air Support Charge in a Mission");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Heat Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Magnetic Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Gas Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Play a game of Frame Fighter, Happy Zephyr, or Wyrmius");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Mark 5 Mods or Resources");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Suspend 5 or more enemies in the air at once with a Heavy Slam Melee Attack");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Cold Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Blast Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Deploy a Specter");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Pick up 20 Energy Orbs");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyTransmuteMods","Complete 3 Transmutations");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 10 Enemies with Finishers");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyPlayEmote","Play 1 Emote");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","While piloting a hijacked Crewship, destroy 3 enemy Fighters");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Collect 100 Resources");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyAimGlide","Kill 20 Enemies while Aim Gliding");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Deploy a Glyph");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Hack 8 Consoles");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Complete a Mission with only a Primary Weapon equipped");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill a Kuva Thrall");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Visit a Featured Dojo");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Open 20 Lockers");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Interact with your Kubrow or Kavat");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 40 Enemies with Headshots");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Corrosive Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with a Primary Weapon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Mercy Kill an Enemy");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Donate to the Leverian");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Toxin Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Abilities");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Radiation Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Clear a personal Kuva Lich Influenced Node");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Scan 25 Objects or Enemies");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Pick up 15,000 Credits");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Viral Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Pick up 8 Mods");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with Electricity Damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Complete a Mission with only a Secondary Weapon equipped");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with a Secondary Weapon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Kill 150 Enemies with a Melee Weapon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Complete a Mission with only a Melee Weapon equipped");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Find 5 Syndicate Medallions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDaily","Bullet Jump 150 times");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Daily/SeasonDailyKillEnemies","Kill 200 Enemies");
        /* weeklies */
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Fully socket 3 Ayatan Sculptures");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Assassination missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Find all caches in 3 Sabotage missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Hijack a Crewship from the enemy");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 6 different Perfect Animal Captures");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 12 Conduits in Disruption");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Kuva Siphon Missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 5 different Bounties in the Plains of Eidolon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Catch 6 Rare Fish in the Plains of Eidolon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Mine 6 Rare Gems or Ore in the Plains of Eidolon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Exterminate missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Pick up 8 Rare Mods");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Kill 30 Eximus");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Railjack Missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Help Clem with his weekly mission");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Clear a Railjack Boarding Party without your Warframe taking damage");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Destroy a Crewship with Forward Artillery");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyKillArchgunEnemies","Kill 500 enemies with an Arch Gun");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 9 Invasion missions of any type");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Capture missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Kill 500 Enemies");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteVenusRace","Complete 3 different K-Drive races in Orb Vallis");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Spy missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Polarize with Forma 1 time (not in Simulacrum)");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteMobileDefense","Complete 3 Mobile Defense missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Rescue missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Sabotage missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 5 Scans for Cephalon Simaris");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 3 Nightmare missions of any type");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 1 Sortie");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCompleteSyndicateMissions","Complete 10 Syndicate missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 8 waves of Sanctuary Onslaught");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Kill a Tusk Thumper in the Plains of Eidolon");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Unlock 3 Relics");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Unlock 4 Orokin Derelict Vaults");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Complete 5 different bounties in the Orb Vallis");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeeklyCatchRareVenusFish","Catch 6 Rare Servofish in the Orb Vallis");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/Weekly/SeasonWeekly","Mine 6 Rare Gems or Ore in the Orb Vallis");
        /* elite weeklies */
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete 4 Halls of Ascension on Lua");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill or Convert a Kuva Lich");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Win 3 wagers in a row without letting the enemy score in one match of The Index");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete a Defense mission reaching at least wave 20");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete 8 Railjack Missions");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete 8 Zones of Elite Sanctuary Onslaught");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardKillEximus","Kill 100 Eximus");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Defeat the Ropalolyst");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill The Exploiter Orb");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill 1 Silver Grove Specter");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Survive over 30 minutes in Kuva Survival");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill or Capture an Eidolon Hydrolyst");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill Profit-Taker");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill 1,500 Enemies");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete a Spy mission with 3 manual console hacks and no alarms");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete 10 Nightmare missions of any type");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete an Extermination mission with only level 30 or higher enemies without triggering alarms");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete 3 Sorties");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHardFastCapture","Finish a Capture mission in less than 90 seconds");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Complete a Survival mission reaching at least 30 minutes");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Unlock 10 Relics");
        descriptionMap.put("/Lotus/Types/Challenges/Seasons/WeeklyHard/SeasonWeeklyHard","Kill a Tusk Thumper Doma in the Plains of Eidolon");
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
