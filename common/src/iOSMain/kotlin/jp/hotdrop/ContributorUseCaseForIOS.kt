package jp.hotdrop

import jp.hotdrop.model.Contributor
import jp.hotdrop.usecase.UseCaseFactory
import kotlinx.coroutines.launch

class ContributorUseCaseForIOS {

    fun findAll(onSuccess: (List<Contributor>) -> Unit)  {
        IOSCoroutineScope().also { mainScope ->
            mainScope.launch {
                val useCase = UseCaseFactory.createContributorUseCase()
                try {
                    val contributors = useCase.findContributors()
                    onSuccess(contributors)
                } catch (e: Throwable) {
                    mainScope.cancel()
                    throw e
                }
            }
        }
    }
}