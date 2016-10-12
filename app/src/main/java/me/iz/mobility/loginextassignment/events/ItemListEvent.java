package me.iz.mobility.loginextassignment.events;

import java.util.List;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/12/2016.
 */

public class ItemListEvent {

    private List<Item> itemList;

    public ItemListEvent(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
