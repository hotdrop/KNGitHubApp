package jp.hotdrop.usecase

import io.ktor.client.HttpClient
import jp.hotdrop.data.repository.GithubRepositoryImpl

// これ本当はexpectにしてAndroidはこれ実装、iOSは別実装の方が良かったかも
object UseCaseFactory {

    fun createContributorUseCase(): ContributorUseCase {
        val client = HttpClient()
        val repository = GithubRepositoryImpl(client)
        return ContributorUseCaseImpl(repository)
    }
}