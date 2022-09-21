package com.example.retrofit.domain

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import kotlin.math.min

class TodoUseCase(val todoRepository: TodoRepository, val userUserUseCase: UserUseCase){

    fun requestData() : Observable<List<Todo>> = todoRepository.requestData()

    fun getCombinedUserAndTodoData(): Observable<List<HashMap<Todo, User>>> {
         return Observable.zip(requestData(), userUserUseCase.requestUserData()) { todoList, userList ->
             returnListOfMapOfData(todoList, userList)
         }
    }

    fun returnListOfMapOfData(todolist : List<Todo>, userList : List<User>) : List<HashMap<Todo, User>> {
        val finalList = ArrayList<HashMap<Todo, User>>()

        for(i in 0 until userList.size){
            for(j in 0 until todolist.size){
                if(todolist[j].id == userList[i].id){
                    finalList.add(hashMapOf(todolist[j] to userList[i]))
                    break;
                }
            }
        }

        Log.i("TodoUseCase", "Length of finalList : ${finalList.size}")
        return finalList.toList()
    }
}