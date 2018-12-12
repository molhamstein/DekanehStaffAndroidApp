package com.brain_socket.dekanehstaff.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class CacheStore {

    private Context context;
    private Session session;

    @Inject
    public CacheStore(Context context, Session session) {
        this.context = context;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    private SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}