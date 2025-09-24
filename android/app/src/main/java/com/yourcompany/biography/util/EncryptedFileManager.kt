package com.yourcompany.biography.util

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptedFileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    fun writeText(fileName: String, content: String) {
        val encryptedFile = createEncryptedFile(fileName)
        encryptedFile.openFileOutput().use { outputStream ->
            outputStream.write(content.encodeToByteArray())
        }
    }

    fun readText(fileName: String): String? {
        val encryptedFile = createEncryptedFile(fileName)
        if (!encryptedFile.file.exists()) return null
        return encryptedFile.openFileInput().use { inputStream ->
            inputStream.readBytes().decodeToString()
        }
    }

    private fun createEncryptedFile(fileName: String): EncryptedFile {
        val file = File(context.filesDir, fileName)
        return EncryptedFile.Builder(
            context,
            file,
            masterKey,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }
}
