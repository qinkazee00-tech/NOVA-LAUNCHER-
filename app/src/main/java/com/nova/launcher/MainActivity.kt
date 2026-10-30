
package com.nova.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF090B12)
private val Panel = Color(0xFF151927)
private val Purple = Color(0xFF8B5CF6)
private val Blue = Color(0xFF38BDF8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Purple,
                    secondary = Blue,
                    background = Background,
                    surface = Panel
                )
            ) {
                NovaApp()
            }
        }
    }
}

@Composable
private fun NovaApp() {
    var selectedTab by remember { mutableStateOf("Home") }
    var showInfo by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            NavigationBar(containerColor = Panel) {
                listOf("Home", "Versions", "Profile", "Settings").forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            Text(
                                when (tab) {
                                    "Home" -> "⌂"
                                    "Versions" -> "▤"
                                    "Profile" -> "◉"
                                    else -> "⚙"
                                },
                                fontSize = 20.sp
                            )
                        },
                        label = { Text(tab) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(padding)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "NOVA",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        "LAUNCHER  •  v0.1",
                        fontSize = 12.sp,
                        color = Blue
                    )
                }
                Text("◈", fontSize = 32.sp, color = Purple)
            }

            Spacer(Modifier.height(28.dp))

            when (selectedTab) {
                "Home" -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF30205D),
                                        Color(0xFF132A4A),
                                        Panel
                                    )
                                ),
                                RoundedCornerShape(24.dp)
                            )
                            .padding(20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(
                                "YOUR WORLD.",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                "YOUR ADVENTURE.",
                                fontSize = 18.sp,
                                color = Blue
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Minecraft Java Launcher",
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(22.dp))

                    Text(
                        "QUICK PLAY",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = { showInfo = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("▶   PLAY MINECRAFT", fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(20.dp))

                    Text("LAUNCHER CENTER", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        FeatureCard(
                            title = "Version Center",
                            subtitle = "Manage versions",
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = "Versions" }
                        )
                        FeatureCard(
                            title = "My Profile",
                            subtitle = "Player settings",
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = "Profile" }
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    FeatureCard(
                        title = "Launcher Settings",
                        subtitle = "Appearance and options",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { selectedTab = "Settings" }
                    )
                }

                "Versions" -> {
                    PageTitle("VERSION CENTER", "Minecraft versions")
                    FeatureCard(
                        "No versions installed",
                        "Version downloads will be added in a future update.",
                        Modifier.fillMaxWidth(),
                        onClick = { showInfo = true }
                    )
                }

                "Profile" -> {
                    PageTitle("PLAYER PROFILE", "Your launcher profile")
                    FeatureCard(
                        "Offline profile",
                        "Profile and skin management are coming later.",
                        Modifier.fillMaxWidth(),
                        onClick = { showInfo = true }
                    )
                }

                else -> {
                    PageTitle("SETTINGS", "NOVA Launcher")
                    FeatureCard(
                        "Launcher appearance",
                        "Dark theme with purple and blue accents.",
                        Modifier.fillMaxWidth(),
                        onClick = { showInfo = true }
                    )
                    Spacer(Modifier.height(12.dp))
                    FeatureCard(
                        "Runtime and renderer",
                        "Configuration options will be added later.",
                        Modifier.fillMaxWidth(),
                        onClick = { showInfo = true }
                    )
                }
            }
        }
    }

    if (showInfo) {
        AlertDialog(
            onDismissRequest = { showInfo = false },
            title = { Text("NOVA LAUNCHER v0.1") },
            text = {
                Text(
                    "Tampilan awal sudah disiapkan. " +
                    "Engine Minecraft Java belum terintegrasi, " +
                    "jadi game belum bisa dijalankan dari tombol ini."
                )
            },
            confirmButton = {
                TextButton(onClick = { showInfo = false }) {
                    Text("OK")
                }
            },
            containerColor = Panel,
            titleContentColor = Color.White,
            textContentColor = Color.LightGray
        )
    }
}

@Composable
private fun PageTitle(title: String, subtitle: String) {
    Text(title, color = Blue, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(6.dp))
    Text(title.replace("CENTER", ""), color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(4.dp))
    Text(subtitle, color = Color.LightGray, fontSize = 14.sp)
    Spacer(Modifier.height(20.dp))
}

@Composable
private fun FeatureCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(Modifier.height(5.dp))
            Text(subtitle, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}
