package org.github.kudrix.famous.theme.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

public abstract class BaseViewModel<State : Any, Actions, Event>(initialState: State) : ViewModel() {
    private val _viewState = MutableStateFlow(initialState)
    private val _viewActions = MutableSharedFlow<Actions?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    public fun viewState(): StateFlow<State> = _viewState.asStateFlow()
    public fun viewActions(): SharedFlow<Actions?> = _viewActions.asSharedFlow()

    protected var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    protected var viewActions: Actions?
        get() = _viewActions.replayCache.last()
        set(value){
            _viewActions.tryEmit(value)
        }

    public abstract fun obtainEvent(viewEvent: Event)
    public fun clearAction(){
        viewActions = null
    }
}