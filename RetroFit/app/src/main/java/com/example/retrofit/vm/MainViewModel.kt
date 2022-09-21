package com.example.retrofit.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.TodoUseCase
import com.example.retrofit.domain.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    val todoUseCase: TodoUseCase
) : ViewModel() {

    private val _listOfData = MutableLiveData<List<HashMap<Todo, User>>>()
    val listOfData get() : LiveData<List<HashMap<Todo, User>>> = _listOfData
    val disposable = CompositeDisposable()


    init {
        loadRequestedTodoData()
        Log.i("TAG", "Loaded ViewModel")
    }

    fun loadRequestedTodoData(){
        disposable.add(
            todoUseCase.getCombinedUserAndTodoData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseList ->
                    _listOfData.value = responseList
                }, { throwable ->
                    Log.i("TAG", "Throwable : $throwable")
                })
        )
    }

}