package com.example.healthstaterecord

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

//
@Composable
fun DetailScreen(date: LocalDate, onBack: () -> Unit, onMoodSelect: (MoodType) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = date.toString(), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "どのような一日でしたか？")
        Row {
            MoodType.values().forEach { mood ->
                Text(
                    text = mood.emoji,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onMoodSelect(mood)
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "メモ")
        OutlinedTextField(value = "", onValueChange = { /* TODO: メモ保存処理 */ }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("戻る")
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DetailScreenPreview() {
//    DetailScreen(date = LocalDate.now(), onBack = {})
//}