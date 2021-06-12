package edu.ib.audiometry

import java.io.Serializable
import java.time.LocalDate
import java.util.*

class Result(name: String, date: LocalDate, results: String) :
    Serializable/*, Comparable<Result>*/ {

    private var fileName: String = name
    private var created: LocalDate = date
    private var volumes: String = results

    fun getCreated(): LocalDate {
        return created
    }

    override fun toString(): String {
        return "Result(fileName='$fileName', created=$created, volumes=${volumes})"
    }

    /*override fun compareTo(other: Result): Int {
        *//*val name: Int = this.created.compareTo(other.getCreated())
        return if (name == 0) name.compareTo(other.getCreated()) else name*//*

        *//*if (this.created.compareTo(other.created)) {*//*

        }
    }*/

}