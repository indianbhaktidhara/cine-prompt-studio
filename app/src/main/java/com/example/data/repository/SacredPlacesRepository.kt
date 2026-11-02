package com.example.data.repository

import com.example.data.model.HeritageCategory
import com.example.data.model.SacredPlace

object SacredPlacesRepository {

    val places: List<SacredPlace> = listOf(
        // --- GURUDWARAS ---
        SacredPlace(
            id = "golden_temple_amritsar",
            name = "Sri Harmandir Sahib (Golden Temple)",
            hindiName = "श्री हरिमंदिर साहिब (स्वर्ण मंदिर)",
            location = "Amritsar",
            state = "Punjab",
            category = HeritageCategory.GURUDWARAS,
            tagline = "The Abode of God with pristine gilded sanctum amidst Amrit Sarovar",
            architectureStyle = "Indo-Islamic & Rajput Sikh Architecture, 24k Pure Gold Leaf Gilding, White Makrana Marble Inlay (Pietra Dura)",
            waterBody = "Sacred Amrit Sarovar (Pool of Nectar) with glassy mirror reflection",
            spiritualAtmosphere = "Continuous Gurbani Kirtan acoustic resonance, Akal Takht presence, divine humility and universal brotherhood",
            visitingHighlights = listOf(
                "Amrit Vela dawn meditation with glowing holy water reflection",
                "Akal Takht lit with amber floodlights against night sky",
                "Palki Sahib ceremony with floral garlands & brass lamps"
            ),
            keyPromptKeywords = "gilded sanctum, 24k gold leaf domes, Amrit Sarovar reflection, white marble causeway, Darshani Deori gateway, floating water palace, Sikh architecture, gold filigree"
        ),
        SacredPlace(
            id = "takht_patna_sahib",
            name = "Takht Sri Patna Sahib",
            hindiName = "तख्त श्री पटना साहिब",
            location = "Patna",
            state = "Bihar",
            category = HeritageCategory.GURUDWARAS,
            tagline = "Birthplace of the Tenth Guru Sri Guru Gobind Singh Ji Maharaj",
            architectureStyle = "Pristine White Marble Domes, Carved Archways, Gilded Kalash, Classic Sikh Takht Majesty",
            waterBody = "Holy Ganga Riverbank nearby with morning mist",
            spiritualAtmosphere = "Warrior-saint regal energy, sacred armory (Shastras), resonating Jaap Sahib hymns",
            visitingHighlights = listOf(
                "Sacred relics and historic weapons of Guru Gobind Singh Ji",
                "Sunlit marble courtyard with blue and gold Nishan Sahib",
                "Pristine fluted central dome gleaming against azure sky"
            ),
            keyPromptKeywords = "white marble takht, fluted ribbed dome, blue Nishan Sahib flag, brass chandeliers, sacred Sikh armory, riverside atmosphere, ornate floral arches"
        ),
        SacredPlace(
            id = "bangla_sahib_delhi",
            name = "Gurudwara Bangla Sahib",
            hindiName = "गुरुद्वारा बंगला साहिब",
            location = "Connaught Place, New Delhi",
            state = "Delhi NCR",
            category = HeritageCategory.GURUDWARAS,
            tagline = "Sacred healing waters & grand golden dome associated with Guru Harkrishan Ji",
            architectureStyle = "Sikh Architectural Splendor with Giant Central Golden Dome and Tall Nishan Sahib Mast",
            waterBody = "Holy Sarovar pool with multi-tiered pillared colonnade walkways",
            spiritualAtmosphere = "Profound peace amidst bustling capital, compassionate Langar seva, healing Sarovar vibrations",
            visitingHighlights = listOf(
                "Illuminated grand golden dome reflecting in the night Sarovar",
                "Lush manicured courtyards with devotional crowds",
                "Towering Nishan Sahib wrapped in yellow cloth waving in breeze"
            ),
            keyPromptKeywords = "grand golden dome, expansive holy sarovar, white marble colonnades, nocturnal floodlit reflection, high Nishan Sahib flagpole, sacred healing vibes"
        ),
        SacredPlace(
            id = "hemkund_sahib",
            name = "Gurudwara Hemkund Sahib",
            hindiName = "गुरुद्वारा हेमकुंड साहिब",
            location = "Chamoli District, Garhwal",
            state = "Uttarakhand Himalayas",
            category = HeritageCategory.GURUDWARAS,
            tagline = "High-altitude star-shaped alpine sanctuary at 15,200 ft amidst Seven Glacial Peaks",
            architectureStyle = "Iconic Pentagonal Modular Modern Mountain Architecture, Stainless Steel & Slate Stone",
            waterBody = "Crystal-clear turquoise glacial Lokpal Lake reflecting snow-capped peaks",
            spiritualAtmosphere = "Silent Himalayan austerity, Brahma Kamal alpine flowers, transcendent cosmic meditation aura",
            visitingHighlights = listOf(
                "Dramatic morning clouds parting to reveal seven snow-clad Saptashring peaks",
                "Glacial turquoise lake with floating ice and vibrant Brahma Kamal",
                "Pentagonal roof gleaming in thin crisp mountain atmosphere"
            ),
            keyPromptKeywords = "15200 feet high Himalayas, snow-capped jagged peaks, turquoise glacial lake, pentagonal star-shaped sanctuary, Brahma Kamal flowers, mountain fog, epic scale"
        ),
        SacredPlace(
            id = "hazur_sahib_nanded",
            name = "Takht Sachkhand Sri Hazur Sahib",
            hindiName = "तख्त सचखंड श्री हजूर साहिब",
            location = "Nanded",
            state = "Maharashtra",
            category = HeritageCategory.GURUDWARAS,
            tagline = "Sanctified site where Guru Gobind Singh Ji breathed his last & consecrated Guru Granth Sahib",
            architectureStyle = "Intricate White Marble Sanctum with Heavy Gold Embossing, Rajput Cupolas and Golden Chhatris",
            waterBody = "Sacred Godavari River banks with twilight breeze",
            spiritualAtmosphere = "Unbroken reverent majesty, royal Nagara drumbeats, supreme eternal Guru reverence",
            visitingHighlights = listOf(
                "Night laser and light water show on Godavari Ghats",
                "Sacred royal chamber adorned with gems and historic artifacts",
                "Midnight prayers under star-filled Deccan sky"
            ),
            keyPromptKeywords = "sacred Godavari riverbank, golden chhatris, marble sanctum, royal Sikh takht, ancient weaponry, twilight illumination, opulent gold embellishment"
        ),
        SacredPlace(
            id = "paonta_sahib",
            name = "Gurudwara Paonta Sahib",
            hindiName = "गुरुद्वारा पोंटा साहिब",
            location = "Sirmaur",
            state = "Himachal Pradesh",
            category = HeritageCategory.GURUDWARAS,
            tagline = "Where Guru Gobind Singh Ji penned Dasam Granth on the banks of flowing Yamuna",
            architectureStyle = "Serene White Marble Shrine with River-Facing Balconies and Ornate Jharokhas",
            waterBody = "Fast-flowing sacred Yamuna River gushing alongside courtyard",
            spiritualAtmosphere = "Poetic brilliance, literary legacy, tranquil river murmur harmonizing with Kirtan",
            visitingHighlights = listOf(
                "River ghat overlook with rushing crystalline waters",
                "Historic Kavi Darbar memorial hall where 52 court poets gathered",
                "Panoramic Himalayan foothills framing the pristine white domes"
            ),
            keyPromptKeywords = "rushing Yamuna river, Himalayan foothills backdrop, riverfront marble balconies, poetic aura, pristine white domes, golden hour river mist"
        ),

        // --- 12 JYOTIRLINGAS ---
        SacredPlace(
            id = "somnath_temple",
            name = "Somnath Jyotirlinga",
            hindiName = "सोमनाथ ज्योतिर्लिंग",
            location = "Prabhas Patan, Veraval",
            state = "Gujarat",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "The First of the Twelve Holy Jyotirlingas, Eternal Shrine standing tall against crashing Arabian Sea",
            architectureStyle = "Kailash Mahameru Prasad Chalukya / Solanki Nagara Architecture, Sandstone Shikhara, Baan Stambh Arrow Pillar",
            waterBody = "Crashing roaring Arabian Sea waves breaking right against temple seawall",
            spiritualAtmosphere = "Infinite timeless cosmic energy, Mahamrityunjaya chants, indestructible spiritual resilience",
            visitingHighlights = listOf(
                "Dramatic ocean sunset with waves crashing against the stone base",
                "Sound & Light show tracing centuries of unyielding rebirth",
                "Baan Stambh arrow pointing straight to Antarctica without land obstruction"
            ),
            keyPromptKeywords = "Arabian sea coast, crashing ocean waves, towering sandstone shikhara, Solanki temple architecture, sea mist, sunset orange horizon, eternal flame"
        ),
        SacredPlace(
            id = "mahakaleshwar_ujjain",
            name = "Mahakaleshwar Jyotirlinga",
            hindiName = "महाकालेश्वर ज्योतिर्लिंग",
            location = "Ujjain (Avantika)",
            state = "Madhya Pradesh",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "Lord of Time & Eternity, South-facing Dakshinabhimukhi Swayambhu Lingam",
            architectureStyle = "Bhumi-ja style multi-tiered Shikhara, Brass-Clad Torana, Grand Mahakal Lok Corridor",
            waterBody = "Holy Shipra River Ghats (Ram Ghat) with morning dip rituals and evening aarti",
            spiritualAtmosphere = "Fierce primordial Shiva Tandava power, pre-dawn sacred Bhasma Aarti with smoking cowdung ashes, damru beats",
            visitingHighlights = listOf(
                "Pre-dawn 4 AM Bhasma Aarti with glowing camphor and chanting crowd",
                "Mahakal Lok corridor with towering monolithic Shiva sculptures and murals",
                "Rudrasagar Lake illuminated with floating lotus lanterns"
            ),
            keyPromptKeywords = "Bhasma Aarti, sacred holy ash, brass bells, incense smoke, Ujjain Mahakal Lok, ancient temple shikhara, dark mystic lighting, glowing trishul, damru"
        ),
        SacredPlace(
            id = "kashi_vishwanath",
            name = "Kashi Vishwanath Temple",
            hindiName = "काशी विश्वनाथ मंदिर",
            location = "Varanasi (Kashi)",
            state = "Uttar Pradesh",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "The Golden Temple of Lord Shiva in the world's oldest living city",
            architectureStyle = "Nagara Shikhara with 800 kg Gold Plated Spire, Grand Kashi Vishwanath Dham Corridor to Ganga",
            waterBody = "Sacred Mother Ganga River flowing north (Uttaravahini) with ancient stone ghats",
            spiritualAtmosphere = "Moksha dayak liberation energy, reverberating conch shells, Ganga Aarti flame pyramids, ancient eternal wisdom",
            visitingHighlights = listOf(
                "Grand Vishwanath corridor opening directly to Manikarnika & Dashashwamedh Ghats",
                "Gilded main spire glowing under theatrical night spotlights",
                "Evening Ganga Maha Aarti with multi-tiered brass lamp towers"
            ),
            keyPromptKeywords = "Varanasi Ganga ghats, 800kg gold spire, ancient stone stairways, Ganga aarti fire towers, smoke, holy sadhus, timeless stone architecture, deep chiaroscuro"
        ),
        SacredPlace(
            id = "kedarnath_temple",
            name = "Kedarnath Jyotirlinga",
            hindiName = "केदारनाथ ज्योतिर्लिंग",
            location = "Rudraprayag District",
            state = "Uttarakhand Himalayas",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "Highest of the 12 Jyotirlingas at 11,755 ft nestled before the sheer Kedarnath Mountain Face",
            architectureStyle = "Heavy Grey Granite Block Kattyuri Architecture, Conical Lingam Rock, Giant Nandi Monolith",
            waterBody = "Rushing Mandakini River originating from Chorabari Glacier",
            spiritualAtmosphere = "Severe Himalayan asceticism, cosmic silence, chilling glacial winds carrying Om Namah Shivaya chants",
            visitingHighlights = listOf(
                "Sunrise hitting the sheer 22,000 ft Kedarnath peak turning snow into pure gold",
                "Historic stone temple standing resilient against blizzard clouds and mountain mist",
                "Bhim Shila giant boulder standing as a divine protective sentinel"
            ),
            keyPromptKeywords = "11755 ft elevation, massive snow mountain backdrop, ancient grey stone temple, Mandakini river gorge, mountain blizzard fog, golden summit sunrise, Nandi bull"
        ),
        SacredPlace(
            id = "omkareshwar_temple",
            name = "Omkareshwar Jyotirlinga",
            hindiName = "ओंकारेश्वर ज्योतिर्लिंग",
            location = "Mandhata Island, Khandwa",
            state = "Madhya Pradesh",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "Sacred Om-shaped river island embracing the holy Narmada River",
            architectureStyle = "Multi-Storeyed Nagara Temple with Elaborately Carved Stone Pillars and Overhanging Balconies",
            waterBody = "Sacred Narmada River encircling Mandhata island with colorful wooden ferry boats",
            spiritualAtmosphere = "Resonant Pranava (OM) sound frequency, serene river breeze, deep devotional dip rituals",
            visitingHighlights = listOf(
                "Suspension pedestrian bridge swinging high over the Narmada gorge",
                "Island parikrama path winding through ancient caves and cliffside shrines",
                "Twilight boat ride watching the temple silhouette illuminated on calm water"
            ),
            keyPromptKeywords = "Om shaped island, Narmada river gorge, colorful boats, suspension bridge, cliffside stone temples, dusk water reflections, serene spiritual landscape"
        ),
        SacredPlace(
            id = "trimbakeshwar_temple",
            name = "Trimbakeshwar Shiva Temple",
            hindiName = "त्र्यंबकेश्वर ज्योतिर्लिंग",
            location = "Nashik",
            state = "Maharashtra",
            category = HeritageCategory.JYOTIRLINGAS,
            tagline = "Three-faced Lingam representing Brahma, Vishnu & Shiva at origin of Godavari",
            architectureStyle = "Black Basalt Stone Hemadpanthi Architecture with Monolithic Carved Sculptures and Kushavarta Kund",
            waterBody = "Holy Kushavarta Tirtha kund & Brahmagiri mountain waterfalls",
            spiritualAtmosphere = "Mystical monsoon lush greenery, Brahmagiri hill mists, potent ritual sanctum",
            visitingHighlights = listOf(
                "Ancient black basalt stone carved shikhara glistening in fresh monsoon rain",
                "Kushavarta stone tank surrounded by carved archways and pilgrims",
                "Jeweled crown (Trideva Mukut) adorned on special holy days"
            ),
            keyPromptKeywords = "black basalt stone, monsoon rain sheen, Brahmagiri green mountains, holy water tank, intricate Hindu stone carvings, atmospheric mist, Vedic chants"
        ),

        // --- AYODHYA & RAMAYANA CIRCUIT ---
        SacredPlace(
            id = "ram_mandir_ayodhya",
            name = "Shri Ram Janmabhoomi Mandir",
            hindiName = "श्री राम जन्मभूमि मंदिर",
            location = "Ayodhya",
            state = "Uttar Pradesh",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "Magnificent Nagara stone masterpiece celebrating the sacred birthplace of Lord Ram",
            architectureStyle = "Classical Nagara Architecture, Pink Bansi Paharpur Sandstone, 161 ft High Central Shikhara, 366 Carved Pillars, No Iron or Steel",
            waterBody = "Holy Sarayu River with Ram Ki Paidi stepped bathing ghats",
            spiritualAtmosphere = "Ram Rajya grandeur, celebratory divine joy, resonant Ramayana chaupais, sandalwood and rose incense",
            visitingHighlights = listOf(
                "Illuminated Ram Ki Paidi with thousands of earthen diyas during Deepotsav",
                "Breathtaking intricately carved sandstone mandapas and soaring shikhara",
                "Balak Ram 5-year-old deity idol sculpted in black Shaligram stone"
            ),
            keyPromptKeywords = "pink sandstone temple, massive Nagara shikhara, 366 carved stone pillars, Sarayu river ghats, millions of glowing diyas, Deepotsav festival, golden hour sunbeams"
        ),
        SacredPlace(
            id = "chitrakoot_dham",
            name = "Chitrakoot Dham (Ramghat & Kamadgiri)",
            hindiName = "चित्रकूट धाम (रामघाट एवं कामदगिरि)",
            location = "Chitrakoot (Satna/Karwi)",
            state = "Madhya Pradesh / Uttar Pradesh Border",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "Sacred forest exile haven where Lord Ram, Sita & Lakshman spent eleven and a half years",
            architectureStyle = "Ancient Stone Ghats, Temple Pavilions, Holy Kamadgiri Parikrama Path, Natural Limestone Caves",
            waterBody = "Pristine Mandakini River gently winding through lush forested hills",
            spiritualAtmosphere = "Tranquil forest hermitage aura, Tulsidas devotion, saintly meditation tranquility, chiming temple bells",
            visitingHighlights = listOf(
                "Evening Mandakini Aarti at Ramghat with wooden paddle boats and floating leaf lamps",
                "5 km barefoot circumambulation of lush sacred Kamadgiri Hill",
                "Gupt Godavari twin limestone caves with secret underground running stream"
            ),
            keyPromptKeywords = "Mandakini river Ramghat, colorful wooden boats, floating diyas on dark water, lush forested Kamadgiri hills, ancient stone steps, twilight spiritual tranquility"
        ),
        SacredPlace(
            id = "maihar_sharda_devi",
            name = "Maihar Maa Sharda Devi Mandir",
            hindiName = "मैहर माँ शारदा देवी मंदिर",
            location = "Maihar",
            state = "Madhya Pradesh",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "Fabled 51 Shakti Peeth atop the 1,063-step conical Trikuta Hill, legendary abode of warrior Alha",
            architectureStyle = "Hilltop Temple Citadel, 1063 Stone Steps, Panoramic Mountain Ropeway, Red Flagged Spire",
            waterBody = "Alha Kund sacred pool and surrounding green valley streams",
            spiritualAtmosphere = "Fierce maternal divine grace, martial valor echoes of warrior Alha and Udal, mountain winds carrying Jai Mata Di chants",
            visitingHighlights = listOf(
                "Sunrise ropeway ride soaring above morning clouds over Vindhya range",
                "1,063 stone steps illuminated like a glowing necklace winding up the mountain",
                "Panoramic 360-degree vista of Vindhyan valleys and sacred Alha Kund"
            ),
            keyPromptKeywords = "steep conical Trikuta mountain peak, 1063 glowing stone stairs, mountain ropeway cable car, red holy flags fluttering, morning mist valley, goddess shrine, epic elevation"
        ),
        SacredPlace(
            id = "janakpur_dham",
            name = "Janakpur Dham (Janaki Mandir)",
            hindiName = "जनकपुर धाम (जानकी मंदिर)",
            location = "Mithila",
            state = "Nepal / Bihar Border",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "The Nau Lakha Mandir, birthplace of Mata Sita and epic setting of the Ram-Sita wedding",
            architectureStyle = "Grand Indo-Islamic Mughal & Rajput Hybrid Architecture, Bright White Stone, Ornate Multi-Domed Terraces and Jharokhas",
            waterBody = "Dhanush Sagar & Ganga Sagar sacred bathing ponds",
            spiritualAtmosphere = "Mithila art culture, sweet Maithili folk wedding hymns, timeless romance of Maryada Purushottam",
            visitingHighlights = listOf(
                "Nau Lakha Mandir gleaming like a white palace under soft pink sunset sky",
                "Traditional Mithila Madhubani paintings decorating courtyard walls",
                "Grand Vivah Mandap celebrating the divine wedding anniversary"
            ),
            keyPromptKeywords = "white palace architecture, ornate Mughal-Rajput jharokhas, multi-level domes, Mithila art motifs, sacred ponds reflection, royal palace temple, soft sunset"
        ),
        SacredPlace(
            id = "sitamarhi_punaura",
            name = "Sitamarhi (Punaura Dham)",
            hindiName = "सीतामढ़ी (पुनौरा धाम)",
            location = "Sitamarhi",
            state = "Bihar",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "Sacred field where King Janak's golden plow unearthed baby Sita from the Earth",
            architectureStyle = "Historic Northern Nagara Shrine, Sacred Sita Kund Tank, Marble Pavilions",
            waterBody = "Sita Kund holy pond where lotus flowers bloom",
            spiritualAtmosphere = "Mother Earth (Bhoomi Devi) fertility, gentle maternal serenity, rural pastoral devotion",
            visitingHighlights = listOf(
                "Sacred Sita Kund surrounded by blooming pink lotus pads in morning fog",
                "Punaura Dham shrine dedicated to Janaki's miraculous advent",
                "Tranquil rural mango groves and temple sanctum bathed in golden light"
            ),
            keyPromptKeywords = "rural sacred pond, blooming lotus flowers, morning fog, ancient temple pavilion, golden plow legend, gentle pastoral spiritual light, terracotta details"
        ),
        SacredPlace(
            id = "hanumangarhi_ayodhya",
            name = "Hanumangarhi & Kanak Bhavan",
            hindiName = "हनुमानगढ़ी एवं कनक भवन",
            location = "Ayodhya",
            state = "Uttar Pradesh",
            category = HeritageCategory.AYODHYA_RAMAYANA,
            tagline = "The Fort-Temple of Veer Hanuman and the golden palace gifted to Mata Sita",
            architectureStyle = "10th Century Fortified Citadel with 76 Steep Stone Steps and Ornate Golden Palace Courtyard",
            waterBody = "Nearby Sarayu channels and stepped water baolis",
            spiritualAtmosphere = "Unshakable devotion, Bajrangbali power, royal romantic splendor of Kanak Bhavan",
            visitingHighlights = listOf(
                "Ascending the 76 steps of the majestic fortified battlement gate",
                "Kanak Bhavan gold-crowned deity statues adorned with jewels",
                "Resounding Hanuman Chalisa recitation in open stone courtyard"
            ),
            keyPromptKeywords = "fortified citadel temple, 76 steep stone stairs, massive red flag, golden palace interior, Kanak Bhavan arches, vibrant vermilion, royal courtyard"
        ),

        // --- VRINDAVAN & MATHURA (KRISHNA CIRCUIT) ---
        SacredPlace(
            id = "prem_mandir_vrindavan",
            name = "Prem Mandir",
            hindiName = "प्रेम मंदिर",
            location = "Vrindavan",
            state = "Uttar Pradesh",
            category = HeritageCategory.VRINDAVAN_MATHURA,
            tagline = "Temple of Divine Love, ethereal Italian white marble monument with magical chromatic illuminations",
            architectureStyle = "Pure Italian White Carrara Marble, Ornate Carved Relief Panels Depicting Leelas, Modern Dynamic LED Illumination",
            waterBody = "Musical dancing water fountains and surrounding manicured reflection basins",
            spiritualAtmosphere = "Pure Radha-Krishna Prema (devotional ecstasy), enchanting flute music, fairy-tale spiritual bliss",
            visitingHighlights = listOf(
                "Spectacular evening multi-color LED transformation casting vibrant hues on white marble",
                "Life-sized 3D marble tableau of Krishna lifting Govardhan Hill",
                "Laser-straight wide marble promenades filled with joyous singing devotees"
            ),
            keyPromptKeywords = "pure white Carrara marble, spectacular LED color transformation, glowing purple and cyan illumination, detailed marble relief carvings, Radha Krishna leela, nighttime magic"
        ),
        SacredPlace(
            id = "banke_bihari_vrindavan",
            name = "Shri Banke Bihari Mandir",
            hindiName = "श्री बांके बिहारी मंदिर",
            location = "Vrindavan",
            state = "Uttar Pradesh",
            category = HeritageCategory.VRINDAVAN_MATHURA,
            tagline = "Tribhanga posture Lord Krishna whose mesmerizing lotus eyes are veiled by velvet curtains",
            architectureStyle = "Rajasthani Havelian Architecture with Arched Pavilions, Intricate Brass Doorways and Floral Frescoes",
            waterBody = "Yamuna River Keshi Ghat situated a short stroll away",
            spiritualAtmosphere = "Overwhelming ecstatic devotion, tossing of rose petals and saffron water, Radhe Radhe echoes",
            visitingHighlights = listOf(
                "Dramatic curtain-pulling Jhanki darshan to prevent devotees from swooning",
                "Festive Phoolon Ki Holi (flower petal celebrations) in temple courtyard",
                "Enchanted narrow alleyways of old Vrindavan leading to the sanctum"
            ),
            keyPromptKeywords = "intense devotional crowd, showers of marigold and rose petals, velvet curtains, traditional Rajasthani arches, brass chandeliers, warm oil lamp glow, ecstatic emotion"
        ),
        SacredPlace(
            id = "krishna_janmasthan_mathura",
            name = "Shri Krishna Janmasthan",
            hindiName = "श्री कृष्ण जन्मस्थान",
            location = "Mathura",
            state = "Uttar Pradesh",
            category = HeritageCategory.VRINDAVAN_MATHURA,
            tagline = "The Holy Prison Cell (Garbha Griha) where the Supreme Lord manifested in darkness at midnight",
            architectureStyle = "Ancient Underground Stone Vault Sanctum, Fortified Walls, Towering Keshavdev Shikhara",
            waterBody = "Potra Kund sacred tank where Lord Krishna's baby clothes were washed",
            spiritualAtmosphere = "Miraculous midnight avatar advent, lightning storm symbolism, divine protector warmth",
            visitingHighlights = listOf(
                "Underground sanctum stone slab marking the exact birth spot of Krishna",
                "Sprawling Keshavdev temple illuminated during Janmashtami celebrations",
                "Potra Kund stepped water tank with evening aarti lights"
            ),
            keyPromptKeywords = "ancient stone prison cell, stone vault, dark storm sky with lightning, dramatic divine spotlight, Potra Kund steps, heavy fortress walls, Janmashtami festive lights"
        ),
        SacredPlace(
            id = "dwarkadhish_dwarka",
            name = "Dwarkadhish Temple (Jagat Mandir)",
            hindiName = "द्वारकाधीश मंदिर (जगत मंदिर)",
            location = "Dwarka",
            state = "Gujarat",
            category = HeritageCategory.VRINDAVAN_MATHURA,
            tagline = "The 5-Storeyed Sun and Moon Flagged Palace of the King of Dwarka at the edge of the Sea",
            architectureStyle = "Chalukyan Architecture, 5-Storeyed Sandstone Shikhara supported by 72 Carved Pillars, 52-Yard Dhwaja Flag",
            waterBody = "Confluence of Sacred Gomti River and Arabian Sea (Gomti Sangam Ghat)",
            spiritualAtmosphere = "Regal Kingly Majesty (Dwarkanath), oceanic sea breeze, celebratory flag changing rituals",
            visitingHighlights = listOf(
                "Ascending the 52-yard triangular silk flag fluttering high in the Arabian sea wind",
                "Gomti Ghat boat ride watching 72-pillared temple reflection",
                "Chakratirtha beach sunset looking toward submerged legendary Dwarka"
            ),
            keyPromptKeywords = "5-storey sandstone palace temple, 72 carved pillars, 52-yard silk flag fluttering, Gomti river sea confluence, Arabian sea coastline, kingly royal aura, golden hour"
        ),
        SacredPlace(
            id = "bet_dwarka",
            name = "Bet Dwarka (Beyt Shankhodhar)",
            hindiName = "बेंट द्वारका (शंखोद्धार)",
            location = "Gulf of Kutch, Okha",
            state = "Gujarat",
            category = HeritageCategory.VRINDAVAN_MATHURA,
            tagline = "The island residence of Lord Krishna where Sudama gifted beaten rice (Tandul)",
            architectureStyle = "Island Coastal Temple Compound, White Carved Terraces, Ornate Inner Sanctum",
            waterBody = "Gulf of Kutch turquoise sea waters bustling with marine boats and gulls",
            spiritualAtmosphere = "Profound friendship (Sudama-Krishna Sakhya), coastal sea isolation, pure tranquil island sanctuary",
            visitingHighlights = listOf(
                "Scenic boat crossing accompanied by flying seagulls over azure sea",
                "Historic Golden and Silver swing deities representing Lord and his queens",
                "Dramatic coastal reef vistas and Sudama Mandir memorial"
            ),
            keyPromptKeywords = "island temple, turquoise marine water, flock of white seagulls, wooden passenger ferry, coastal sandstone walls, bright sunbeams, pure oceanic devotion"
        ),

        // --- CHAR DHAM & PURI ---
        SacredPlace(
            id = "jagannath_puri",
            name = "Shree Jagannath Temple",
            hindiName = "श्री जगन्नाथ मंदिर",
            location = "Puri",
            state = "Odisha",
            category = HeritageCategory.CHAR_DHAM_PURI,
            tagline = "Lord of the Universe, 214 ft Curvilinear Kalinga Shikhara with Neela Chakra and Patitapabana Flag",
            architectureStyle = "Magnificent Kalinga Architecture, Rekha Deula Style, Pancharatha Plan, 214 ft Stone Spire, Giant Kitchen (Rosha Ghara)",
            waterBody = "Bay of Bengal Golden Beach & sacred Narendra Pushkarini Tank",
            spiritualAtmosphere = "Miraculous coastal wind reversing flag, grand Mahaprasad Chhappan Bhog, world-famous Ratha Yatra ecstasy",
            visitingHighlights = listOf(
                "Daily evening flag changing ritual: priest scaling the 214 ft spire barehanded",
                "Aruna Stambha monolithic chlorite pillar at the Lion Gate (Singhadwara)",
                "Grand Bada Danda boulevard packed with millions during Ratha Yatra"
            ),
            keyPromptKeywords = "towering Kalinga stone shikhara, Neela Chakra brass disc, reverse-flying silk flag, Singhadwara lion gate, Aruna Stambha pillar, Bay of Bengal sea breeze, ancient masonry"
        ),
        SacredPlace(
            id = "konark_sun_temple",
            name = "Konark Sun Temple",
            hindiName = "कोणार्क सूर्य मंदिर",
            location = "Puri District",
            state = "Odisha",
            category = HeritageCategory.CHAR_DHAM_PURI,
            tagline = "The Colossal Stone Chariot of Surya with 24 Intricately Carved Sun Wheels and 7 Horses",
            architectureStyle = "13th Century Kalinga Architectural Zenith, Khondalite Stone, 24 Sundial Wheels, Erotic & Celestial Sculptures",
            waterBody = "Chandrabhaga Beach coastal waters where Sun God healed Samba",
            spiritualAtmosphere = "Astronomical cosmic alignment, raw architectural genius, sun worship solar power",
            visitingHighlights = listOf(
                "First rays of dawn hitting the central sanctum entrance",
                "24 intricately carved stone chariot wheels acting as precise sundials",
                "Dramatic Natya Mandapa dancing hall sculptures frozen in stone"
            ),
            keyPromptKeywords = "colossal stone chariot, 24 carved stone sundial wheels, rearing stone horses, Khondalite weathered stone, golden morning sunbeams, dramatic architectural ruin, IMAX scale"
        ),
        SacredPlace(
            id = "badrinath_temple",
            name = "Badrinath Temple",
            hindiName = "बद्रीनाथ मंदिर",
            location = "Chamoli District",
            state = "Uttarakhand Himalayas",
            category = HeritageCategory.CHAR_DHAM_PURI,
            tagline = "Badrivishal seated in Padmasana between Nar and Narayan Mountain Ranges",
            architectureStyle = "Colorful Tibetan-Inspired Facade, Gabled Timber & Stone Mountain Roof, Hot Sulfur Kund",
            waterBody = "Alaknanda River raging through canyon & steaming Tapt Kund hot springs",
            spiritualAtmosphere = "Vaikuntha on Earth, sage Narada meditation aura, steaming thermal springs amidst sub-zero snow",
            visitingHighlights = listOf(
                "Vibrant painted yellow, green and red arched facade standing out against snow peaks",
                "Steam rising from Tapt Kund sulfur baths into crisp morning mountain air",
                "Neelkanth mountain peak glowing like a silver pyramid in background"
            ),
            keyPromptKeywords = "colorful painted temple facade, Neelkanth peak background, raging Alaknanda river gorge, steaming thermal spring, Himalayan snow landscape, crystal mountain air"
        ),

        // --- HISTORIC & ARCHITECTURAL WONDERS ---
        SacredPlace(
            id = "khajuraho_temples",
            name = "Khajuraho Group of Monuments",
            hindiName = "खजुराहो मंदिर समूह (कंदरिया महादेव)",
            location = "Chhatarpur District",
            state = "Madhya Pradesh",
            category = HeritageCategory.HERITAGE_TEMPLES,
            tagline = "UNESCO World Heritage Kandariya Mahadeva, the pinnacle of Chandela medieval stone carving art",
            architectureStyle = "Chandela Sandstone Nagara Architecture, Soaring Shikharas Resembling Mount Kailash Peaks, 800+ Exquisite Sculptures",
            waterBody = "Shivsagar Lake and surrounding garden reflection moats",
            spiritualAtmosphere = "Celebration of all facets of human existence, divine cosmic union, artistic mastery of stone",
            visitingHighlights = listOf(
                "Kandariya Mahadeva shikhara with 84 mini-shikharas cascading like mountain peaks",
                "Golden hour sunlight bringing warm sandstone celestial nymphs (Apsaras) to life",
                "Sprawling manicured lawns illuminated during Khajuraho Dance Festival"
            ),
            keyPromptKeywords = "Kandariya Mahadeva, cascading sandstone shikharas, intricate erotic and celestial stone carvings, golden hour warm amber light, UNESCO world heritage, cinematic depth"
        ),
        SacredPlace(
            id = "meenakshi_amman",
            name = "Meenakshi Amman Temple",
            hindiName = "मीनाक्षी अम्मन मंदिर",
            location = "Madurai",
            state = "Tamil Nadu",
            category = HeritageCategory.HERITAGE_TEMPLES,
            tagline = "The City of Nectar's Colossal Dravidian Wonderland with 14 Soaring Multi-Tiered Gopurams",
            architectureStyle = "Dravidian Masterpiece, 14 Rainbow-Colored Sculpted Gopurams, Hall of Thousand Pillars, Musical Pillars",
            waterBody = "Potramarai Kulam (Golden Lotus Tank) enclosed by frescoed corridors",
            spiritualAtmosphere = "Feminine divine sovereignty of Queen Meenakshi, mesmerizing Dravidian nadaswaram music, camphor aroma",
            visitingHighlights = listOf(
                "South Gopuram soaring 170 ft adorned with 1,500 colorful mythological figures",
                "Hall of Thousand Pillars with infinite perspective alignment and stone sculptures",
                "Golden Lotus Tank reflecting towering gopuram under dramatic starry sky"
            ),
            keyPromptKeywords = "massive multi-tiered rainbow gopurams, Dravidian temple towers, thousands of painted stone deities, golden lotus tank, Hall of Thousand Pillars, incense smoke, deep shadow"
        ),
        SacredPlace(
            id = "hampi_virupaksha",
            name = "Virupaksha Temple & Hampi Ruins",
            hindiName = "विरूपाक्ष मंदिर (हम्पी)",
            location = "Hampi, Vijayanagara",
            state = "Karnataka",
            category = HeritageCategory.HERITAGE_TEMPLES,
            tagline = "Unbroken 7th-century active Shiva worship amidst surreal monolithic boulder-strewn landscape",
            architectureStyle = "Vijayanagara Dravidian Architecture, 160 ft Stepped Entrance Gopuram, Ranga Mandapa, Stone Chariot at Vittala",
            waterBody = "Tungabhadra River rushing through granite boulder gorges",
            spiritualAtmosphere = "Ancient empire grandeur, timeless boulder silence, surreal Martian-like terrain bathed in golden sunset",
            visitingHighlights = listOf(
                "Sunset from Matanga Hill watching the massive Virupaksha gopuram dominate the boulder horizon",
                "Iconic monolithic Stone Chariot with revolving wheels at nearby Vittala complex",
                "Coracle boat rides on the swirling Tungabhadra River"
            ),
            keyPromptKeywords = "surreal granite boulders, massive 160 ft entrance gopuram, Tungabhadra river valley, sunset over ancient ruins, Vijayanagara stone chariot, golden dust haze"
        )
    )

    fun getPlacesByCategory(category: HeritageCategory): List<SacredPlace> {
        return if (category == HeritageCategory.ALL) {
            places
        } else {
            places.filter { it.category == category }
        }
    }

    fun getPlaceById(id: String): SacredPlace? {
        return places.firstOrNull { it.id == id }
    }

    fun searchPlaces(query: String): List<SacredPlace> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return places
        return places.filter {
            it.name.lowercase().contains(q) ||
                it.hindiName.contains(q) ||
                it.location.lowercase().contains(q) ||
                it.state.lowercase().contains(q) ||
                it.architectureStyle.lowercase().contains(q) ||
                it.tagline.lowercase().contains(q) ||
                it.keyPromptKeywords.lowercase().contains(q)
        }
    }
}
