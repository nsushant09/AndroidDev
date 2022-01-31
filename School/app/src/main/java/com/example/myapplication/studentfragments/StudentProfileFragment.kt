package com.example.myapplication.studentfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.LoginAsStudent
import com.example.myapplication.R
import com.example.myapplication.Student
import kotlinx.android.synthetic.main.fragment_student_profile.*


class StudentProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accountIndex = LoginAsStudent.StaticMethods.returnAccountIndex()
        val studentObj : Student = Student.studentList[accountIndex]

        studentFirstName.text = studentObj.getFirstName()
        studentLastName.text = studentObj.getLastName()
        studentGrade.text = studentObj.getGrade().toString()
        studentId.text = studentObj.getStudentId().toString()

        btnGotoLogin.setOnClickListener{
            val backButtonIntent = Intent(activity,LoginAsStudent::class.java)
            startActivity(backButtonIntent)

        }

    }
    }

