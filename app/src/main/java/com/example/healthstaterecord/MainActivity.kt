import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate
import com.example.healthstaterecord.CalendarScreen
import com.example.healthstaterecord.DetailScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "calendar") {
        composable("calendar") {
            CalendarScreen(onDayClick = { date ->
                // LocalDateはStringで渡す
                navController.navigate("detail/${date.toString()}")
            })
        }
        composable("detail/{date}") { backStackEntry ->
            val dateStr = backStackEntry.arguments?.getString("date")
            val date = LocalDate.parse(dateStr)
            DetailScreen(date = date, onBack = { navController.popBackStack() })
        }
    }
}
