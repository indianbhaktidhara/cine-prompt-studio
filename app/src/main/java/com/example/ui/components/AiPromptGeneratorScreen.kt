package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

sealed interface AiGenerationState {
    data object Idle : AiGenerationState
    data object Loading : AiGenerationState
    data class Success(val generatedPrompt: String) : AiGenerationState
    data class Error(val message: String) : AiGenerationState
}

@Composable
fun AiPromptGeneratorScreen(
    siteName: String,
    cameraAngle: String,
    lensType: String,
    onSiteNameChange: (String) -> Unit,
    onCameraAngleChange: (String) -> Unit,
    onLensTypeChange: (String) -> Unit,
    generationState: AiGenerationState,
    onGenerateClick: () -> Unit,
    onSaveFavoritePrompt: (siteName: String, promptText: String) -> Unit,
    onFeedback: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboard = remember {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    var copied by remember { mutableStateOf(false) }
    var favorited by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))

            // Hero Header
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        Brush.horizontalGradient(listOf(GoldPrimary, AnamorphicCyan)),
                        RoundedCornerShape(16.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = CinemaSurface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = GoldPrimary.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AutoAwesome,
                                    contentDescription = null,
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "GEMINI AI POWERED",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary,
                                    letterSpacing = 0.8.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "AI Image Prompt Creator",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhitePrimary
                    )

                    Text(
                        text = "Specify your heritage site, custom camera angle, and cinema lens. Gemini AI synthesizes full positive/negative prompts, DOP specs, and multi-angle variations.",
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 17.sp
                    )
                }
            }
        }

        // Field 1: Site Name
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "SITE NAME",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = siteName,
                    onValueChange = onSiteNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_site_name"),
                    placeholder = {
                        Text("e.g. Golden Temple Amritsar, Kedarnath, Ram Mandir Ayodhya", fontSize = 13.sp, color = TextMuted)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Place,
                            contentDescription = "Site",
                            tint = GoldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = CinemaDarkBg,
                        unfocusedContainerColor = CinemaDarkBg,
                        focusedIndicatorColor = GoldPrimary,
                        unfocusedIndicatorColor = CinemaBorder,
                        focusedTextColor = TextWhitePrimary,
                        unfocusedTextColor = TextWhitePrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Quick Site Presets
                val sitePresets = listOf(
                    "Sri Harmandir Sahib (Golden Temple), Amritsar",
                    "Shri Ram Janmabhoomi Mandir, Ayodhya",
                    "Somnath Jyotirlinga, Gujarat",
                    "Kedarnath Jyotirlinga, Himalayas",
                    "Mahakaleshwar, Ujjain",
                    "Chitrakoot Dham Ramghat",
                    "Maihar Maa Sharda Devi Mandir",
                    "Janakpur Dham Janaki Mandir",
                    "Prem Mandir, Vrindavan",
                    "Jagannath Puri Temple"
                )
                val siteScroll = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .horizontalScroll(siteScroll),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    sitePresets.forEach { preset ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CinemaSurfaceVariant)
                                .clickable { onSiteNameChange(preset) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = preset.split(",").first(),
                                fontSize = 11.sp,
                                color = AnamorphicCyan,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Field 2: Camera Angle
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "CAMERA ANGLE & PERSPECTIVE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AnamorphicCyan,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = cameraAngle,
                    onValueChange = onCameraAngleChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_camera_angle"),
                    placeholder = {
                        Text("e.g. Low-angle water reflection, IMAX aerial drone reveal", fontSize = 13.sp, color = TextMuted)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Camera,
                            contentDescription = "Angle",
                            tint = AnamorphicCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = CinemaDarkBg,
                        unfocusedContainerColor = CinemaDarkBg,
                        focusedIndicatorColor = AnamorphicCyan,
                        unfocusedIndicatorColor = CinemaBorder,
                        focusedTextColor = TextWhitePrimary,
                        unfocusedTextColor = TextWhitePrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Quick Angle Presets
                val anglePresets = listOf(
                    "Low-angle holy water reflection with ripples",
                    "IMAX 70mm aerial drone sweeping overview",
                    "Eye-level volumetric god rays piercing incense smoke",
                    "Extreme macro telephoto gold filigree & cosmic nebula",
                    "Slow cinematic dolly track through marble courtyard",
                    "Dutch angle dramatic sunset silhouette"
                )
                val angleScroll = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .horizontalScroll(angleScroll),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    anglePresets.forEach { preset ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CinemaSurfaceVariant)
                                .clickable { onCameraAngleChange(preset) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = preset,
                                fontSize = 11.sp,
                                color = NebulaTeal,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Field 3: Lens Type
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "LENS TYPE & OPTICS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = NebulaTeal,
                    letterSpacing = 0.8.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = lensType,
                    onValueChange = onLensTypeChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_lens_type"),
                    placeholder = {
                        Text("e.g. Panavision Ultra Vista 1.65x Anamorphic, 50mm T1.5 Cine Prime", fontSize = 13.sp, color = TextMuted)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "Lens",
                            tint = NebulaTeal,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = CinemaDarkBg,
                        unfocusedContainerColor = CinemaDarkBg,
                        focusedIndicatorColor = NebulaTeal,
                        unfocusedIndicatorColor = CinemaBorder,
                        focusedTextColor = TextWhitePrimary,
                        unfocusedTextColor = TextWhitePrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Quick Lens Presets
                val lensPresets = listOf(
                    "Panavision Ultra Vista 1.65x Anamorphic",
                    "Cooke Anamorphic /i 50mm T2.3 Full Frame",
                    "Leica Summilux-C 35mm T1.4 Prime",
                    "Hasselblad H6D 80mm f/2.2 Medium Format",
                    "Canon Cine-Servo 50-1000mm Telephoto",
                    "Arri Master Anamorphic 28mm T1.9"
                )
                val lensScroll = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .horizontalScroll(lensScroll),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    lensPresets.forEach { preset ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CinemaSurfaceVariant)
                                .clickable { onLensTypeChange(preset) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = preset,
                                fontSize = 11.sp,
                                color = GoldPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // 'Generate' Button
        item {
            val isLoading = generationState is AiGenerationState.Loading
            Button(
                onClick = onGenerateClick,
                enabled = !isLoading && siteName.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("generate_ai_prompt_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color.Black,
                    disabledContainerColor = CinemaSurfaceVariant,
                    disabledContentColor = TextMuted
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.Black,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Triggering Gemini AI API...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Generate Prompt with Gemini AI",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Result Code Block View (when Generated)
        item {
            when (generationState) {
                is AiGenerationState.Loading -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .border(1.dp, CinemaBorder, RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = CodeBg)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = GoldPrimary,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Gemini AI is crafting your OTT cinematic prompt...",
                                fontSize = 13.sp,
                                color = TextMuted
                            )
                        }
                    }
                }

                is AiGenerationState.Success -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = CodeBg)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            // Header bar
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF0F1522))
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "gemini_prompt.md",
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }

                                Surface(
                                    color = NebulaTeal.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "Gemini 2.5 Flash",
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 10.sp,
                                        color = NebulaTeal,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            // Prompt text
                            val hScrollState = rememberScrollState()
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                                    .horizontalScroll(hScrollState)
                            ) {
                                Text(
                                    text = generationState.generatedPrompt,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 12.sp,
                                    color = Color(0xFFE2E8F0),
                                    lineHeight = 18.sp
                                )
                            }

                            // Actions
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF0F1522))
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = {
                                        val clip = ClipData.newPlainText("Gemini Prompt", generationState.generatedPrompt)
                                        clipboard.setPrimaryClip(clip)
                                        copied = true
                                        onFeedback("Copied Gemini prompt to clipboard!")
                                    },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (copied) Color(0xFF15803D) else GoldPrimary,
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Icon(
                                        imageVector = if (copied) Icons.Filled.Check else Icons.Filled.ContentCopy,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (copied) "Copied!" else "Copy Code Block",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // Save Favorite Heart Button
                                IconButton(
                                    onClick = {
                                        onSaveFavoritePrompt(siteName, generationState.generatedPrompt)
                                        favorited = true
                                        onFeedback("Saved Gemini AI prompt to Favorites!")
                                    },
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(if (favorited) HeartRed.copy(alpha = 0.2f) else CinemaSurfaceVariant)
                                        .testTag("save_gemini_favorite_button")
                                ) {
                                    Icon(
                                        imageVector = if (favorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Save Favorite",
                                        tint = if (favorited) HeartRed else TextMuted,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                // Share
                                OutlinedButton(
                                    onClick = {
                                        val sendIntent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, generationState.generatedPrompt)
                                            type = "text/plain"
                                        }
                                        context.startActivity(Intent.createChooser(sendIntent, "Share Prompt"))
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextWhitePrimary)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "Share",
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                is AiGenerationState.Error -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, HeartRed.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = CinemaSurface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "Generation Notice",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = HeartRed
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = generationState.message,
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                        }
                    }
                }

                AiGenerationState.Idle -> {
                    // Placeholder card prompting user
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, CinemaBorder, RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = CinemaSurface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Enter site name, camera angle, and lens type above, then tap 'Generate' to create your image prompt.",
                                fontSize = 12.sp,
                                color = TextMuted,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
