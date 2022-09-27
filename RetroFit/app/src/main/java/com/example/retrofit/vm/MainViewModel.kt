package com.example.retrofit.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.domain.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel(
//    val todoUseCase: TodoUseCase
) : ViewModel() , KoinComponent{

    val todoUseCase :TodoUseCase = get()
    private val _listOfData = MutableLiveData<List<HashMap<Todo, User>>>()
    val listOfData get() : LiveData<List<HashMap<Todo, User>>> = _listOfData

    private val _response = MutableLiveData<ApiPostResponse>()
    val response get() : LiveData<ApiPostResponse> = _response

    val disposable = CompositeDisposable()


    init {
        setPostData(Posts(101, 101, "SushantNeupane", "This post is made by sushant neupane to learn posting in network using rxjava and retrofit"))
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

    fun setPostData(post : Posts){
        disposable.add(
            todoUseCase.setPostData(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {   response ->
                        Log.i("TAG", "Response : ${response.id}")
                        _response.value = response
                    },{throwable ->
                    Log.i("TAG", "Throwable : $throwable")
                    }
                )
        )
    }

}