package hr.algebra.nasa.handler

import android.content.Context
import android.util.Log
import hr.algebra.nasa.factory.createGetHttpUrlConnection
import java.io.File
import java.lang.Exception
import java.net.HttpURLConnection
import java.nio.file.Files
import java.nio.file.Paths

fun downloadImageAndStore(
    context: Context,
    url: String,
    namePart1: String,
    namePart2: String
): String? {
    val fileName = namePart1 + namePart2
    val ext = url.substring(url.lastIndexOf('.')).lowercase()
    val file: File = createFile(context, fileName, ext)

    try {
        val con: HttpURLConnection = createGetHttpUrlConnection(url)
        Files.copy(con.inputStream, Paths.get(file.toURI()))
        return file.absolutePath
    } catch (e: Exception) {
        Log.e("DOWNLOAD IMAGE", e.message, e)
    }
    return null
}

fun createFile(context: Context, fileName: String, ext: String): File {
    val dir = context.applicationContext.getExternalFilesDir(null)
    val file = File(dir, File.separator + fileName + ext)
    if (file.exists()) {
        file.delete()
    }
    return file
}
