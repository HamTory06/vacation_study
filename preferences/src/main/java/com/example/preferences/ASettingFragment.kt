import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import com.example.preferences.R

class ASettingFragment: PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_a, rootKey)
        Log.d("상태","ASettingFragment")
    }
}