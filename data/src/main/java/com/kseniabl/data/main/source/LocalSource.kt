package com.kseniabl.data.main.source

import com.kseniabl.data.entity.BannerModel

interface LocalSource {
    fun getBanners(): List<BannerModel>
}
