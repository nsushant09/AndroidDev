package com.example.myapplication.studentfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.MenuRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_student_menu.*


class StudentMenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_rv.layoutManager = LinearLayoutManager(context)
        val menuRecyclerAdapter  = MenuRecyclerAdapter(requireContext(),imageArray(),titleList(),descriptionList())
        menu_rv.adapter = menuRecyclerAdapter
    }

    fun imageArray() : ArrayList<Int>{
        val imageList = arrayListOf<Int>(

            R.drawable.google_classroom,
            R.drawable.exam,
            R.drawable.assignment,
            R.drawable.attendance,
            R.drawable.homework,
            R.drawable.report_card,
            R.drawable.books,
            R.drawable.event,
        )
        return imageList
    }

    fun titleList() : ArrayList<String>{
        val titleList = arrayListOf<String>(

            "Online Class",
            "E-Classroom",
            "Assignments",
            "Attendance",
            "Homework",
            "Report Card",
            "Reading Materials",
            "Events"

        )
        return titleList
    }

    fun descriptionList() : ArrayList<String>{
        val descriptionList = arrayListOf<String>(

            "View Active Online Classrooms",
            "Multiple Choice Questions",
            "View & Submit Assignment",
            "Monthly & Aggregate Report",
            "Daily Homework",
            "View Exam Results",
            "View Reading Material and Resources",
            "Program and Activities"

        )
        return descriptionList
    }
}