package crystal.physionow

import ReminderPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import crystal.physionow.ui.theme.PhysionowTheme
import crystal.physionow.ui.theme.devpages.DevSettingsPage
import crystal.physionow.ui.theme.pages.QRCodeScannerPage
import crystal.physionow.ui.theme.pages.SettingsPage
import crystal.physionow.ui.theme.pages.ImpressumPage
import crystal.physionow.ui.theme.pages.ChatWithGeminiPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhysionowTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavigationHost(navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            Greeting(name = "Android", navController = navController)
        }
        composable("scanner") {
            QRCodeScannerPage()
        }
        composable("settings") {
            SettingsPage(navController)
        }
        composable("impressum") {
            ImpressumPage()
        }
        composable("searchExercises") {
            ChatWithGeminiPage()
        }
        composable("devsettings") {
            DevSettingsPage()
        }
        composable("reminder") {
            ReminderPage()
        }
    }
}


@Composable
fun Greeting(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.0f),
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.0f)
                )
            ))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.physionowlogowithimprint),
                contentDescription = "PhysioNow Logo",
                modifier = Modifier
                    .size(280.dp)
                    .padding(bottom = 40.dp)
                    .graphicsLayer {
                        alpha = 0.9f
                        translationY = 20f
                    }
            )


            Text(
                text = "Willkommen zurück!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    letterSpacing = 1.5.sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = {
                    navController.navigate("searchExercises")
                },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .height(55.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Suche nach Übungen", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAccountClick: () -> Unit) {
    TopAppBar(
        title = { Text("PhysioNow") },
        navigationIcon = {
            IconButton(onClick = { onAccountClick() }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Account",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        },
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Scanner") },
            label = { Text("Scanner") },
            selected = false,
            onClick = { navController.navigate("scanner") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = { navController.navigate("settings") }
        )
    }
}
