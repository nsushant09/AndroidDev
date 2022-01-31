package com.example.myapplication

import android.app.AppComponentFactory

class Student constructor(private var firstName: String,private var lastName : String,private var grade: Byte){
    companion object{
        private var studentCount : Int = 0
        var studentList = arrayListOf<Student>()
        init{
            defaultStudents()
        }

        fun defaultStudents(){
            val sushant = Student("Sushant","Neupane",10)
            val yogesh = Student("Yogesh","Bhatta",9)
            val utsab = Student("Utsab", "Sapkota",8)
            studentList.add(sushant)
            studentList.add(yogesh)
            studentList.add(utsab)
        }

    }
    init {
        studentCount++
    }
    private var userName : String = "${firstName.lowercase()}$studentCount"
    private var password : String = "${lastName}$grade"
    var studentId : Int = studentCount
    fun getFirstName() : String = firstName
    fun getLastName() : String = lastName
    fun getGrade() : Byte = grade
    fun getStudentCount() : Int = studentCount
    @JvmName("getStudentId1")
    fun getStudentId() : Int = studentId
    private fun getUserName() : String = userName

    private fun getPassword() : String = password
    private fun setPassword(newPassword: String){
        password = newPassword
    }

    object StaticMethods{

        fun addStudent(newStudent : Student){
            studentList.add(newStudent)
        }

        fun passwordAuthenticationSuccess(userNameInput : String , passwordInput : String) : Boolean{
            for (i in 0 until studentList.size){
                if(studentList[i].getUserName() == userNameInput){
                    if(studentList[i].getPassword() == passwordInput){
                        return true
                    }
                }
            }
            return false
        }

        fun loggedInIndex(userNameInput: String,passwordInput: String) : Int{
            for (i in 0 until studentList.size){
                if(studentList[i].getUserName() == userNameInput){
                    if(studentList[i].getPassword() == passwordInput){
                        return i
                    }
                }
            }
            return -1
        }
    }

    fun canChangePassword(oldPassword : String,newPassword : String,newPasswordEqual : String) : Boolean{
        if(oldPassword == getPassword()){
            if(newPassword == newPasswordEqual){
                setPassword(newPassword)
                return true
            }
        }
        return false
    }
}
