package de.riagade.warframeBots.voidtrader.util;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.GenericJSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ShopItemHelper {
    public static final String SHOP_ITEM_DATA = "baroshop.json";

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
            int catNumber = 0;
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
            JSONObject shopItems = GenericJSONParser.getJSONFromResource(ShopItemHelper.SHOP_ITEM_DATA)
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
            categories = GenericJSONParser.getJSONFromResource(ShopItemHelper.SHOP_ITEM_DATA)
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

    public static String createMessageForItemGroups(Map<String, List<ShopItem>> orderedShopItems) {
        StringBuilder sb = new StringBuilder();
        for(String key : orderedShopItems.keySet()){
            List<ShopItem> items = orderedShopItems.get(key);
            sb.append(ShopItemHelper.createMessageForItemGroup(key, orderedShopItems.get(key)));
        }
        return sb.toString();
    }

    public static String createMessageForItemGroup(String key, List<ShopItem> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("```ini\n");
        sb.append("[").append(key).append("]\n");
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

    public static String createBaroMessage(BasicBot bot,
                                           Date startDate,
                                           Date expireDate,
                                           Map<String, List<ShopItem>> orderedShopItems) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm z", bot.getLocale());
        StringBuilder sb = new StringBuilder();
        sb.append("```css\n");
        sb.append("[Times]\n");
        sb.append("``````diff\n");
        sb.append("- Arrived: ").append(dateFormat.format(startDate)).append("\n");
        sb.append("- Leaving: ").append(dateFormat.format(expireDate)).append("\n");
        sb.append("```\n");
        sb.append(createMessageForItemGroups(orderedShopItems));
        return sb.toString();
    }
}