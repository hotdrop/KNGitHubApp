package org.kotlin.mpp.mobile

import org.kotlin.mpp.mobile.jp.hotdrop.model.Contributor

//expect fun platformName(): String

//fun createApplicationScreenMessage(): String {
//    return "Kotlin Rocks on ${platformName()}"
//}

fun getContributorName(): String {
    val contributor = Contributor(name = "Kenji", star = 3)
    return contributor.name
}