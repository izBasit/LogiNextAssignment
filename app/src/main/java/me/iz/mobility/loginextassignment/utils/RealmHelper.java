

package me.iz.mobility.loginextassignment.utils;

import android.support.annotation.NonNull;

import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author ibasit
 */
public class RealmHelper {

    private final String TAG = getClass().getSimpleName();


    @DebugLog
    public static Realm getRealmInstance() {

        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("ln_order_management")
                .deleteRealmIfMigrationNeeded()
                .build();
        // Get a Realm instance for this thread

        return Realm.getInstance(realmConfig);
    }

    @DebugLog
    public static void updateRealm(@NonNull Realm realm, RealmObject object) {
        realm.beginTransaction();
        try {
            realm.copyToRealmOrUpdate(object);
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void updateRealm(@NonNull Realm realm, RealmObject... objects) {
        realm.beginTransaction();
        try {
            for (RealmObject obj : objects) {
                realm.copyToRealmOrUpdate(obj);
            }
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void updateRealm(@NonNull Realm realm, RealmList<? extends RealmObject> object) {
        realm.beginTransaction();
        try {
            realm.copyToRealmOrUpdate(object);
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void insertIntoRealm(@NonNull Realm realm, RealmObject object) {
        realm.beginTransaction();
        try {
            realm.copyToRealm(object);
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void insertIntoRealm(@NonNull Realm realm, RealmList<? extends RealmObject> object) {
        realm.beginTransaction();
        try {
            realm.copyToRealm(object);
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void insertIntoRealm(@NonNull Realm realm, RealmObject... objects) {
        realm.beginTransaction();
        try {
            for (RealmObject obj : objects) {
                realm.copyToRealm(obj);
            }
        } finally {
            realm.commitTransaction();
        }
    }

    @DebugLog
    public static void deleteAll() {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        try {
            realm.deleteAll();
        } finally {
            realm.commitTransaction();
        }
    }
}
