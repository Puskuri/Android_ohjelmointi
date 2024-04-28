package com.example.helloworld;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import io.reactivex.Single;

public class DataStoreSingleton {
    private static final DataStoreSingleton ourInstance = new DataStoreSingleton();
    private RxDataStore<Preferences> datastore;
    private io.reactivex.schedulers.Schedulers Schedulers;

    public static DataStoreSingleton getInstance() {
        return ourInstance;
    }

    private DataStoreSingleton() { }

    public void setDataStore(RxDataStore<Preferences> datastore) {
        this.datastore = datastore;
    }

    public Single<RxDataStore<Preferences>> getDataStore() {
        if (datastore == null) {
            datastore = new RxPreferenceDataStoreBuilder(new GameActivity(), "my_datastore").build();
        }
        return Single.just(datastore);
    }

    public Single<Boolean> putStringValue(String key, String value) {
        return getDataStore()
                .flatMap(dataStore -> dataStore.updateDataAsync(preferences -> {
                    MutablePreferences mutablePreferences = preferences.toMutablePreferences();
                    mutablePreferences.set(PreferencesKeys.stringKey(key), value);
                    return Single.just(mutablePreferences);
                }))
                .subscribeOn(Schedulers.io())
                .map(result -> true)
                .onErrorReturnItem(false);
    }

    public Single<String> getStringValue(String key) {
        return getDataStore()
                .flatMap(dataStore -> dataStore.data().firstOrError())
                .map(preferences -> preferences.get(PreferencesKeys.stringKey(key)))
                .subscribeOn(Schedulers.io());
    }
}