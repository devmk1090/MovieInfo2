package com.devkproject.movieinfo2.widget

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

//EventWrapper Class
open class Event<out T>(private val content: T) {
    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) { //이벤트가 이미 처리되었다면
            null //null을 반환하고,
        } else { //그렇지 않다면
            hasBeenHandled = true //이벤트가 처리되었다고 표시한 후에
            content //값을 반환한다.
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     * 이벤트의 처리 여부에 상관없이 값을 반환한다.
     */
    fun peekContent(): T = content
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (content is Any && other is Any) return true
        if (javaClass != other?.javaClass) return false
        other as Event<*>
        if (content != other.content) return false
        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + hasBeenHandled.hashCode()
        return result
    }
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

@MainThread
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): EventObserver<T> {
    val wrappedObserver = EventObserver<T> { t ->
        onChanged(t)
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T> MutableLiveData<Event<T>>.setEvent(value: T) {
    if (value !is Event<*>) {
        setValue(Event(value))
    } else {
        throw IllegalArgumentException("Please remove event wrapping.")
    }
}