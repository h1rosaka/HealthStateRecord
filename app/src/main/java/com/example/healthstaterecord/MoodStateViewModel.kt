import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.healthstaterecord.MoodDay
import com.example.healthstaterecord.MoodType
import java.time.LocalDate

class MoodStateViewModel : ViewModel() {
    // 日付ごとの気分の状態を保存
    private val _moodStates = mutableStateListOf<MoodDay>()
    val moodStates: List<MoodDay> get() = _moodStates

    // 気分を保存する関数
    fun updateMood(date: LocalDate, mood: MoodType) {
        val existingMood = _moodStates.find { it.date == date }
        if (existingMood != null) {
            _moodStates.remove(existingMood)
        }
        _moodStates.add(MoodDay(date, mood))
    }
}
