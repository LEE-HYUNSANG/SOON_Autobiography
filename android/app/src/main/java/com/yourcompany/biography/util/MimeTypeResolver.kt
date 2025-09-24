package com.yourcompany.biography.util

import android.webkit.MimeTypeMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MimeTypeResolver @Inject constructor() {
    fun fromExtension(extension: String): String? =
        MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.lowercase())
}
