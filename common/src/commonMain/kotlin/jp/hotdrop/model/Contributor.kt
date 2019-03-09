package jp.hotdrop.model

data class Contributor(
    val name: String,
    val avatarUrl: String,
    val contribution: Int
) {
    fun toStringContribution(): String = contribution.toString()
}