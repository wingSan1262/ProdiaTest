package vanrrtech.app.prodiaappsample.UtilServices.shared_preference

import android.app.Application
import android.content.Context

class SharedPreferenceService(application: Application) {

    val APP_STORAGE_NAMEKEY = "whole-app-storage"

    val sharedPref = application.getSharedPreferences(
        APP_STORAGE_NAMEKEY, Context.MODE_PRIVATE)

    /** good thing add some encryptor and decryptor
     * dependancy later on for storing user credential
     * **/
    fun insertString(key : String, value : String){
        with (sharedPref.edit()){
            putString(key, value)
            apply()
        }
    }

    fun delete(key : String){
        with(sharedPref.edit()){
            remove(key)
            apply()
        }
    }

    fun getString(key : String): String {
        return sharedPref.getString(key, "").toString()
    }



}