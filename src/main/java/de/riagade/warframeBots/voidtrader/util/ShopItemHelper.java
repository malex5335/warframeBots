package de.riagade.warframeBots.voidtrader.util;

import com.google.inject.internal.util.Lists;
import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.GenericJSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ShopItemHelper {
    public static final String SHOP_ITEM_DATA = "baroshop.json";
    public static final int NEWLY_ADDED = 0;

    public static List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        try{
            JSONObject shopItems = GenericJSONParser.retrieveJSONFromResource(ShopItemHelper.SHOP_ITEM_DATA)
                    .getJSONObject("items");
            keys.addAll(Lists.newArrayList(shopItems.keys()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return keys;
    }

    public static String getDescription(String key) {
        try{
            JSONObject shopItem = getItem(key);
            if (!shopItem.isEmpty()) {
                return shopItem.getString("desc");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    public static String getCategory(String key) {
        try{
            int catNumber = ShopItemHelper.NEWLY_ADDED;
            JSONObject shopItem = getItem(key);
            if (!shopItem.isEmpty()) {
                catNumber = shopItem.getInt("cat");
            }
            if(!getCategories().isEmpty()) {
                return getCategories().getString(catNumber);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    private static JSONObject getItem(String key) {
        JSONObject shopItem = new JSONObject();
        try {
            JSONObject shopItems = GenericJSONParser.retrieveJSONFromResource(ShopItemHelper.SHOP_ITEM_DATA)
                    .getJSONObject("items");
            if(shopItems.has(key)) {
                shopItem = shopItems.getJSONObject(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopItem;
    }

    public static JSONArray getCategories() {
        JSONArray categories = new JSONArray();
        try{
            categories = GenericJSONParser.retrieveJSONFromResource(ShopItemHelper.SHOP_ITEM_DATA)
                    .getJSONArray("categories");
        } catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    public static String formatNumber(int value) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        return decimalFormat.format(value);
    }

    public static String createMessageForItemGroup(String category, List<ShopItem> items) {
        // @TODO: a template for this message would be nice
        StringBuilder sb = new StringBuilder();
        sb.append("```ini\n");
        sb.append("[").append(category).append("]\n");
        sb.append("``````diff");
        for(ShopItem item : items){
            sb.append(createMessageForItem(item));
        }
        sb.append("```");
        return sb.toString();
    }

    public static String createMessageForItem(ShopItem item){
        return "\n+ " + item.getDescription() + "\n" +
                "-- Ducats: " + item.getDucats() + "\n" +
                "-- Credits: " + item.getCredits() + "\n";
    }

    public static String createBaroTimeMessage(BasicBot bot,
                                           Date startDate,
                                           Date expireDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm z", bot.getLocale());
        return "```css\n" +
                "[Times]\n" +
                "``````diff\n" +
                "- Arrived: " + dateFormat.format(startDate) + "\n" +
                "- Leaving: " + dateFormat.format(expireDate) + "\n" +
                "```\n";
    }

    public static boolean isNewItem(ShopItem item) {
        return getCategories().get(NEWLY_ADDED).equals(item.getCategory());
    }
}
