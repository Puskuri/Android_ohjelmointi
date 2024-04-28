package com.example.helloworld;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.rxjava2.RxDataStore;

import io.reactivex.Single;

public class DataStoreHelper {
    private RxDataStore<Preferences> dataStoreRX;

    public DataStoreHelper(RxDataStore<Preferences> dataStoreRX) {
        this.dataStoreRX = dataStoreRX;
    }

    public Single<Boolean> putStringValue(String key, String value) {
        return DataStoreSingleton.getInstance().putStringValue(key, value);
    }

    public Single<String> getStringValue(String key) {
        return DataStoreSingleton.getInstance().getStringValue(key);
    }
}