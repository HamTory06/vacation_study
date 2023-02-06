import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import com.example.preferences.R

class BSettingFragment: PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_b, rootKey)
        Log.d("상태","BSettingFragment")
    }

}