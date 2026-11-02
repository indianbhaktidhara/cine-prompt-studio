package com.example

import com.example.data.model.DirectorStyle
import com.example.data.model.LightingMood
import com.example.data.model.ProductionStandard
import com.example.data.model.SocialDimension
import com.example.data.model.VfxLayer
import com.example.data.repository.SacredPlacesRepository
import com.example.generator.PromptGeneratorEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun `test sacred places repository contains all required circuits`() {
        assertNotNull(SacredPlacesRepository.getPlaceById("golden_temple_amritsar"))
        assertNotNull(SacredPlacesRepository.getPlaceById("takht_patna_sahib"))
        assertNotNull(SacredPlacesRepository.getPlaceById("somnath_temple"))
        assertNotNull(SacredPlacesRepository.getPlaceById("mahakaleshwar_ujjain"))
        assertNotNull(SacredPlacesRepository.getPlaceById("ram_mandir_ayodhya"))
        assertNotNull(SacredPlacesRepository.getPlaceById("chitrakoot_dham"))
        assertNotNull(SacredPlacesRepository.getPlaceById("maihar_sharda_devi"))
        assertNotNull(SacredPlacesRepository.getPlaceById("janakpur_dham"))
        assertNotNull(SacredPlacesRepository.getPlaceById("sitamarhi_punaura"))
        assertNotNull(SacredPlacesRepository.getPlaceById("prem_mandir_vrindavan"))
        assertNotNull(SacredPlacesRepository.getPlaceById("krishna_janmasthan_mathura"))
        assertNotNull(SacredPlacesRepository.getPlaceById("dwarkadhish_dwarka"))
        assertNotNull(SacredPlacesRepository.getPlaceById("jagannath_puri"))
        assertNotNull(SacredPlacesRepository.getPlaceById("khajuraho_temples"))
    }

    @Test
    fun `prompt generator produces valid OTT code block with variations`() {
        val place = SacredPlacesRepository.getPlaceById("golden_temple_amritsar")!!
        val bundle = PromptGeneratorEngine.generate(
            place = place,
            director = DirectorStyle.NOLAN,
            production = ProductionStandard.NETFLIX_ORIGINAL,
            lighting = LightingMood.VOLUMETRIC_GOD_RAYS,
            vfx = VfxLayer.COSMIC_NEBULA,
            dimension = SocialDimension.LANDSCAPE_16_9
        )

        assertTrue(bundle.positivePrompt.contains("Sri Harmandir Sahib"))
        assertTrue(bundle.positivePrompt.contains("Christopher Nolan"))
        assertTrue(bundle.negativePrompt.contains("cgi plastic look"))
        assertTrue(bundle.fullCodeBlock.contains("```cinematic-prompt"))
        assertTrue(bundle.fullCodeBlock.contains("--ar 16:9"))
        assertEquals(4, bundle.angles.size)
    }
}
