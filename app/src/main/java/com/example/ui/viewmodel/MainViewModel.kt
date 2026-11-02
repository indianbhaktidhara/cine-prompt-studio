package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.CinePromptDatabase
import com.example.data.db.FavoritePromptEntity
import com.example.data.model.DirectorStyle
import com.example.data.model.HeritageCategory
import com.example.data.model.LightingMood
import com.example.data.model.ProductionStandard
import com.example.data.model.SacredPlace
import com.example.data.model.SocialDimension
import com.example.data.model.VfxLayer
import com.example.data.repository.FavoriteRepository
import com.example.data.repository.SacredPlacesRepository
import com.example.generator.GeneratedPromptBundle
import com.example.generator.PromptGeneratorEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import com.example.data.api.GeminiPromptService
import com.example.ui.components.AiGenerationState

enum class AppTab(val label: String) {
    EXPLORER("Sacred Sites"),
    GENERATOR("OTT Studio"),
    AI_GENERATOR("Gemini AI"),
    FAVORITES("My Favorites")
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository: FavoriteRepository

    init {
        val database = CinePromptDatabase.getDatabase(application)
        favoriteRepository = FavoriteRepository(database.favoritePromptDao())
    }

    // Reactive favorites from Room
    val savedFavorites: StateFlow<List<FavoritePromptEntity>> = favoriteRepository.allFavorites
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val favoritePlaceIds: StateFlow<Set<String>> = favoriteRepository.favoritePlaceIds
        .map { it.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    // Navigation Tab
    private val _activeTab = MutableStateFlow(AppTab.EXPLORER)
    val activeTab: StateFlow<AppTab> = _activeTab.asStateFlow()

    // Explorer State
    private val _selectedCategory = MutableStateFlow(HeritageCategory.ALL)
    val selectedCategory: StateFlow<HeritageCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val filteredPlaces: StateFlow<List<SacredPlace>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        val listByCategory = SacredPlacesRepository.getPlacesByCategory(category)
        if (query.isBlank()) {
            listByCategory
        } else {
            val q = query.trim().lowercase()
            listByCategory.filter {
                it.name.lowercase().contains(q) ||
                    it.hindiName.contains(q) ||
                    it.location.lowercase().contains(q) ||
                    it.state.lowercase().contains(q) ||
                    it.architectureStyle.lowercase().contains(q) ||
                    it.tagline.lowercase().contains(q) ||
                    it.keyPromptKeywords.lowercase().contains(q)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SacredPlacesRepository.places
    )

    // Studio Generator Controls
    private val _selectedPlace = MutableStateFlow(SacredPlacesRepository.places.first())
    val selectedPlace: StateFlow<SacredPlace> = _selectedPlace.asStateFlow()

    private val _selectedDirector = MutableStateFlow(DirectorStyle.NOLAN)
    val selectedDirector: StateFlow<DirectorStyle> = _selectedDirector.asStateFlow()

    private val _selectedProduction = MutableStateFlow(ProductionStandard.NETFLIX_ORIGINAL)
    val selectedProduction: StateFlow<ProductionStandard> = _selectedProduction.asStateFlow()

    private val _selectedLighting = MutableStateFlow(LightingMood.VOLUMETRIC_GOD_RAYS)
    val selectedLighting: StateFlow<LightingMood> = _selectedLighting.asStateFlow()

    private val _selectedVfx = MutableStateFlow(VfxLayer.COSMIC_NEBULA)
    val selectedVfx: StateFlow<VfxLayer> = _selectedVfx.asStateFlow()

    private val _selectedDimension = MutableStateFlow(SocialDimension.LANDSCAPE_16_9)
    val selectedDimension: StateFlow<SocialDimension> = _selectedDimension.asStateFlow()

    // Computed Prompt Bundle
    val currentPromptBundle: StateFlow<GeneratedPromptBundle> = combine(
        combine(_selectedPlace, _selectedDirector, _selectedProduction) { place, dir, prod ->
            Triple(place, dir, prod)
        },
        combine(_selectedLighting, _selectedVfx, _selectedDimension) { light, vfx, dim ->
            Triple(light, vfx, dim)
        }
    ) { (place, director, production), (lighting, vfx, dimension) ->
        PromptGeneratorEngine.generate(
            place = place,
            director = director,
            production = production,
            lighting = lighting,
            vfx = vfx,
            dimension = dimension
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PromptGeneratorEngine.generate(
            place = SacredPlacesRepository.places.first(),
            director = DirectorStyle.NOLAN,
            production = ProductionStandard.NETFLIX_ORIGINAL,
            lighting = LightingMood.VOLUMETRIC_GOD_RAYS,
            vfx = VfxLayer.COSMIC_NEBULA,
            dimension = SocialDimension.LANDSCAPE_16_9
        )
    )

    // User feedback message
    private val _userFeedback = MutableStateFlow<String?>(null)
    val userFeedback: StateFlow<String?> = _userFeedback.asStateFlow()

    fun setTab(tab: AppTab) {
        _activeTab.value = tab
    }

    fun setCategory(category: HeritageCategory) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectPlace(place: SacredPlace, navigateToStudio: Boolean = true) {
        _selectedPlace.value = place
        if (navigateToStudio) {
            _activeTab.value = AppTab.GENERATOR
        }
    }

    fun setDirector(director: DirectorStyle) {
        _selectedDirector.value = director
    }

    fun setProduction(production: ProductionStandard) {
        _selectedProduction.value = production
    }

    fun setLighting(lighting: LightingMood) {
        _selectedLighting.value = lighting
    }

    fun setVfx(vfx: VfxLayer) {
        _selectedVfx.value = vfx
    }

    fun setDimension(dimension: SocialDimension) {
        _selectedDimension.value = dimension
    }

    fun clearFeedback() {
        _userFeedback.value = null
    }

    fun postFeedback(message: String) {
        _userFeedback.value = message
    }

    /**
     * Toggles favorite status for a given SacredPlace.
     * When favorited, saves the current or custom generated prompt bundle to Room database.
     */
    fun toggleFavorite(place: SacredPlace) {
        viewModelScope.launch {
            val isFavorited = favoritePlaceIds.value.contains(place.id)
            if (isFavorited) {
                favoriteRepository.removeFavoriteByPlaceId(place.id)
                _userFeedback.value = "Removed ${place.name} from Favorites"
            } else {
                // Generate a custom bundle for this place using current director & dimension settings
                val bundle = PromptGeneratorEngine.generate(
                    place = place,
                    director = _selectedDirector.value,
                    production = _selectedProduction.value,
                    lighting = _selectedLighting.value,
                    vfx = _selectedVfx.value,
                    dimension = _selectedDimension.value
                )
                val entity = FavoritePromptEntity(
                    placeId = place.id,
                    placeName = place.name,
                    location = "${place.location}, ${place.state}",
                    category = place.category.title,
                    directorStyle = _selectedDirector.value.displayName,
                    productionStandard = _selectedProduction.value.title,
                    aspectRatio = _selectedDimension.value.ratio,
                    aspectRatioLabel = _selectedDimension.value.label,
                    generatedCodeBlock = bundle.fullCodeBlock,
                    positivePrompt = bundle.positivePrompt
                )
                favoriteRepository.saveFavorite(entity)
                _userFeedback.value = "Saved ${place.name} prompt to Favorites!"
            }
        }
    }

    fun deleteFavorite(favorite: FavoritePromptEntity) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(favorite)
            _userFeedback.value = "Removed from Favorites"
        }
    }

    fun loadFavoriteIntoStudio(favorite: FavoritePromptEntity) {
        val place = SacredPlacesRepository.getPlaceById(favorite.placeId)
        if (place != null) {
            _selectedPlace.value = place
        }
        val matchingDirector = DirectorStyle.values().firstOrNull { it.displayName == favorite.directorStyle }
        if (matchingDirector != null) {
            _selectedDirector.value = matchingDirector
        }
        val matchingDimension = SocialDimension.values().firstOrNull { it.ratio == favorite.aspectRatio }
        if (matchingDimension != null) {
            _selectedDimension.value = matchingDimension
        }
        _activeTab.value = AppTab.GENERATOR
        _userFeedback.value = "Loaded ${favorite.placeName} into OTT Studio"
    }

    // Gemini AI Prompt Creator State
    private val _aiSiteName = MutableStateFlow("Sri Harmandir Sahib (Golden Temple), Amritsar")
    val aiSiteName: StateFlow<String> = _aiSiteName.asStateFlow()

    private val _aiCameraAngle = MutableStateFlow("Low-angle holy water reflection with ripples")
    val aiCameraAngle: StateFlow<String> = _aiCameraAngle.asStateFlow()

    private val _aiLensType = MutableStateFlow("Panavision Ultra Vista 1.65x Anamorphic")
    val aiLensType: StateFlow<String> = _aiLensType.asStateFlow()

    private val _aiGenerationState = MutableStateFlow<AiGenerationState>(AiGenerationState.Idle)
    val aiGenerationState: StateFlow<AiGenerationState> = _aiGenerationState.asStateFlow()

    fun setAiSiteName(value: String) { _aiSiteName.value = value }
    fun setAiCameraAngle(value: String) { _aiCameraAngle.value = value }
    fun setAiLensType(value: String) { _aiLensType.value = value }

    fun generateWithGemini() {
        val site = _aiSiteName.value.trim()
        val angle = _aiCameraAngle.value.trim()
        val lens = _aiLensType.value.trim()
        if (site.isBlank()) {
            _userFeedback.value = "Please enter a site name."
            return
        }

        viewModelScope.launch {
            _aiGenerationState.value = AiGenerationState.Loading
            val result = GeminiPromptService.generatePrompt(site, angle, lens)
            result.onSuccess { text ->
                _aiGenerationState.value = AiGenerationState.Success(text)
                _userFeedback.value = "Gemini AI generated image prompt successfully!"
            }.onFailure { err ->
                _aiGenerationState.value = AiGenerationState.Error(err.localizedMessage ?: "Failed to generate prompt.")
            }
        }
    }

    fun saveAiPromptFavorite(siteName: String, promptCodeBlock: String) {
        viewModelScope.launch {
            val entity = FavoritePromptEntity(
                placeId = "gemini_ai_${System.currentTimeMillis()}",
                placeName = siteName,
                location = "Gemini AI Custom Generation",
                category = "Gemini AI Prompt",
                directorStyle = "Gemini AI Custom",
                productionStandard = "OTT 8K Cinematic",
                aspectRatio = "16:9",
                aspectRatioLabel = "16:9 Landscape",
                generatedCodeBlock = promptCodeBlock,
                positivePrompt = promptCodeBlock.take(200)
            )
            favoriteRepository.saveFavorite(entity)
            _userFeedback.value = "Saved Gemini prompt to Favorites!"
        }
    }
}
