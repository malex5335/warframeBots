package de.riagade.warframeBots.voidtrader.util;

public class ShopItem {
    private String name, description, category,ducats, credits;

    public ShopItem(String name, int ducats, int credits){
        setName(name);
        setDescription(ShopItemHelper.getDescription(name));
        setCategory(ShopItemHelper.getCategory(name));
        setDucats(ShopItemHelper.formatNumber(ducats));
        setCredits(ShopItemHelper.formatNumber(credits));
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    public String getDucats() {
        return ducats;
    }

    private void setDucats(String ducats) {
        this.ducats = ducats;
    }

    public String getCredits() {
        return credits;
    }

    private void setCredits(String credits) {
        this.credits = credits;
    }

}