package jp.hotdrop.data.repository

import jp.hotdrop.model.Contributor

interface GithubRepository {
    suspend fun findContributors(): List<Contributor>
}