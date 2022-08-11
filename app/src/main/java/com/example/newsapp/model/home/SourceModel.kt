package com.example.newsapp.model.home

import android.os.Parcel
import android.os.Parcelable

data class SourceModel(var id : String?=null , var name : String ?=null):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SourceModel> {
        override fun createFromParcel(parcel: Parcel): SourceModel {
            return SourceModel(parcel)
        }

        override fun newArray(size: Int): Array<SourceModel?> {
            return arrayOfNulls(size)
        }
    }
}
