package com.example.myapplication.studentfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Login
import com.example.myapplication.LoginAsStudent
import com.example.myapplication.R
import com.example.myapplication.Student
import kotlinx.android.synthetic.main.fragment_student_home.*

class StudentHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accountIndex = LoginAsStudent.StaticMethods.returnAccountIndex()
        val studentObj : Student = Student.studentList[accountIndex]

        homeSetFullName.text = "${studentObj.getFirstName()} ${studentObj.getLastName()}"

        calenderCard.setOnClickListener{
            val intent = Intent(activity,Login::class.java)
            startActivity(intent)
        }

        notifcationCard.setOnClickListener{
            val intent = Intent(activity,Login::class.java)
            startActivity(intent)
        }

    }
}

