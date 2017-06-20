package com.chenbing.oneweather.Utils

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.text.SimpleDateFormat
import java.util.Date

import android.os.Environment
import com.coorchice.ban.utils.loge

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/24
 * Notes:
 */
object FileUtil {
    private val APP_FILE_DIR = GetSDCard() + "/OneWeather/"
    private val APP_CACHE_DIR = APP_FILE_DIR + "cache/"
    private val APP_PHOTO_DIR = APP_FILE_DIR + "photo/"
    private val APP_OBJECT_DIR = APP_CACHE_DIR + "object/"
    private val APP_CRASH_DIR = APP_FILE_DIR + "crash/"

    fun GetAppFileDir(): String {
        val file = File(APP_FILE_DIR)
        if (!file.exists()) {
            file.mkdirs()
        }
        return APP_FILE_DIR
    }

    fun GetAppCacheDir(): String {
        val file = File(APP_CACHE_DIR)
        if (!file.exists()) {
            file.mkdirs()
        }
        return APP_CACHE_DIR
    }

    fun GetAppPhotoDir(): String {
        val file = File(APP_PHOTO_DIR)
        if (!file.exists()) {
            file.mkdirs()
        }
        return APP_PHOTO_DIR
    }

    fun GetAppObjectDir(): String {
        val file = File(APP_OBJECT_DIR)
        if (!file.exists()) {
            file.mkdirs()
        }
        return APP_OBJECT_DIR
    }

    val appCrashDir: String
        get() {
            val file = File(APP_OBJECT_DIR)
            if (!file.exists()) {
                file.mkdirs()
            }
            return APP_CRASH_DIR
        }

    fun SaveObject(path: String, `object`: Any) {
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null
        val file = File(path)
        try {
            loge(file.absolutePath)
            fos = FileOutputStream(file)
            oos = ObjectOutputStream(fos)
            oos.writeObject(`object`)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (oos != null) {
                    oos.close()
                }
                if (fos != null) {
                    fos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    @Throws(FileNotFoundException::class)
    fun <T> RestoreObject(path: String, clazz: Class<T>): T? {
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null
        var t: T? = null
        val file = File(path)
        if (!file.exists()) {
            throw FileNotFoundException("请求文件不存在，请检查路径是否正确。")
        }
        try {
            loge(file.absolutePath)
            fis = FileInputStream(file)
            ois = ObjectInputStream(fis)
            t = ois.readObject() as T
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (ois != null) {
                    ois.close()
                }
                if (fis != null) {
                    fis.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return t
        }
    }

    fun ScanDirectory(path: String): String {
        val file = File(path)
        val files = file.listFiles()
        val filesName = StringBuffer()
        val format = "index:%d\nfileName:%s\nfileSize:%s\n最后修改时间:%s\n--------------\n"
        var count = 0
        val dateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
        for (tempFile in files) {
            count++
            if (tempFile.isFile) {
                val fileLength = tempFile.length()
                val fileSize: String
                if (fileLength >= 1024) {
                    fileSize = ((fileLength / 1024).toString() + "K").toString()
                } else
                    fileSize = (fileLength.toString() + "B").toString()

                val lastModify = tempFile.lastModified()
                val lastModifyString = dateFormat.format(Date(lastModify))

                filesName.append(String.format(format, count, tempFile.name, fileSize,
                        lastModifyString))
            }
        }
        filesName.append(String.format("共计文件数:%d\n", count))
        return filesName.toString()
    }


    fun ExistSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !Environment.isExternalStorageRemovable()
    }

    /**
     * 尝试获取SDCard的路径

     * @return 如果SDCard存在返回其路径,如果不存在返回""
     */
    fun GetSDCard(): String {
        if (ExistSDCard()) {
            return Environment.getExternalStorageDirectory().absolutePath
        } else {
            return ""
        }
    }


}
