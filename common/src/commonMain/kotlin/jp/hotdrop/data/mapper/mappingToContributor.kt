package jp.hotdrop.data.entity

import jp.hotdrop.model.Contributor

fun ContributorEntity.toContributor(): Contributor =
        Contributor(
            name = this.login,
            avatarUrl = this.avatarUrl,
            contribution = this.contributions
        )