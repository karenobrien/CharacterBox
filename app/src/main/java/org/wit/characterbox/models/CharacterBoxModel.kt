package org.wit.characterbox.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterBoxModel(var id: Long = 0,
                             var cName: String = "",
                             var aName: String = "") : Parcelable