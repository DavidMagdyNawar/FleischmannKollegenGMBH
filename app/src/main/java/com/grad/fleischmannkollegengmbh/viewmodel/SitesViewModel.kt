package com.grad.fleischmannkollegengmbh.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class SitesViewModel() : ViewModel() {



    private val REF: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("/sites")

    private val liveData: FirebaseQueryLiveData = FirebaseQueryLiveData(REF)

    fun getDataSnapshotLiveData(): LiveData<DataSnapshot?> {
        return liveData
    }


}
