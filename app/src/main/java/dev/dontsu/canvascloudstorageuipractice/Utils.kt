package dev.dontsu.canvascloudstorageuipractice

import androidx.compose.ui.geometry.Offset

object Utils {

    fun isPointInPolygon(point: Offset, polygon: List<Offset>): Boolean {
        var minX = 0f
        var maxX = 0f
        var minY = 0f
        var maxY = 0f

        for (coordinate in polygon) {
            minX = coordinate.x.coerceAtMost(minX)
            maxX = coordinate.x.coerceAtLeast(maxX)
            minY = coordinate.y.coerceAtMost(minY)
            maxY = coordinate.y.coerceAtLeast(maxY)
        }

        if (point.x < minX || point.x > maxX || point.y < minY || point.y > maxY) {
            return false
        }

        // 아래는 Ray-Casting 알고리즘을 사용했다. (Ray-Casting algorithm은 insde-polygon test의 일종)
        // 점과 (단순)다각형이 있을 때, 점이 다각형 안에 들어있는가를 판별한다.
        var isInside = false

        var j = polygon.size - 1

        polygon.forEachIndexed { index, coordinate ->
            if ((coordinate.y > point.y) != (polygon[j].y > point.y) && point.x < (polygon[j].x - coordinate.x) * (point.y - coordinate.y) / (polygon[j].y - coordinate.y) + coordinate.x) {
                isInside = !isInside
            }

            j = index
        }

        return isInside

    }

}