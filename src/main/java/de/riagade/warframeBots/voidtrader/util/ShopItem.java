package de.riagade.warframeBots.voidtrader.util;

import lombok.Data;

@Data
public class ShopItem {
    private String name;
    private String description;
    private String category;
    private String ducats;
    private String credits;
    private boolean newItem;

    public ShopItem(String name, int ducats, int credits){
        setName(name);
        setDescription(ShopItemHelper.getDescription(name));
        setCategory(ShopItemHelper.getCategory(name));
        setDucats(ShopItemHelper.formatNumber(ducats));
        setCredits(ShopItemHelper.formatNumber(credits));
        setNewItem(ShopItemHelper.isNewItem(this));
    }
}
