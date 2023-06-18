package com.kseniabl.deliverypizza.domain.adapter

import com.kseniabl.data.main.repository.MainRepositoryImpl
import com.kseniabl.deliverypizza.domain.MainRepository
import com.kseniabl.deliverypizza.domain.BannerImageModel
import javax.inject.Inject

class MainAdapter @Inject constructor(
    private val repositoryImpl: MainRepositoryImpl
): MainRepository {

    override fun getBanners(): List<BannerImageModel> {
        return repositoryImpl.getBanners().map { BannerImageModel(it.bannerImg) }
    }

}