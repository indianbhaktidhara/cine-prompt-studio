package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DirectorStyle
import com.example.data.model.LightingMood
import com.example.data.model.ProductionStandard
import com.example.data.model.SacredPlace
import com.example.data.model.SocialDimension
import com.example.data.model.VfxLayer
import com.example.data.repository.SacredPlacesRepository
import com.example.generator.GeneratedPromptBundle
import com.example.ui.theme.AnamorphicCyan
import com.example.ui.theme.CinemaBorder
import com.example.ui.theme.CinemaDarkBg
import com.example.ui.theme.CinemaSurface
import com.example.ui.theme.CinemaSurfaceVariant
import com.example.ui.theme.CodeBg
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.NebulaTeal
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhitePrimary

enum class StudioSubTab(val label: String, val icon: ImageVector) {
    CONTROLS("Specs & Styles", Icons.Filled.Tune),
    CODE_BLOCK("Code Block", Icons.Filled.Code),
    ANGLES("4 Camera Angles", Icons.Filled.Videocam)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioGeneratorScreen(
    bundle: GeneratedPromptBundle,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSelectPlace: (SacredPlace) -> Unit,
    onSelectDirector: (DirectorStyle) -> Unit,
    onSelectProduction: (ProductionStandard) -> Unit,
    onSelectLighting: (LightingMood) -> Unit,
    onSelectVfx: (VfxLayer) -> Unit,
    onSelectDimension: (SocialDimension) -> Unit,
    onFeedback: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboard = remember {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    var currentSubTab by remember { mutableStateOf(StudioSubTab.CONTROLS) }
    var placeDropdownExpanded by remember { mutableStateOf(false) }
    var isCopiedFloating by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Mobile Sub-Tab Navigation Header
            TabRow(
                selectedTabIndex = currentSubTab.ordinal,
                containerColor = CinemaDarkBg,
                contentColor = GoldPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[currentSubTab.ordinal]),
                        color = GoldPrimary,
                        height = 3.dp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, CinemaBorder)
            ) {
                StudioSubTab.values().forEach { tab ->
                    Tab(
                        selected = currentSubTab == tab,
                        onClick = { currentSubTab = tab },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (currentSubTab == tab) GoldPrimary else TextMuted
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = tab.label,
                                    fontSize = 11.sp,
                                    fontWeight = if (currentSubTab == tab) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        },
                        selectedContentColor = GoldPrimary,
                        unselectedContentColor = TextMuted,
                        modifier = Modifier.testTag("studio_tab_${tab.name}")
                    )
                }
            }

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Shared Hero Header: Selected Place & Quick Dropdown
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
                            .testTag("studio_hero_banner"),
                        colors = CardDefaults.cardColors(containerColor = CinemaSurface)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = GoldPrimary.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Place,
                                            contentDescription = null,
                                            tint = GoldPrimary,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = bundle.place.category.title.uppercase(),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = GoldPrimary
                                        )
                                    }
                                }

                                // Mobile Heart Favorite Button (48dp touch target)
                                IconButton(
                                    onClick = onToggleFavorite,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isFavorite) HeartRed.copy(alpha = 0.15f) else CinemaSurfaceVariant
                                        )
                                        .testTag("studio_favorite_button")
                                ) {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = if (isFavorite) "Remove from favorites" else "Save prompt to favorites",
                                        tint = if (isFavorite) HeartRed else TextMuted,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = bundle.place.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhitePrimary
                            )

                            Text(
                                text = "${bundle.place.location}, ${bundle.place.state} • ${bundle.place.hindiName}",
                                fontSize = 12.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Switch Sacred Place Dropdown
                            ExposedDropdownMenuBox(
                                expanded = placeDropdownExpanded,
                                onExpandedChange = { placeDropdownExpanded = it },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = bundle.place.name,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Switch Sacred Site / Temple / Gurudwara", fontSize = 11.sp) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = placeDropdownExpanded) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                        .testTag("switch_place_dropdown"),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = CinemaDarkBg,
                                        unfocusedContainerColor = CinemaDarkBg,
                                        focusedIndicatorColor = GoldPrimary,
                                        unfocusedIndicatorColor = CinemaBorder,
                                        focusedTextColor = TextWhitePrimary,
                                        unfocusedTextColor = TextWhitePrimary,
                                        focusedLabelColor = GoldPrimary,
                                        unfocusedLabelColor = TextMuted
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                ExposedDropdownMenu(
                                    expanded = placeDropdownExpanded,
                                    onDismissRequest = { placeDropdownExpanded = false },
                                    modifier = Modifier
                                        .background(CinemaSurface)
                                        .border(1.dp, CinemaBorder)
                                ) {
                                    SacredPlacesRepository.places.forEach { place ->
                                        DropdownMenuItem(
                                            text = {
                                                Column {
                                                    Text(
                                                        text = place.name,
                                                        fontSize = 13.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = TextWhitePrimary
                                                    )
                                                    Text(
                                                        text = "${place.location} • ${place.category.title}",
                                                        fontSize = 11.sp,
                                                        color = TextMuted
                                                    )
                                                }
                                            },
                                            onClick = {
                                                onSelectPlace(place)
                                                placeDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // TAB 1: Specs, Directors & Dimensions
                if (currentSubTab == StudioSubTab.CONTROLS) {
                    // Social Dimensions Section
                    item {
                        MobileSectionHeader(
                            icon = Icons.Filled.AspectRatio,
                            title = "SOCIAL PLATFORM DIMENSIONS",
                            subtitle = "Midjourney, SDXL & Flux Aspect Ratio (--ar)"
                        )
                        val dimScroll = rememberScrollState()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(dimScroll),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SocialDimension.values().forEach { dim ->
                                val isSelected = bundle.dimension == dim
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectDimension(dim) },
                                    label = {
                                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                            Text(
                                                text = dim.label,
                                                fontSize = 12.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                            )
                                            Text(
                                                text = dim.platform,
                                                fontSize = 10.sp,
                                                color = if (isSelected) GoldPrimary else TextMuted
                                            )
                                        }
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = CinemaSurface,
                                        labelColor = TextWhitePrimary,
                                        selectedContainerColor = GoldPrimary.copy(alpha = 0.2f),
                                        selectedLabelColor = GoldPrimary
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        borderColor = CinemaBorder,
                                        selectedBorderColor = GoldPrimary,
                                        borderWidth = 1.dp,
                                        selectedBorderWidth = 1.5.dp,
                                        enabled = true,
                                        selected = isSelected
                                    ),
                                    modifier = Modifier.testTag("dim_chip_${dim.ratio.replace(":", "_")}")
                                )
                            }
                        }
                    }

                    // Famous Directors Section
                    item {
                        MobileSectionHeader(
                            icon = Icons.Filled.Movie,
                            title = "FAMOUS DIRECTORS' CINEMATIC STYLES",
                            subtitle = "Nolan, Villeneuve, Ridley Scott, Deakins, Anderson"
                        )
                        val directorScroll = rememberScrollState()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(directorScroll),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            DirectorStyle.values().forEach { director ->
                                val isSelected = bundle.director == director
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectDirector(director) },
                                    label = {
                                        Text(
                                            text = director.displayName,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = CinemaSurface,
                                        labelColor = TextWhitePrimary,
                                        selectedContainerColor = AnamorphicCyan.copy(alpha = 0.2f),
                                        selectedLabelColor = AnamorphicCyan
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        borderColor = CinemaBorder,
                                        selectedBorderColor = AnamorphicCyan,
                                        borderWidth = 1.dp,
                                        selectedBorderWidth = 1.5.dp,
                                        enabled = true,
                                        selected = isSelected
                                    ),
                                    modifier = Modifier.testTag("director_chip_${director.name}")
                                )
                            }
                        }

                        // Director Visual Summary Callout Card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            colors = CardDefaults.cardColors(containerColor = CinemaDarkBg)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "${bundle.director.displayName}: ${bundle.director.aestheticSummary}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AnamorphicCyan
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = bundle.director.visualSignature,
                                    fontSize = 11.sp,
                                    color = TextMuted,
                                    lineHeight = 15.sp
                                )
                            }
                        }
                    }

                    // Industry Production Standards
                    item {
                        MobileSectionHeader(
                            icon = Icons.Filled.CameraAlt,
                            title = "INDUSTRY PRODUCTION STANDARDS",
                            subtitle = "Netflix 4K HDR, Nat Geo Master, BBC Earth, HBO Prestige"
                        )
                        val prodScroll = rememberScrollState()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(prodScroll),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ProductionStandard.values().forEach { prod ->
                                val isSelected = bundle.production == prod
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectProduction(prod) },
                                    label = {
                                        Text(
                                            text = prod.title,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = CinemaSurface,
                                        labelColor = TextWhitePrimary,
                                        selectedContainerColor = NebulaTeal.copy(alpha = 0.2f),
                                        selectedLabelColor = NebulaTeal
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        borderColor = CinemaBorder,
                                        selectedBorderColor = NebulaTeal,
                                        borderWidth = 1.dp,
                                        selectedBorderWidth = 1.5.dp,
                                        enabled = true,
                                        selected = isSelected
                                    ),
                                    modifier = Modifier.testTag("prod_chip_${prod.name}")
                                )
                            }
                        }
                    }

                    // Lighting & VFX
                    item {
                        MobileSectionHeader(
                            icon = Icons.Filled.WbSunny,
                            title = "DOP LIGHTING & VFX SHADERS",
                            subtitle = "Volumetric God Rays, Twilight Diyas, Cosmic Nebula"
                        )
                        val lightScroll = rememberScrollState()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(lightScroll),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            LightingMood.values().forEach { light ->
                                val isSelected = bundle.lighting == light
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectLighting(light) },
                                    label = {
                                        Text(
                                            text = light.title,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = CinemaSurface,
                                        labelColor = TextWhitePrimary,
                                        selectedContainerColor = GoldPrimary.copy(alpha = 0.2f),
                                        selectedLabelColor = GoldPrimary
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        borderColor = CinemaBorder,
                                        selectedBorderColor = GoldPrimary,
                                        borderWidth = 1.dp,
                                        selectedBorderWidth = 1.5.dp,
                                        enabled = true,
                                        selected = isSelected
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val vfxScroll = rememberScrollState()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(vfxScroll),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            VfxLayer.values().forEach { vfx ->
                                val isSelected = bundle.vfx == vfx
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectVfx(vfx) },
                                    label = {
                                        Text(
                                            text = vfx.title,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = CinemaSurface,
                                        labelColor = TextWhitePrimary,
                                        selectedContainerColor = NebulaTeal.copy(alpha = 0.2f),
                                        selectedLabelColor = NebulaTeal
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        borderColor = CinemaBorder,
                                        selectedBorderColor = NebulaTeal,
                                        borderWidth = 1.dp,
                                        selectedBorderWidth = 1.5.dp,
                                        enabled = true,
                                        selected = isSelected
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Button to view code block tab directly
                        Button(
                            onClick = { currentSubTab = StudioSubTab.CODE_BLOCK },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Code, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("View Generated Code Block →", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }

                // TAB 2: Full Code Block Viewer
                if (currentSubTab == StudioSubTab.CODE_BLOCK) {
                    item {
                        PromptCodeBlockView(
                            bundle = bundle,
                            onFeedback = onFeedback
                        )
                    }
                }

                // TAB 3: 4 Camera Angles Breakdown
                if (currentSubTab == StudioSubTab.ANGLES) {
                    item {
                        MobileSectionHeader(
                            icon = Icons.Filled.Videocam,
                            title = "4 MULTI-ANGLE CINEMATIC SHOT LIST",
                            subtitle = "Ready-to-generate camera variations for this site"
                        )
                    }
                    items(bundle.angles.size) { index ->
                        val angle = bundle.angles[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.dp, CinemaBorder, RoundedCornerShape(12.dp)),
                            colors = CardDefaults.cardColors(containerColor = CinemaSurface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = angle.title,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldPrimary
                                    )
                                    IconButton(
                                        onClick = {
                                            val clip = ClipData.newPlainText(angle.title, angle.promptText)
                                            clipboard.setPrimaryClip(clip)
                                            onFeedback("Copied ${angle.title} prompt!")
                                        },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.ContentCopy,
                                            contentDescription = "Copy prompt",
                                            tint = AnamorphicCyan,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Text(
                                    text = "Movement: ${angle.cameraMove}",
                                    fontSize = 11.sp,
                                    color = NebulaTeal,
                                    fontWeight = FontWeight.Medium
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = angle.description,
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(CodeBg)
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = angle.promptText,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 11.sp,
                                        color = Color(0xFFCBD5E1),
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(84.dp))
                }
            }
        }

        // Floating Mobile Quick Copy / Action Bar at bottom
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            shape = RoundedCornerShape(14.dp),
            color = CinemaSurface.copy(alpha = 0.95f),
            border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f)),
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // One-tap Copy Code Block
                Button(
                    onClick = {
                        val clip = ClipData.newPlainText("CinePrompt Code Block", bundle.fullCodeBlock)
                        clipboard.setPrimaryClip(clip)
                        isCopiedFloating = true
                        onFeedback("Copied complete prompt code block to clipboard!")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("floating_copy_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCopiedFloating) Color(0xFF15803D) else GoldPrimary,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = if (isCopiedFloating) Icons.Filled.Check else Icons.Filled.ContentCopy,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isCopiedFloating) "Copied!" else "Copy Code Block",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                // Heart favorite toggle button
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isFavorite) HeartRed.copy(alpha = 0.2f) else CinemaSurfaceVariant
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Save favorite",
                        tint = if (isFavorite) HeartRed else TextWhitePrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Share button
                IconButton(
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, bundle.fullCodeBlock)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share Prompt"))
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CinemaSurfaceVariant)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share",
                        tint = TextWhitePrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MobileSectionHeader(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = GoldPrimary,
                letterSpacing = 0.8.sp
            )
        }
        Text(
            text = subtitle,
            fontSize = 11.sp,
            color = TextMuted
        )
    }
}
