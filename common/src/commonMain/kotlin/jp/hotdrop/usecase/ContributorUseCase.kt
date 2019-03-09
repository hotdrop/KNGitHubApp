package jp.hotdrop.usecase

import jp.hotdrop.model.Contributor

interface ContributorUseCase {
    suspend fun findContributors(): List<Contributor>
}