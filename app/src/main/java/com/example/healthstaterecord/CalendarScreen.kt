package com.example.healthstaterecord

//package com.example.moodcalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import androidx.compose.ui.tooling.preview.Preview
import java.time.YearMonth

// 日付データのモデル
data class MoodDay(
    val date: LocalDate,
    val mood: MoodType = MoodType.NEUTRAL // 初期値は普通の顔
)

// 気分の種類
enum class MoodType(val emoji: String) {
    AWESOME("😃"),
    GOOD("😊"),
    NEUTRAL("😐"),
    BAD("😞"),
    TERRIBLE("😢")
}

@Composable
fun CalendarScreen(onDayClick: (LocalDate) -> Unit, moodStates: List<MoodDay>) {
    val currentMonth = YearMonth.now()
    val daysInMonth = currentMonth.lengthOfMonth()

    val days = remember {
        (1..daysInMonth).map { day ->
            MoodDay(LocalDate.of(currentMonth.year, currentMonth.month, day))
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${currentMonth.month.value}月",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(7), modifier = Modifier.fillMaxSize()) {
            items(days.size) { index ->
                val day = days[index]
                // MoodDayItem に気分情報を追加
                val mood = moodStates.find { it.date == day.date }?.mood ?: MoodType.NEUTRAL
                MoodDayItem(day.copy(mood = mood), onDayClick)
            }
        }
    }
}

// 各日のアイテム
@Composable
fun MoodDayItem(day: MoodDay, onDayClick: (LocalDate) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onDayClick(day.date) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = day.date.dayOfMonth.toString(), fontSize = 16.sp)
        Text(text = day.mood.emoji, fontSize = 20.sp)
    }
}

// プレビュー用
//@Preview(showBackground = true)
//@Composable
//fun CalendarScreenPreview() {
//    CalendarScreen(onDayClick = {})
//}
