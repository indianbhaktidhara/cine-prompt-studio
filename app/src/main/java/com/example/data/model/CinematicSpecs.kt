package com.example.data.model

enum class DirectorStyle(
    val displayName: String,
    val directorName: String,
    val aestheticSummary: String,
    val visualSignature: String,
    val colorTone: String
) {
    NOLAN(
        displayName = "Christopher Nolan",
        directorName = "Christopher Nolan",
        aestheticSummary = "IMAX 70mm, practical scale, high contrast & intense realism",
        visualSignature = "Monumental grand architecture, physical atmospheric volumetrics, time-dilated epic scale, sharp deep shadow latitude",
        colorTone = "Steel cyan dusk shadows paired with radiant sacred gold highlights, filmic bleach-bypass contrast"
    ),
    VILLENEUVE(
        displayName = "Denis Villeneuve",
        directorName = "Denis Villeneuve",
        aestheticSummary = "Blade Runner / Dune style monumental minimalism & fog",
        visualSignature = "Monolithic proportions, thick atmospheric dust haze, minimalist geometric framing, transcendental silence",
        colorTone = "Monochromatic golden amber mist, sandstorm glow, deep charcoal blacks, muted ochre and teal"
    ),
    RIDLEY_SCOTT(
        displayName = "Ridley Scott",
        directorName = "Ridley Scott",
        aestheticSummary = "Chiaroscuro, light shafts, cinematic smoke & mythic grandeur",
        visualSignature = "Pronounced god-ray light shafts piercing through incense smoke, historical authenticity, textured patina",
        colorTone = "Deep oil-paint chiaroscuro, burnished bronze, dramatic rim lights on stone carvings"
    ),
    ROGER_DEAKINS(
        displayName = "Roger Deakins (ASC)",
        directorName = "Roger Deakins",
        aestheticSummary = "Master of naturalistic light, golden hour silhouette mastery",
        visualSignature = "Breathtaking silhouette framing against sunset, organic soft diffusion, invisible technical perfection",
        colorTone = "Lush warm tungsten, authentic golden hour glow, seamless gentle gradient rolloff into shadows"
    ),
    WES_ANDERSON(
        displayName = "Wes Anderson",
        directorName = "Wes Anderson",
        aestheticSummary = "Perfect bilateral symmetry, pastel palette & ornate diorama",
        visualSignature = "Strict center-weighted frontal symmetry, miniature architectural precision, storybook planar staging",
        colorTone = "Pastel saffron, dusty rose, mint jade, ochre yellow, vibrant symmetrical color blocking"
    ),
    GUILLERMO_DEL_TORO(
        displayName = "Guillermo del Toro",
        directorName = "Guillermo del Toro",
        aestheticSummary = "Dark baroque fantasy, bioluminescent glow & mystical gold",
        visualSignature = "Gothic intricacy meets sacred temple carvings, otherworldly spiritual presence, rich physical textures",
        colorTone = "Midnight lapis lazuli blues, bioluminescent cyan specks, antique leaf gold highlights"
    )
}

enum class ProductionStandard(
    val title: String,
    val badge: String,
    val cameraSensor: String,
    val lensPackage: String,
    val ecosystemNotes: String
) {
    NETFLIX_ORIGINAL(
        title = "Netflix 4K HDR Original",
        badge = "Netflix 4K",
        cameraSensor = "ARRI Alexa LF 4.5K Large Format Sensor",
        lensPackage = "Panavision Ultra Vista 1.65x Anamorphic Prime Lenses",
        ecosystemNotes = "Mastered in Dolby Vision P3 Color Gamut, HDR1000 peak highlights, rich filmic roll-off"
    ),
    NAT_GEO_MASTER(
        title = "National Geographic Expeditions",
        badge = "Nat Geo",
        cameraSensor = "RED V-Raptor 8K VV Multi-Format Sensor",
        lensPackage = "Leica Summilux-C & Ultra-Telephoto Canon Cine Primes",
        ecosystemNotes = "Hyper-realistic cultural documentary, extreme architectural micro-texture, pure raw daylight fidelity"
    ),
    BBC_EARTH(
        title = "BBC Earth & Planet Earth III",
        badge = "BBC Earth",
        cameraSensor = "IMAX 65mm Drone Rig & Sony Venice 2 8K",
        lensPackage = "Cooke Anamorphic /i Full Frame Plus Cine Lenses",
        ecosystemNotes = "Breathtaking environmental dawn clarity, crystal atmospheric depth, ethereal mist capture"
    ),
    HBO_PRESTIGE(
        title = "HBO Max Prestige Cinema",
        badge = "HBO Prestige",
        cameraSensor = "Panavision Millennium DXL2 8K with Kodak Vision3 500T Grain",
        lensPackage = "Panavision Primo 70 T1.6 Primes",
        ecosystemNotes = "Moody cinematic tone, heavy textured grain, organic halation around burning oil lamps"
    )
}

enum class LightingMood(
    val title: String,
    val promptFragment: String
) {
    VOLUMETRIC_GOD_RAYS(
        title = "Volumetric God Rays",
        promptFragment = "epic piercing volumetric sunbeams breaking through morning mist, god rays illuminated by floating dust particles"
    ),
    TWILIGHT_DIYAS(
        title = "Twilight Blue Hour & Diyas",
        promptFragment = "deep indigo blue hour dusk, hundreds of flickering brass oil lamps and floating diyas casting golden spherical bokeh"
    ),
    GOLDEN_HOUR_DUSK(
        title = "Golden Hour Warm Ember",
        promptFragment = "low-angle golden hour sunset illuminating temple spires, warm ember rim lighting, gentle anamorphic horizontal streak"
    ),
    MYSTIC_DAWN_MIST(
        title = "Sacred Dawn Amrit Vela Mist",
        promptFragment = "ethereal morning mist hovering over sacred water, soft diffused ambient dawn luminescence, tranquil spiritual stillness"
    ),
    NIGHT_STARRY_COSMIC(
        title = "Cosmic Night & Starlight",
        promptFragment = "clear starry night sky with Milky Way galactic core shining overhead, architectural silhouette with gentle moonlit rim"
    )
}

enum class VfxLayer(
    val title: String,
    val promptFragment: String
) {
    COSMIC_NEBULA(
        title = "Cosmic Nebula & Stardust",
        promptFragment = "hyper-detailed swirling celestial nebula in cosmic deep space behind the temple shikhara, stardust embers, transcendental spiritual aura, Unreal Engine 5.4 Lumen global illumination"
    ),
    MICRO_DUST_LUMEN(
        title = "Floating Micro-Dust Motes",
        promptFragment = "macro suspended golden motes of dust dancing in light beams, octane render photorealistic ray-tracing, subsurface scattering"
    ),
    INCENSE_SMOKE_RIBBONS(
        title = "Sacred Dhoop Smoke Ribbons",
        promptFragment = "ethereal fluid simulation incense dhoop smoke ribbons curling towards the heavens, volumetric spiritual mist"
    ),
    GOLD_AURA_SUB_SURFACE(
        title = "Luminous Gold Leaf & Marble",
        promptFragment = "subsurface scattering on white Makrana marble and real 24k gold leaf filigree, caustics reflection from holy sarovar water"
    )
}

enum class SocialDimension(
    val label: String,
    val platform: String,
    val aspectParam: String,
    val ratio: String,
    val description: String
) {
    LANDSCAPE_16_9(
        label = "16:9 Landscape",
        platform = "YouTube & OTT 4K",
        aspectParam = "--ar 16:9",
        ratio = "16:9",
        description = "Widescreen TV, YouTube Video Thumbnail, Desktop 4K Wallpaper"
    ),
    VERTICAL_9_16(
        label = "9:16 Vertical",
        platform = "Reels & Shorts",
        aspectParam = "--ar 9:16",
        ratio = "9:16",
        description = "Instagram Reels, TikTok, YouTube Shorts, Mobile Wallpaper"
    ),
    SQUARE_1_1(
        label = "1:1 Square",
        platform = "Instagram Post",
        aspectParam = "--ar 1:1",
        ratio = "1:1",
        description = "Instagram Classic Feed, Profile Display, Album Cover Art"
    ),
    PORTRAIT_4_5(
        label = "4:5 Portrait",
        platform = "Instagram Portrait",
        aspectParam = "--ar 4:5",
        ratio = "4:5",
        description = "Instagram Vertical Feed post, optimal mobile feed screen coverage"
    ),
    CINEMATIC_2_39(
        label = "2.39:1 Anamorphic",
        platform = "Ultra-Widescreen",
        aspectParam = "--ar 21:9",
        ratio = "2.39:1",
        description = "Cinemascope theatrical anamorphic aspect ratio, panoramic film look"
    )
}
