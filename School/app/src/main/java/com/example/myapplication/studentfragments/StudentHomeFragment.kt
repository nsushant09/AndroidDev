package com.example.myapplication.studentfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.adapter.HomeAssignmentAdapter
import com.example.myapplication.adapter.HomeNotificationAdapter
import com.example.myapplication.classpackage.NotificationHome
import com.example.myapplication.classpackage.Student
import com.example.myapplication.classpackage.TodayAssignmentHome
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
        val notificationAdapter = HomeNotificationAdapter(requireContext(),notification_list())
        home_noti_rv.adapter = notificationAdapter

        home_assign_rv.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL, false)
        val assignmentAdapter = HomeAssignmentAdapter(requireContext(),assignList())
        home_assign_rv.adapter = assignmentAdapter

    }

    fun notification_list() : ArrayList<NotificationHome>{
        val list = arrayListOf<NotificationHome>(
            NotificationHome("Reminder", "Your examination is starting soon on Feb 10th"),
            NotificationHome("Holiday Update", "On the Ocassion of Shiva Ratri the School Management has decided to give an Holiday"),
            NotificationHome("Deadline Approaching", "Your Science Project is due on 9th Feb"),
            NotificationHome("Event", "The School is organizing a sports festival from 21th Feb"),
            NotificationHome("Registration Open", "The Registration for Sports Festival is open from tomorrow"),
            NotificationHome("Examination Routine", "Your Examination Routine has been published.\nCheck your respective examination schedule and examination hall from Events."),
            NotificationHome("Notice", "The College Management has decided to continue to physical classes after your examination ends."),
            NotificationHome("Notification", "This is a random text just provided as notification description to fully test the scroll View"),
        )
        return list
    }

    fun assignList() : ArrayList<TodayAssignmentHome>{
        val list = arrayListOf<TodayAssignmentHome>(
            TodayAssignmentHome("English", "Prepare an essay of 300 words on a topic of your choice."),
            TodayAssignmentHome("Maths", "Solve Exercise 13.3, 13.4, 13.5 till 8th Feb"),
            TodayAssignmentHome("A.Maths", "Solve Exercise 9"),
            TodayAssignmentHome("Social","Draw a Map of Nepal and locate National Parks."),
            TodayAssignmentHome("Science", "Solves Question and Answer of Metals"),
            TodayAssignmentHome("Computer", "Prepare a Website that contains a table in it"),
            TodayAssignmentHome("H.P.E","Complete your pending works before examination")
        )
        return list
    }

}

