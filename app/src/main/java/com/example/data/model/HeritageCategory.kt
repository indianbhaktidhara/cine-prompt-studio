package com.example.data.model

enum class HeritageCategory(
    val title: String,
    val subtitle: String,
    val iconName: String
) {
    ALL(
        title = "All Holy Sites",
        subtitle = "Full Sacred Heritage Directory",
        iconName = "AutoAwesome"
    ),
    GURUDWARAS(
        title = "Gurudwaras",
        subtitle = "Golden Temple, Patna Sahib, Hemkund & Takhts",
        iconName = "WbSunny"
    ),
    JYOTIRLINGAS(
        title = "12 Jyotirlingas",
        subtitle = "Somnath, Mahakaleshwar, Kashi, Kedarnath & more",
        iconName = "LocalFireDepartment"
    ),
    AYODHYA_RAMAYANA(
        title = "Ayodhya & Ramayana",
        subtitle = "Ram Mandir, Chitrakoot, Janakpur, Sitamarhi & Maihar",
        iconName = "TempleHindu"
    ),
    VRINDAVAN_MATHURA(
        title = "Vrindavan & Mathura",
        subtitle = "Prem Mandir, Banke Bihari, Janmasthan & Dwarka",
        iconName = "Favorite"
    ),
    CHAR_DHAM_PURI(
        title = "Char Dham & Puri",
        subtitle = "Jagannath Puri, Konark Sun Temple, Badrinath",
        iconName = "Place"
    ),
    HERITAGE_TEMPLES(
        title = "Historic Wonders",
        subtitle = "Khajuraho, Meenakshi, Hampi & Brihadisvara",
        iconName = "AccountBalance"
    )
}
