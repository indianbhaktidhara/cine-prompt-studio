package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.TempleHindu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.AiPromptGeneratorScreen
import com.example.ui.components.ExplorerScreen
import com.example.ui.components.FavoritesScreen
import com.example.ui.components.StudioGeneratorScreen
import com.example.ui.theme.AnamorphicCyan
import com.example.ui.theme.CinemaBlack
import com.example.ui.theme.CinemaBorder
import com.example.ui.theme.CinemaDarkBg
import com.example.ui.theme.CinemaSurface
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhitePrimary
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(viewModel: MainViewModel) {
    val activeTab by viewModel.activeTab.collectAsStateWithLifecycle()
    val places by viewModel.filteredPlaces.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val favoritePlaceIds by viewModel.favoritePlaceIds.collectAsStateWithLifecycle()
    val savedFavorites by viewModel.savedFavorites.collectAsStateWithLifecycle()
    val currentBundle by viewModel.currentPromptBundle.collectAsStateWithLifecycle()
    val userFeedback by viewModel.userFeedback.collectAsStateWithLifecycle()

    val aiSiteName by viewModel.aiSiteName.collectAsStateWithLifecycle()
    val aiCameraAngle by viewModel.aiCameraAngle.collectAsStateWithLifecycle()
    val aiLensType by viewModel.aiLensType.collectAsStateWithLifecycle()
    val aiGenerationState by viewModel.aiGenerationState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    // Handle feedback snackbars
    LaunchedEffect(userFeedback) {
        userFeedback?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearFeedback()
        }
    }

    // Predictive back navigation handling
    BackHandler(enabled = activeTab != AppTab.EXPLORER) {
        viewModel.setTab(AppTab.EXPLORER)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CinemaBlack,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        listOf(GoldPrimary, AnamorphicCyan)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "CinePrompt Studio",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhitePrimary
                            )
                            Text(
                                text = "OTT 8K Image Prompts • Nolan & NatGeo Standards",
                                fontSize = 10.sp,
                                color = GoldPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                },
                actions = {
                    Surface(
                        color = CinemaSurface,
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CinemaBorder),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF22C55E))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "v6.1 Ready",
                                fontSize = 10.sp,
                                color = TextMuted,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CinemaDarkBg,
                    titleContentColor = TextWhitePrimary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = CinemaDarkBg,
                contentColor = TextMuted,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .border(1.dp, CinemaBorder)
                    .testTag("bottom_nav_bar")
            ) {
                // Tab 1: Sacred Sites Explorer
                NavigationBarItem(
                    selected = activeTab == AppTab.EXPLORER,
                    onClick = { viewModel.setTab(AppTab.EXPLORER) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == AppTab.EXPLORER) Icons.Filled.TempleHindu else Icons.Outlined.TempleHindu,
                            contentDescription = "Sacred Sites",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Sacred Sites", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GoldPrimary,
                        selectedTextColor = GoldPrimary,
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted,
                        indicatorColor = GoldPrimary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_explorer")
                )

                // Tab 2: OTT Studio Generator
                NavigationBarItem(
                    selected = activeTab == AppTab.GENERATOR,
                    onClick = { viewModel.setTab(AppTab.GENERATOR) },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == AppTab.GENERATOR) Icons.Filled.Movie else Icons.Outlined.Movie,
                            contentDescription = "OTT Studio",
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("OTT Studio", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AnamorphicCyan,
                        selectedTextColor = AnamorphicCyan,
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted,
                        indicatorColor = AnamorphicCyan.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_generator")
                )

                // Tab 3: Gemini AI Prompt Creator
                NavigationBarItem(
                    selected = activeTab == AppTab.AI_GENERATOR,
                    onClick = { viewModel.setTab(AppTab.AI_GENERATOR) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = "Gemini AI",
                            tint = if (activeTab == AppTab.AI_GENERATOR) NebulaTeal else TextMuted,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = { Text("Gemini AI", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = NebulaTeal,
                        selectedTextColor = NebulaTeal,
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted,
                        indicatorColor = NebulaTeal.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_ai")
                )

                // Tab 4: Persistent Favorites (with Heart Icon & Count Badge)
                NavigationBarItem(
                    selected = activeTab == AppTab.FAVORITES,
                    onClick = { viewModel.setTab(AppTab.FAVORITES) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (savedFavorites.isNotEmpty()) {
                                    Badge(
                                        containerColor = HeartRed,
                                        contentColor = Color.White
                                    ) {
                                        Text("${savedFavorites.size}")
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (activeTab == AppTab.FAVORITES) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorites",
                                tint = if (activeTab == AppTab.FAVORITES) HeartRed else TextMuted,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("Favorites", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = HeartRed,
                        selectedTextColor = HeartRed,
                        unselectedIconColor = TextMuted,
                        unselectedTextColor = TextMuted,
                        indicatorColor = HeartRed.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("nav_tab_favorites")
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    containerColor = CinemaSurface,
                    contentColor = TextWhitePrimary,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(12.dp)
                        .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = data.visuals.message,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (activeTab) {
                AppTab.EXPLORER -> {
                    ExplorerScreen(
                        places = places,
                        selectedCategory = selectedCategory,
                        searchQuery = searchQuery,
                        favoritePlaceIds = favoritePlaceIds,
                        onSelectCategory = { viewModel.setCategory(it) },
                        onSearchQueryChange = { viewModel.setSearchQuery(it) },
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        onSelectPlace = { viewModel.selectPlace(it, navigateToStudio = true) }
                    )
                }

                AppTab.GENERATOR -> {
                    val isFav = favoritePlaceIds.contains(currentBundle.place.id)
                    StudioGeneratorScreen(
                        bundle = currentBundle,
                        isFavorite = isFav,
                        onToggleFavorite = { viewModel.toggleFavorite(currentBundle.place) },
                        onSelectPlace = { viewModel.selectPlace(it, navigateToStudio = false) },
                        onSelectDirector = { viewModel.setDirector(it) },
                        onSelectProduction = { viewModel.setProduction(it) },
                        onSelectLighting = { viewModel.setLighting(it) },
                        onSelectVfx = { viewModel.setVfx(it) },
                        onSelectDimension = { viewModel.setDimension(it) },
                        onFeedback = { viewModel.postFeedback(it) }
                    )
                }

                AppTab.AI_GENERATOR -> {
                    AiPromptGeneratorScreen(
                        siteName = aiSiteName,
                        cameraAngle = aiCameraAngle,
                        lensType = aiLensType,
                        onSiteNameChange = { viewModel.setAiSiteName(it) },
                        onCameraAngleChange = { viewModel.setAiCameraAngle(it) },
                        onLensTypeChange = { viewModel.setAiLensType(it) },
                        generationState = aiGenerationState,
                        onGenerateClick = { viewModel.generateWithGemini() },
                        onSaveFavoritePrompt = { site, prompt -> viewModel.saveAiPromptFavorite(site, prompt) },
                        onFeedback = { viewModel.postFeedback(it) }
                    )
                }

                AppTab.FAVORITES -> {
                    FavoritesScreen(
                        favorites = savedFavorites,
                        onLoadInStudio = { viewModel.loadFavoriteIntoStudio(it) },
                        onDeleteFavorite = { viewModel.deleteFavorite(it) },
                        onExplorePlaces = { viewModel.setTab(AppTab.EXPLORER) },
                        onFeedback = { viewModel.postFeedback(it) }
                    )
                }
            }
        }
    }
}
