package edu.ib.audiometry

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class Result(name: String, results: String) :
    Serializable/*, Comparable<Result>*/ {

    private var fileName: String = name
    private var volumes: String = results

    fun getFileName(): String {
        return fileName;
    }

    fun getVolumes(): String {
        return volumes;
    }

    override fun toString(): String {
        return "Result(fileName='$fileName', volumes=${volumes})"
    }
}