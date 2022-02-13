package com.example.myapplication.studentfragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Login
import com.example.myapplication.LoginAsStudent
import com.example.myapplication.R
import com.example.myapplication.Student
import com.example.myapplication.adapter.HomeAssignmentAdapter
import com.example.myapplication.adapter.HomeNotificationAdapter
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

//        homeSetFullName.text = "${studentObj.getFirstName()} ${studentObj.getLastName()}"

        home_noti_rv.layoutManager = LinearLayoutManager(context)
        val notificationAdapter = HomeNotificationAdapter(requireContext(),noti_title_list(),noti_desc_list())
        home_noti_rv.adapter = notificationAdapter

        home_assign_rv.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL, false)
        val assignmentAdapter = HomeAssignmentAdapter(requireContext(),assign_sub_list(),assing_desc_list())
        home_assign_rv.adapter = assignmentAdapter

    }

    fun noti_title_list() : ArrayList<String>{
        val list = arrayListOf<String>(

            "Reminder",
            "Holiday Update",
            "Deadline Approaching",
            "Event",
            "Registration Open",
            "Examination Routine",
            "Notice",
            "Notification",
            "Notification",
            "Notification"

        )
        return list
    }

    fun noti_desc_list() : ArrayList<String>{
        val list = arrayListOf<String>(

            "Your examination is starting soon on 10th Feb",
            "On the Ocassion of Shiva Ratri the School Management has decided to give an Holiday",
            "Your Science Project is due on 9th Feb",
            "The School is organizing a sports festival from 21th Feb",
            "The Registration for Sports Festival is open from tomorrow",
            "Your Examination Routine has been published.\nCheck your respective examination schedule and examination hall from Events.",
            "The College Management has decided to continue to physical classes after your examination ends.",
            "This is a random text just provided as notification description to fully test the scroll View",
            "This is a random text just provided as notification description to fully test the scroll View",
            "This is a random text just provided as notification description to fully test the scroll View"

        )
        return list
    }

    fun assign_sub_list() : ArrayList<String>{
        val list = arrayListOf<String>(

            "English",
            "Maths",
            "A.Maths",
            "Social",
            "Science",
            "Computer",
            "H.P.E",

        )
        return list
    }

    fun assing_desc_list() : ArrayList<String>{
        val list = arrayListOf<String>(

            "Prepare an essay of 300 words on a topic of your choice.",
            "Solve Exercise 13.3, 13.4, 13.5 till 8th Feb",
            "Solve Exercise 9",
            "Draw a Map of Nepal and location National Parks.",
            "Solve Question and Answer of Metals",
            "Prepare a Website that contains a table in it ",
            "Complete your pending works before examination"

        )
        return list
    }
}

