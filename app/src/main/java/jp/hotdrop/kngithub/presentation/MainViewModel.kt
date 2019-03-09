package jp.hotdrop.kngithub.presentation

import androidx.lifecycle.*
import jp.hotdrop.model.Contributor
import jp.hotdrop.usecase.ContributorUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val useCase: ContributorUseCase
): ExViewModel(), LifecycleObserver {

    private val mutableContributors = MutableLiveData<List<Contributor>>()
    val contributors: LiveData<List<Contributor>> = mutableContributors

    private val mutableError = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = mutableError

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun findContributors() {
        launch {
            try {
                val contributors = useCase.findContributors()
                Timber.d("コントリビュータを取得しました。取得数=${contributors.size}")
                contributors.forEach {
                    Timber.d("    ${it.name}")
                }
                mutableContributors.postValue(contributors)
            } catch (error: Throwable) {
                Timber.e(error)
                mutableError.postValue(error)
            }
        }
    }
}