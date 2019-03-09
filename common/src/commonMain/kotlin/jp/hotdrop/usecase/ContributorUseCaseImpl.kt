package jp.hotdrop.usecase

import jp.hotdrop.data.repository.GithubRepository
import jp.hotdrop.model.Contributor

class ContributorUseCaseImpl(
    private val githubRepository: GithubRepository
): ContributorUseCase {
    override suspend fun findContributors(): List<Contributor> {
        return githubRepository.findContributors()
    }
}