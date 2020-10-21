package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.GenericJSONParser;
import de.riagade.warframeBots.voidtrader.util.ShopItem;
import de.riagade.warframeBots.voidtrader.util.ShopItemHelper;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

@Getter
@Setter
public class ShopReminder extends TimerTask {
    private BasicBot bot;

    public ShopReminder(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        Date expireDate = getExpireDate();
        List<ShopItem> shopItemList = generateShopItems();
        String msg = ShopItemHelper.createBaroMessage(getBot(), getStartDate(), getExpireDate(),
                orderShopItems(shopItemList));
        getBot().sendMessage(msg);
    }

    private Map<String, List<ShopItem>> orderShopItems(List<ShopItem> shopItemList) {
        Map<String, List<ShopItem>> orderedShopItems = new TreeMap<String, List<ShopItem>>();
        for(ShopItem item : shopItemList){
            String key = item.getCategory();
            if(orderedShopItems.containsKey(key)){
                orderedShopItems.get(key).add(item);
            } else {
                List<ShopItem> items = new ArrayList<ShopItem>();
                items.add(item);
                orderedShopItems.put(key, items);
            }
        }
        return orderedShopItems;
    }

    private Date getExpireDate() {
        return getDate("Expiry");
    }

    private Date getStartDate() {
        return getDate("Activation");
    }

    private Date getDate(String key) {
        Date expireDate = new Date(0);
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONArray voidTraders = object.getJSONArray("VoidTraders");
            JSONObject firstEntry = voidTraders.getJSONObject(0);
            String expiry = firstEntry.getJSONObject(key).getJSONObject("$date").getString("$numberLong");
            Calendar expireDateTmp = Calendar.getInstance(getBot().getLocale());
            if(StringUtils.isNumeric(expiry)) {
                expireDateTmp.setTimeInMillis(Long.parseLong(expiry));
            }
            expireDate = expireDateTmp.getTime();
        } catch (Exception e){
            e.printStackTrace();
        }
        return expireDate;
    }

    private List<ShopItem> generateShopItems() {
        List<ShopItem> shopItems = new ArrayList<ShopItem>();
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONArray voidTraders = object.getJSONArray("VoidTraders");
            JSONObject firstEntry = voidTraders.getJSONObject(0);
            JSONArray manifest = firstEntry.getJSONArray("Manifest");
            for(int i = 0; i < manifest.length(); i++){
                JSONObject item = manifest.getJSONObject(i);
                String name = item.getString("ItemType");
                int ducats = item.getInt("PrimePrice");
                int credits = item.getInt("RegularPrice");
                ShopItem shopItem = new ShopItem(name, ducats, credits);
                shopItems.add(shopItem);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return shopItems;
    }

}