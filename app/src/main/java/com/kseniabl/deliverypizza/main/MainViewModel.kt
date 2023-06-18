package com.kseniabl.deliverypizza.main

import androidx.lifecycle.ViewModel
import com.kseniabl.deliverypizza.domain.BannerImageModel
import com.kseniabl.deliverypizza.domain.BannersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bannersUseCases: BannersUseCases
): ViewModel() {

    private val _banners = MutableStateFlow<List<BannerImageModel>?>(null)
    val banners = _banners.asStateFlow()

    fun getBanners() {
        val imageModels = bannersUseCases.getBannersUseCase()
        _banners.value = imageModels
    }

}