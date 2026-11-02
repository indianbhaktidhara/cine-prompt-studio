package com.example.generator

import com.example.data.model.DirectorStyle
import com.example.data.model.LightingMood
import com.example.data.model.ProductionStandard
import com.example.data.model.SacredPlace
import com.example.data.model.SocialDimension
import com.example.data.model.VfxLayer

data class AngleVariation(
    val title: String,
    val angleType: String,
    val description: String,
    val cameraMove: String,
    val promptText: String
)

data class GeneratedPromptBundle(
    val place: SacredPlace,
    val director: DirectorStyle,
    val production: ProductionStandard,
    val lighting: LightingMood,
    val vfx: VfxLayer,
    val dimension: SocialDimension,
    val positivePrompt: String,
    val negativePrompt: String,
    val technicalParameters: String,
    val angles: List<AngleVariation>,
    val fullCodeBlock: String
)

object PromptGeneratorEngine {

    fun generate(
        place: SacredPlace,
        director: DirectorStyle,
        production: ProductionStandard,
        lighting: LightingMood,
        vfx: VfxLayer,
        dimension: SocialDimension
    ): GeneratedPromptBundle {
        val positivePrompt = buildString {
            append("Cinematic masterpiece of ${place.name} in ${place.location}, ${place.state}. ")
            append("Directed in the signature visual style of ${director.directorName} (${director.aestheticSummary}). ")
            append("Shot on ${production.cameraSensor} paired with ${production.lensPackage}. ")
            append("Architectural majesty featuring ${place.architectureStyle}, showcasing ${place.keyPromptKeywords}. ")
            append("Atmospheric DOP lighting with ${lighting.promptFragment}. ")
            append("Visual effects and CGI: ${vfx.promptFragment}. ")
            append("Water dynamics: ${place.waterBody}. ")
            append("Color grade: ${director.colorTone}. ")
            append("Production grade: ${production.ecosystemNotes}. ")
            append("Ultra-photorealistic 8K resolution, volumetric depth, anamorphic lens flares, Unreal Engine 5.4 Lumen global illumination, Octane render quality, IMAX aspect ratio ${dimension.ratio}.")
        }

        val negativePrompt = buildString {
            append("cgi plastic look, low resolution, blurry, oversaturated colors, cartoonish, 3d render seams, ")
            append("distorted architecture, crooked spires, extra domes, anatomically incorrect hands or pilgrims, ")
            append("modern billboards, power lines, ugly watermarks, stock photo text, compression artifacts, ")
            append("lens smudge, blown-out flat highlights, dull washed shadows, noise, cloned elements.")
        }

        val technicalParams = buildString {
            appendLine("CAMERA RIG       : ${production.cameraSensor}")
            appendLine("LENS PACKAGE     : ${production.lensPackage}")
            appendLine("FOCAL LENGTH     : 35mm - 85mm T1.5 Anamorphic Squeeze")
            appendLine("SHUTTER & ISO    : 180° Shutter Angle, Native ISO 800 (Clean Latitude)")
            appendLine("LIGHTING RIG     : ${lighting.title} + Natural Volumetric Haze")
            appendLine("VFX & SHADERS    : ${vfx.title} (Lumen GI, Subsurface Raytracing)")
            appendLine("COLOR SPACE      : ACEScg / Dolby Vision P3 / DCI Color Standard")
            append("SOCIAL ASPECT    : ${dimension.aspectParam} (${dimension.platform} | ${dimension.ratio})")
        }

        val angles = listOf(
            AngleVariation(
                title = "Angle 1: IMAX 70mm Aerial Drone Reveal",
                angleType = "Sweeping Bird's-Eye Master Shot",
                description = "Sky-high panoramic drone perspective showcasing the sprawling architectural footprint and cosmic scale.",
                cameraMove = "Slow forward tracking push-in at 400 ft altitude",
                promptText = "IMAX 70mm aerial drone master shot of ${place.name}, high altitude overview revealing the grand architectural symmetry, ${place.waterBody}, morning mist rolling over the landscape, directed by ${director.directorName}, ${dimension.aspectParam} --style raw --v 6.1"
            ),
            AngleVariation(
                title = "Angle 2: Low-Angle Holy Water Reflection",
                angleType = "Low-Angle Symmetrical Mirror Shot",
                description = "Camera positioned inches above the sacred water capturing the glowing golden spires mirrored perfectly in ripples.",
                cameraMove = "Gentle floating slider motion skimmed across water surface",
                promptText = "Low-angle cinematic water-level shot of ${place.name}, glassy mirror reflection in ${place.waterBody}, illuminated marble causeway, floating diyas and gentle golden hour ripples, Panavision anamorphic, directed by ${director.directorName}, ${dimension.aspectParam} --style raw --v 6.1"
            ),
            AngleVariation(
                title = "Angle 3: Anamorphic Eye-Level God Rays",
                angleType = "Eye-Level Volumetric Light Shafts",
                description = "Pilgrim perspective facing the sanctum with piercing god rays filtering through incense dhoop smoke.",
                cameraMove = "Slow cinematic pedestal rise with gentle anamorphic horizontal streak",
                promptText = "Eye-level majestic cinematic portrait of ${place.name}, shafts of golden volumetric sunlight piercing through rising incense smoke ribbons, 360-degree intricate stone and gold relief, spiritual aura, directed by ${director.directorName}, ${dimension.aspectParam} --style raw --v 6.1"
            ),
            AngleVariation(
                title = "Angle 4: Macro Telephoto Gold & Cosmic Nebula",
                angleType = "Extreme Close-Up & Cosmic VFX Aura",
                description = "Macro lens intimacy focusing on 24k gold leaf filigree against a swirling cosmic starlight nebula sky.",
                cameraMove = "Slow rack-focus from delicate stone carving to infinite celestial starfield",
                promptText = "Macro telephoto 8K close-up of ${place.name} golden kalash and stone filigree carvings, background opening into an ethereal glowing cosmic nebula and stardust aura, subsurface scattering, directed by ${director.directorName}, ${dimension.aspectParam} --style raw --v 6.1"
            )
        )

        val fullCodeBlock = buildString {
            appendLine("```cinematic-prompt")
            appendLine("/* ========================================================================")
            appendLine(" * OTT PREMIUM IMAGE PROMPT SPECIFICATION")
            appendLine(" * TEMPLE / HERITAGE SITE : ${place.name.uppercase()}")
            appendLine(" * LOCATION               : ${place.location}, ${place.state}")
            appendLine(" * CATEGORY               : ${place.category.title}")
            appendLine(" * DIRECTOR SIGNATURE     : ${director.displayName}")
            appendLine(" * INDUSTRY ECOSYSTEM     : ${production.title}")
            appendLine(" * TARGET DIMENSION       : ${dimension.label} (${dimension.platform})")
            appendLine(" * ======================================================================== */")
            appendLine()
            appendLine("/* --- [POSITIVE PROMPT] --- */")
            appendLine(positivePrompt)
            appendLine()
            appendLine("/* --- [NEGATIVE PROMPT] --- */")
            appendLine(negativePrompt)
            appendLine()
            appendLine("/* --- [CINEMATOGRAPHY & DOP PARAMETERS] --- */")
            appendLine(technicalParams)
            appendLine()
            appendLine("/* --- [CAMERA ANGLES & SHOT VARIATIONS] --- */")
            angles.forEachIndexed { index, angle ->
                appendLine("[VARIATION ${index + 1}: ${angle.title.uppercase()}]")
                appendLine("Type       : ${angle.angleType}")
                appendLine("Camera Move: ${angle.cameraMove}")
                appendLine("Prompt     : ${angle.promptText}")
                appendLine()
            }
            appendLine("/* --- [MIDJOURNEY / SDXL / FLUX EXECUTION COMMAND] --- */")
            appendLine("/imagine prompt: ${place.name}, ${place.location}, in the visual style of ${director.directorName}, ${production.cameraSensor}, ${lighting.promptFragment}, ${vfx.promptFragment}, 8k photorealistic, cinematic lighting ${dimension.aspectParam} --style raw --stylize 250 --v 6.1")
            append("```")
        }

        return GeneratedPromptBundle(
            place = place,
            director = director,
            production = production,
            lighting = lighting,
            vfx = vfx,
            dimension = dimension,
            positivePrompt = positivePrompt,
            negativePrompt = negativePrompt,
            technicalParameters = technicalParams,
            angles = angles,
            fullCodeBlock = fullCodeBlock
        )
    }
}
