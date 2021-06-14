package com.tmobile.subbu.model.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CardsResponse(
    val page: PageResponse
)

data class PageResponse(
    val cards: List<Card>
)

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("card_type") val cardType: String,
    @Embedded @SerializedName("card") val cardInfo: CardInfo
)

data class CardInfo(
    val value: String?,
    @Embedded val attributes: Attributes?,
    @Embedded(prefix = "tit_") val title: Title?,
    @Embedded(prefix = "desc_") val description: Title?,
    @Embedded val image: Image?
)

data class Title(
    @ColumnInfo(name = "val") val value: String,
    @Embedded(prefix = "tit_") val attributes: Attributes
)

data class Image(
    val url: String,
    @Embedded val size: Size
)

data class Size(
    val width: Int,
    val height: Int
)

data class Attributes(
    @SerializedName("text_color") val textColor: String,
    @Embedded val font: Font
)

data class Font(
    val size: Int
)
