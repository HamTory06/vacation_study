import android.content.SharedPreferences
import android.os.Bundle
import android.os.PatternMatcher
import android.text.TextUtils
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.preferences.R

class MySettingFragment: PreferenceFragmentCompat(),
SharedPreferences.OnSharedPreferenceChangeListener{
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        Log.d("상태","onCreatePreferences()")
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")
        idPreference?.isVisible = true

        idPreference?.summary = "code summary"
        idPreference?.title = "code title"

        idPreference?.summaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference>{ preference ->
                val text = preference.text
                if(TextUtils.isEmpty(text)){
                    "설정이 되지 않았습니다"
                } else {
                    "설정된 ID 값은 : $text 입니다"
                }
            }

        val sharedPreference = activity?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val id = sharedPreference?.getString("id","")

        idPreference?.setOnPreferenceChangeListener{ preference, newValue ->
            Log.d("상태","preference key : ${preference.key}, newValue : $newValue")
            true
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == "id"){
            Log.d("상태","newValue : " + sharedPreferences?.getString("id",""))
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences
            ?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences
            ?.registerOnSharedPreferenceChangeListener(this)
    }


}