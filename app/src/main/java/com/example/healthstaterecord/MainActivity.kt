package com.example.healthstaterecord

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthStateRecordTheme {
                // Set up the NavController for navigation
                val navController = rememberNavController()

                // Define the NavHost with routes for the screens
                NavHost(navController, startDestination = "calendar") {
                    composable("calendar") {
                        // Calendar screen where days are displayed
                        CalendarScreen(
                            onDayClick = { date ->
                                // Navigate to DetailScreen with the selected date
                                navController.navigate("detail/$date")
                            }
                        )
                    }
                    composable("detail/{date}") { backStackEntry ->
                        val date = LocalDate.parse(backStackEntry.arguments?.getString("date"))
                        // Pass the selected date to the DetailScreen
                        DetailScreen(date = date, onBack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
