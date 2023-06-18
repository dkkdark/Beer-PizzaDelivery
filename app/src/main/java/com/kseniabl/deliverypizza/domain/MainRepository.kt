package com.kseniabl.deliverypizza.domain

interface MainRepository {
    fun getBanners(): List<BannerImageModel>
}