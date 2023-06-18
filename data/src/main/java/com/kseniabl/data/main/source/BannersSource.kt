package com.kseniabl.data.main.source

import com.kseniabl.data.R
import com.kseniabl.data.entity.BannerModel

class BannersSource: LocalSource {

    // In real app it should be also api call but here we store banners locally

    private val bannersList = listOf(
        BannerModel(0, R.drawable.beer_ad1),
        BannerModel(1, R.drawable.beer_ad2),
        BannerModel(2, R.drawable.beer_ad3),
    )

    override fun getBanners() = bannersList
}