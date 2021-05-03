package edu.ib.audiometry

import java.io.Serializable
import java.time.LocalDate

class Result(name: String, date: LocalDate, volumesArray: Array<Float>) :
    Serializable/*, Comparable<Result>*/ {

    private var fileName: String = name
    private var created: LocalDate = date
    private var volumes: Array<Float> = volumesArray

    fun getCreated(): LocalDate {
        return created
    }

    override fun toString(): String {
        return "Result(fileName='$fileName', created=$created, volumes=${volumes.contentToString()})"
    }

    /*override fun compareTo(other: Result): Int {
        *//*val name: Int = this.created.compareTo(other.getCreated())
        return if (name == 0) name.compareTo(other.getCreated()) else name*//*

        *//*if (this.created.compareTo(other.created)) {*//*

        }
    }*/

}