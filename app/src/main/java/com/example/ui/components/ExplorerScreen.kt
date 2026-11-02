package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HeritageCategory
import com.example.data.model.SacredPlace
import com.example.ui.theme.AnamorphicCyan
import com.example.ui.theme.CinemaBorder
import com.example.ui.theme.CinemaDarkBg
import com.example.ui.theme.CinemaSurface
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.HeartRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhitePrimary

@Composable
fun ExplorerScreen(
    places: List<SacredPlace>,
    selectedCategory: HeritageCategory,
    searchQuery: String,
    favoritePlaceIds: Set<String>,
    onSelectCategory: (HeritageCategory) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavorite: (SacredPlace) -> Unit,
    onSelectPlace: (SacredPlace) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_input"),
                placeholder = {
                    Text(
                        "Search temples, gurudwaras, cities, states...",
                        fontSize = 13.sp,
                        color = TextMuted
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = GoldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear search",
                                tint = TextMuted,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = CinemaSurface,
                    unfocusedContainerColor = CinemaSurface,
                    focusedIndicatorColor = GoldPrimary,
                    unfocusedIndicatorColor = CinemaBorder,
                    focusedTextColor = TextWhitePrimary,
                    unfocusedTextColor = TextWhitePrimary
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }

        // Category Chips Row
        item {
            val catScroll = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(catScroll),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HeritageCategory.values().forEach { category ->
                    val isSelected = selectedCategory == category
                    FilterChip(
                        selected = isSelected,
                        onClick = { onSelectCategory(category) },
                        label = {
                            Text(
                                text = category.title,
                                fontSize = 12.sp,
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
                        ),
                        modifier = Modifier.testTag("category_chip_${category.name}")
                    )
                }
            }
        }

        // Quick Curated Visiting Hotspots Row
        item {
            val quickPlaces = listOf(
                "Amritsar", "Somnath", "Ayodhya", "Chitrakoot", "Maihar",
                "Janakpur", "Sitamarhi", "Ujjain", "Vrindavan", "Dwarka",
                "Patna Sahib", "Jagannath Puri", "Khajuraho"
            )
            val spotScroll = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(spotScroll),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "VISITING CIRCUITS:",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMuted,
                    letterSpacing = 0.8.sp
                )
                quickPlaces.forEach { spot ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(CinemaDarkBg)
                            .border(1.dp, CinemaBorder, RoundedCornerShape(14.dp))
                            .clickable { onSearchQueryChange(spot) }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = spot,
                            fontSize = 11.sp,
                            color = AnamorphicCyan,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Results Count Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${places.size} HERITAGE DESTINATIONS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    letterSpacing = 0.8.sp
                )

                if (favoritePlaceIds.isNotEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = HeartRed.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "♥ ${favoritePlaceIds.size} Favorited",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = HeartRed,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }

        // Place Cards List with Heart Favorites
        items(places, key = { it.id }) { place ->
            val isFav = favoritePlaceIds.contains(place.id)
            PlaceCard(
                place = place,
                isFavorite = isFav,
                onFavoriteClick = { onToggleFavorite(place) },
                onClick = { onSelectPlace(place) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
