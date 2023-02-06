import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import com.example.preferences.R

class MySettingFragment: PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        Log.d("상태","onCreatePreferences()")
    }
}