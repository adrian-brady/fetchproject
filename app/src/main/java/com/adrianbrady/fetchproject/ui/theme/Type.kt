package com.adrianbrady.fetchproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.adrianbrady.fetchproject.R

val AbrilFatFace = FontFamily(
    Font(R.font.abril_fatface_regular)
)

val MontSerrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
)