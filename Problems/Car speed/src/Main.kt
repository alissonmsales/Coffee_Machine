fun assesLimit(speed: Int, limit: Int = 60): String {
    if (speed > limit) {
        return "Exceeds the limit by " + (speed - limit) + " kilometers per hour"
    }

    return "Within the limit"
}

fun main(args: Array<String>) {
    var speed = readLine()!!.toInt()
    var speedLimit = readLine()!!

    if (speedLimit == "no limit")
        println(assesLimit(speed))
    else
        println(assesLimit(speed, speedLimit.toInt()))

}