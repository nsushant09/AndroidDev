package com.example.contactappusedfirebase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappusedfirebase.data.Contact
import com.example.contactappusedfirebase.data.NODE_CONTACTS
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class ContactViewModel : ViewModel() {

    //create object for database connection
    //create object for database conncetion and call .getInstance method and pass the url of the firebase if the server is outside of uscentral then pass the ENTITY name in get reference
    private val dbcontacts = FirebaseDatabase.getInstance("https://contactlistproject-340a9-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(NODE_CONTACTS)



    //created a list named _result which stores MutableLIveData of type exception
    //this is to store if there are any exception while writing the data to the firebase
    private val _result = MutableLiveData<Exception?>()
    //the _result is then passed to result
    val result : LiveData<Exception?> get() = _result

    //similarly this list _contact is to store contact object
    private val _contact = MutableLiveData<Contact>()
    //then the contact object is passed to the contast list
    val contact : LiveData<Contact> get() = _contact

    //function add contact to add the contact to the firebase which takes Contact object as parameter
    fun addContact(contact : Contact){
        //since the contace id is set by the firebase itself it is set to firebaseobject.push().key
        //where key is different for each object and push() as name suggest pushes the key to id
        contact.id = dbcontacts.push().key

        //below firebase object call .child methods where primary key is passed whose value is set to contact i.e object of Contact
        //after setting the value addOncompleteListener is called to check if the process is successfull or not
        dbcontacts.child(contact.id!!).setValue(contact).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }


    //below an reference of ChildEventListener is called which implement the required methods
    private val childEventListener = object : ChildEventListener{

        //added to list from database
        //snapshot is the object in database
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            //a reference of Contact is created
            //then the snapshot is convereted to Contact class
            val contact = snapshot.getValue(Contact::class.java)

            //below the id of the contact object is set to snapshot key
            contact?.id = snapshot.key
            //the list _contacts value is set to contact object which is not null
            _contact.value = contact!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            val contact = snapshot.getValue(Contact::class.java)
            contact?.id = snapshot.key
            _contact.value = contact!!

        }

        override fun onChildRemoved(snapshot: DataSnapshot) {

            val contact = snapshot.getValue(Contact::class.java)
            contact?.id = snapshot.key
            contact?.isDeleted = true
            _contact.value = contact!!

        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    //to get real time updates
    fun getRealTimeUpdate(){
        //firebase instance uses .addChildEventListener and passes the previously instantiated childEventListener object
        dbcontacts.addChildEventListener(childEventListener)
    }

    fun updateContact(contact: Contact){
        dbcontacts.child(contact.id!!).setValue(contact).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }

    fun deleteContact(contact : Contact){
        dbcontacts.child(contact.id!!).setValue(null).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }

    //this is a good practise to clear
    override fun onCleared() {
        super.onCleared()
        dbcontacts.removeEventListener(childEventListener)
    }

}

