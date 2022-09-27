package com.example.retrofit.domain

import android.util.Log
import com.example.retrofit.data.TodoRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class TodoUseCase(
    val todoRepository: TodoRepository, val userUserUseCase: UserUseCase,val postUseCase: PostUseCase
) {

//    val todoRepository: TodoRepositoryImpl by inject(TodoRepositoryImpl::class.java)
//    val userUserUseCase: UserUseCase by inject(UserUseCase::class.java)
//    val postUseCase: PostUseCase by inject(PostUseCase::class.java)

    fun requestData(): Observable<List<Todo>> = todoRepository.requestData()

    fun setPostData(post: Posts) = postUseCase.setPostData(post)

    fun getCombinedUserAndTodoData(): Observable<List<HashMap<Todo, User>>> {
        return Observable.zip(
            requestData(),
            userUserUseCase.requestUserData()
        ) { todoList, userList ->
            returnListOfMapOfData(todoList, userList)
        }
    }

    fun returnListOfMapOfData(
        todolist: List<Todo>,
        userList: List<User>
    ): List<HashMap<Todo, User>> {
        val finalList = ArrayList<HashMap<Todo, User>>()

        for (i in 0 until userList.size) {
            val todoIndex = isSameIdBinary(userList[i].id, todolist)
            if (todoIndex != -1) {
                finalList.add(hashMapOf(todolist[todoIndex] to userList[i]))
            }
        }

        Log.i("TodoUseCase", "Length of finalList : ${finalList.size}")
        return finalList.toList()
    }

    fun isSameIdBinary(value: Int, numbers: List<Todo>): Int {
        if (numbers.isEmpty()) return -1

        var firstIndex = 0
        var lastIndex = numbers.size - 1
        var middleIndex = (firstIndex + lastIndex) / 2

        while (firstIndex <= lastIndex) {

            if (value == numbers[middleIndex].id) {
                return middleIndex
            }

            if (value < numbers[middleIndex].id!!) {
                lastIndex = middleIndex - 1
                middleIndex = (firstIndex + lastIndex) / 2
            } else {
                firstIndex = middleIndex + 1
                middleIndex = (firstIndex + lastIndex) / 2
            }

        }

        return -1
    }
}