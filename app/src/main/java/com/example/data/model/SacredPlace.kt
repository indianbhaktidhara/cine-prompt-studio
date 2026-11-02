package com.example.data.model

data class SacredPlace(
    val id: String,
    val name: String,
    val hindiName: String,
    val location: String,
    val state: String,
    val category: HeritageCategory,
    val tagline: String,
    val architectureStyle: String,
    val waterBody: String,
    val spiritualAtmosphere: String,
    val visitingHighlights: List<String>,
    val keyPromptKeywords: String
)
