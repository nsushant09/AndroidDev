package com.example.contactappusedfirebase.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappusedfirebase.R
import com.example.contactappusedfirebase.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    //fragmentcontactbinding is the name of the layout i.e fragmetncontact.xml
    private var _binding : FragmentContactsBinding ?= null
    //binding is set to _binding that is not null
    private val binding get() = _binding!!

    //created and adapter of ContactAdapter
    private val  adapter = ContactAdapter()

    //created an object of ContactViewModel
    private  lateinit var viewModel : ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding this Fragment to xml file fragment_contact.xml
        _binding = FragmentContactsBinding.inflate(inflater,container,false)

        //initialized the viewModel to COntactViewModel
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //rvcontact is the id of recycler view in fragment contacts
        binding.rvContacts.adapter = adapter

        //when floating button with + is cliked it shows the child Fragment which is AddContactDialogFragment
        //more to learn about dialog
        binding.addButton.setOnClickListener{
            AddContactDialogFragment().show(childFragmentManager,"")
        }

        //viewModel is object of ContactViewModel class whose contact list is being observed
        //viewLifecycleOwner , Observer are default parameters for now

        viewModel.contact.observe(viewLifecycleOwner, Observer {
            //adapter is of recycler view which adds the contact item to mutable list in recycler view adapter
            adapter.addContact(it)
        })

        //getRealTimeUpdate() of ContactViewModel is being called to notify changes
        viewModel.getRealTimeUpdate()

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvContacts)

    }

    //first parameter is for vertical and second one is for horizontal
    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val position = viewHolder.adapterPosition
            val currentContact = adapter.contacts[position]
            when(direction){
                ItemTouchHelper.RIGHT -> {
                    UpdateContactDialogFragment(currentContact).show(childFragmentManager,"")
                }
                ItemTouchHelper.LEFT -> {
                    AlertDialog.Builder(requireContext()).also {
                        it.setTitle("Are you sure you want to delete this contact?")
                        it.setPositiveButton("Yes"){dialog, which ->
                            viewModel.deleteContact(currentContact)
                            binding.rvContacts.adapter?.notifyItemRemoved(position)
                        }
                    }.create().show()
                }
            }
            binding.rvContacts.adapter?.notifyDataSetChanged()

        }
    }

    //thsi is a good practise to destroy
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}