package hr.algebra.nasa.dao

import android.content.Context

fun getNasaRepository(context: Context?) = NasaSqlHelper(context)