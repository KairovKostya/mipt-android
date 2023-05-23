package com.phystech.edu.mipt.screen.mainMenu


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phystech.edu.mipt.data.RemoteRestaurant
import com.phystech.edu.mipt.data.RestaurantRepository
import com.phystech.edu.mipt.data.mapToNearestRestaurantEntity
import com.phystech.edu.mipt.data.mapToPopularRestaurantEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RestaurantState(
    val nearestRestaurants: List<RemoteRestaurant> = emptyList(),
    val popularRestaurants: List<RemoteRestaurant> = emptyList()
)


@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
) :
    ViewModel() {

    private val _viewState: MutableLiveData<RestaurantState> = MutableLiveData(RestaurantState())
    val viewState: LiveData<RestaurantState> = _viewState

    init {
        viewModelScope.launch(Dispatchers.Default) {
            restaurantRepository.fetchCatalog()
                .collectLatest { response ->
                    _viewState.postValue(
                        _viewState.value?.copy(
                            nearestRestaurants = response.nearest,
                            popularRestaurants = response.popular
                        )
                    )
                }
        }
    }
}
