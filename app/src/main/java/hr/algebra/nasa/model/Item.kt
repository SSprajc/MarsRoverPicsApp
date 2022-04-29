package hr.algebra.nasa.model

data class Item(
    var _id: Long?,
    val picturePath: String,
    val roverName: String,
    val cameraName: String,
    val earthDate: String,
    val roverStatus: String,
    val landingDate: String
)
