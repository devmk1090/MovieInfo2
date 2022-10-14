package com.devkproject.movieinfo2.base

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkproject.movieinfo2.utils.ResourceProvider
import com.devkproject.movieinfo2.widget.Event
import com.devkproject.movieinfo2.widget.setEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel @Inject constructor(private val resourceProvider: ResourceProvider): ViewModel() {

    private val _toastMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    private val _backPressedEvent: MutableLiveData<Event<Any>> = MutableLiveData()
    val backPressedEvent: LiveData<Event<Any>> = _backPressedEvent

    private val _finishEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val finishEvent: LiveData<Event<Int>> = _finishEvent

    fun showToastMessage(message: String?) {
        message?.let {
            viewModelScope.launch {
                _toastMessage.setEvent(message)
            }
        }
    }

    fun finish() {
        finish(Activity.RESULT_CANCELED)
    }

    fun finish(result: Int) {
        viewModelScope.launch {
            _finishEvent.setEvent(result)
        }
    }

    fun backPressed() {
        viewModelScope.launch {
            _backPressedEvent.setEvent(Any())
        }
    }

    fun getString(@StringRes resId: Int) {
        resourceProvider.getString(resId)
    }

    fun getColor(@ColorRes colorId: Int) {
        resourceProvider.getColor(colorId)
    }
}