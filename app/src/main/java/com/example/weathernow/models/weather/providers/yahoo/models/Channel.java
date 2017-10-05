
package com.example.weathernow.models.weather.providers.yahoo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("units")
    @Expose
    private Units units;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

}
