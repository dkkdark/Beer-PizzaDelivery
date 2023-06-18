package com.kseniabl.deliverypizza.domain

import javax.inject.Inject

class BannersUseCases @Inject constructor(
    private val repository: MainRepository
) {

    fun getBannersUseCase(): List<BannerImageModel> {
        return repository.getBanners()
    }

}