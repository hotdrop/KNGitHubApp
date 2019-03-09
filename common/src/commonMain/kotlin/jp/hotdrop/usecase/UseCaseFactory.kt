package jp.hotdrop.usecase

import io.ktor.client.HttpClient
import jp.hotdrop.data.repository.GithubRepositoryImpl

object UseCaseFactory {

    fun createContributorUseCase(): ContributorUseCase {
        val client = HttpClient()
        val repository = GithubRepositoryImpl(client)
        return ContributorUseCaseImpl(repository)
    }
}