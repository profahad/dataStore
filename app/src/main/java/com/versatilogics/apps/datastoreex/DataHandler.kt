package com.versatilogics.apps.datastoreex

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class DataHandler(private val context: Context) {

    // Create the dataStore and give it a name same as shared preferences
    private val dataStore = context.dataStore

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
    }

    // Store user data
    // refer to the data store and using edit
    // we can store values using the keys
    fun storeUser(age: Int, name: String) {
        CoroutineScope(context.).launch {
            dataStore.edit {
                it[USER_AGE_KEY] = age
                it[USER_NAME_KEY] = name
            }
        }
    }

    // Create an age flow to retrieve age from the preferences
    // flow comes from the kotlin coroutine
    val userAgeFlow: Flow<Int> = dataStore.data.map {
        it[USER_AGE_KEY] ?: 0
    }

    // Create a name flow to retrieve name from the preferences
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }
}