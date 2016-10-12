package me.iz.mobility.loginextassignment.impl;

import io.realm.Realm;
import me.iz.mobility.loginextassignment.models.CustomerInfo;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.utils.RealmHelper;

/**
 * This class is just used to create the Orders for the first time.
 * Created by ibasit on 10/10/2016.
 */
public class DummyDataCreator {

    public static void dumpData() {

        Realm realm = RealmHelper.getRealmInstance();
        // Making Dummy Customers
        RealmHelper.insertIntoRealm(realm,new CustomerInfo(1, "Basit Parkar", "Ram Baug Colony, Off Paud Road, Pune 511038", "9860000000"));
        RealmHelper.insertIntoRealm(realm,new CustomerInfo(2, "Deepak Thomas", "Sapphire Park, Near Balewadi Stadium, Pune 511058", "989764578"));
        RealmHelper.insertIntoRealm(realm,new CustomerInfo(3, "Chinmay Bhanagay", "Sakar Sankul, Off Shivaji Nagar Station, Pune 5110001", "89371316232"));
        RealmHelper.insertIntoRealm(realm,new CustomerInfo(4, "Hrishi Nene", "Pinnac Housing Society, Karve Nagar, Pune 511052", "9860000123"));
        RealmHelper.insertIntoRealm(realm,new CustomerInfo(5, "Arti Gambhir", "iDot Society, Bavdhan, Pune 511038", "9860000000"));


        //Adding Individual Items
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new Laptop()));
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new Monitor()));
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new SmartBand()));
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new Mouse()));
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new Mobile()));
        RealmHelper.insertIntoRealm(realm, new ItemImpl(new MobileCover()));

        // Adding Orders
        RealmHelper.insertIntoRealm(realm,prepareOrder_9());
        RealmHelper.insertIntoRealm(realm,prepareOrder_2());
        RealmHelper.insertIntoRealm(realm,prepareOrder_3());
        RealmHelper.insertIntoRealm(realm,prepareOrder_9());
        RealmHelper.insertIntoRealm(realm,prepareOrder_6());
        RealmHelper.insertIntoRealm(realm,prepareOrder_10());
        RealmHelper.insertIntoRealm(realm,prepareOrder_9());
        RealmHelper.insertIntoRealm(realm,prepareOrder_8());
        RealmHelper.insertIntoRealm(realm,prepareOrder_1());
        RealmHelper.insertIntoRealm(realm,prepareOrder_6());
        RealmHelper.insertIntoRealm(realm,prepareOrder_5());
        RealmHelper.insertIntoRealm(realm,prepareOrder_4());
        RealmHelper.insertIntoRealm(realm,prepareOrder_9());
        RealmHelper.insertIntoRealm(realm,prepareOrder_2());
    }


    private static Order prepareOrder_1() {
        Order order = new Order(1);
        order.addItems(new Laptop());
        return order;
    }

    private static Order prepareOrder_2() {
        Order order = new Order(2);
        order.addItems(new Mouse());
        return order;
    }

    private static Order prepareOrder_3() {
        Order order = new Order(3);
        order.addItems(new Mouse());
        order.addItems(new SmartBand());
        return order;
    }

    private static Order prepareOrder_4() {
        Order order = new Order(4);
        order.addItems(new Laptop());
        return order;
    }

    private static Order prepareOrder_5() {
        Order order = new Order(5);
        order.addItems(new Laptop());
        return order;
    }

    private static Order prepareOrder_6() {
        Order order = new Order(2);
        order.addItems(new Laptop());
        order.addItems(new Mouse());
        return order;
    }

    private static Order prepareOrder_7() {
        Order order = new Order(1);
        order.addItems(new Laptop());
        order.addItems(new Monitor());
        return order;
    }

    private static Order prepareOrder_8() {
        Order order = new Order(4);
        order.addItems(new Monitor());
        order.addItems(new SmartBand());
        return order;
    }

    private static Order prepareOrder_9() {
        Order order = new Order(3);
        order.addItems(new Mobile());
        order.addItems(new MobileCover());
        order.addItems(new SmartBand());
        return order;
    }

    private static Order prepareOrder_10() {
        Order order = new Order(5);
        order.addItems(new Mobile());
        return order;
    }
}
