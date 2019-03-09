package jp.hotdrop.kngithub.module

import jp.hotdrop.usecase.UseCaseFactory
import org.koin.dsl.module

val useCaseModule = module {
    single { UseCaseFactory.createContributorUseCase() }
}