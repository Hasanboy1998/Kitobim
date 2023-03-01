package uz.gita.khasanboy_kitobim.data.local.shared_prefrences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.khasanboy_kitobim.utils.Constants
import uz.gita.khasanboy_kitobim.utils.Constants.DARK_THEME_KEY
import uz.gita.khasanboy_kitobim.utils.extensions.SharedPreference
import uz.gita.khasanboy_kitobim.utils.Constants.TEXT_SIZE_KEY
import uz.gita.khasanboy_kitobim.utils.Constants.THEME_KEY
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context, sharedPreferences: SharedPreferences) :
    SharedPreference(context, sharedPreferences) {
    var themes: Int by Ints(defValue = 1)
    var textStyleType: Int by Ints(defValue = 2)
    var isDark: Boolean by Booleans(init = false)
}

class MySharedPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val shared: SharedPreferences = context.getSharedPreferences("Kitobim", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = shared.edit()

    fun putData(name: String, id: Long) {
        editor.putLong(name, id)
        editor.commit()
    }

    fun getData(name: String): Long {
        return shared.getLong(name, Constants.NULL_ID)
    }
}