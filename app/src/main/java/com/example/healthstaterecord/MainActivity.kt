package com.example.healthstaterecord

import MoodStateViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.healthstaterecord.ui.theme.HealthStateRecordTheme
import java.time.LocalDate
import androidx.activity.viewModels
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.healthstaterecord.MoodStateViewModel


class MainActivity : ComponentActivity() {
    private val moodStateViewModel: MoodStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthStateRecordTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "calendar") {
                    composable("calendar") {
                        CalendarScreen(
                            onDayClick = { date ->
                                navController.navigate("detail/$date")
                            },
                            moodStates = moodStateViewModel.moodStates
                        )
                    }
                    composable("detail/{date}") { backStackEntry ->
                        val date = LocalDate.parse(backStackEntry.arguments?.getString("date"))
                        DetailScreen(
                            date = date,
                            onBack = { navController.popBackStack() },
                            onMoodSelect = { mood ->
                                moodStateViewModel.updateMood(date, mood)
                               // navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}