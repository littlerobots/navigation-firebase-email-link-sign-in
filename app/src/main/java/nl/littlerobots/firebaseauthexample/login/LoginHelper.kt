package nl.littlerobots.firebaseauthexample.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_USER_EMAIL = "email"

fun getUserEmail(context: Context): String? =
    getLoginPreferences(context).getString(KEY_USER_EMAIL, null)

fun saveUserEmail(context: Context, email: String) {
    getLoginPreferences(context).edit {
        putString(KEY_USER_EMAIL, email)
    }
}

private fun getLoginPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("login", 0)