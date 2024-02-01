package com.app.assignment1.data


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        //private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val STUDENT_ID_KEY = stringPreferencesKey("student_id")
        private val EMAIL_KEY = stringPreferencesKey("email_id")
        private val USER_NAME_ID = stringPreferencesKey("user_name_id")
    }

    /*val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        //preferences[USER_TOKEN_KEY] ?: ""

    }*/

    val getAccessStudentId: Flow<String> = context.dataStore.data.map { preferences ->

        preferences[STUDENT_ID_KEY] ?: ""

    }
    val getAccessEmailId: Flow<String> = context.dataStore.data.map { preferences ->


        preferences[EMAIL_KEY] ?: ""
    }
    val getAccessuserNameId: Flow<String> = context.dataStore.data.map { preferences ->


        preferences[USER_NAME_ID] ?: ""
    }
    /*suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            //preferences[USER_TOKEN_KEY] = token

        }
    }*/

    suspend fun saveStudentId(token: String) {
        context.dataStore.edit { preferences ->

            preferences[STUDENT_ID_KEY] = token

        }
    }
    suspend fun saveEmailId(token: String) {
        context.dataStore.edit { preferences ->

            preferences[EMAIL_KEY] = token

        }
    }
    suspend fun saveuserNameId(token: String) {
        context.dataStore.edit { preferences ->

            preferences[USER_NAME_ID] = token

        }
    }
}