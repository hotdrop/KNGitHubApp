package jp.hotdrop.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import jp.hotdrop.data.entity.ContributorEntity
import jp.hotdrop.data.entity.toContributor
import jp.hotdrop.model.Contributor
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GithubRepositoryImpl(
    private val client: HttpClient,
    private val apiEndpoint: String = "https://api.github.com/repos/takahirom/conference-app-2019"
): GithubRepository {

    override suspend fun findContributors(): List<Contributor> {
        val rawResponse = client.get<String> {
            url("$apiEndpoint/contributors")
            accept(ContentType.Application.Json)
        }

        return Json.parse(ContributorEntity.serializer().list, rawResponse)
            .map {
                it.toContributor()
            }
    }
}