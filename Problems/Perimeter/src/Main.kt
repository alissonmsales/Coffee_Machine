import kotlin.math.hypot

fun perimeter(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double, x4: Double = x3, y4: Double = y3): Double {
    var perimeter = 0.0
    perimeter += hypot(x1 - x2, y1 - y2)
    perimeter += hypot(x2 - x3, y2 - y3)

    perimeter += hypot(x3 - x4, y3 - y4)
    perimeter += hypot(x4 - x1, y4 - y1)

    return perimeter
}
