package com.example.kotlinapp.iu.dialogfragmment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.kotlinapp.iu.detailplaylist.adaptre.DetailPlaylistAdapter
import com.example.kotlinapp.models.DetailVideo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.sheet_dialog_fragment.*


class DialogFragment(private val modelList:MutableList<DetailVideo>) : BottomSheetDialogFragment(){
    private lateinit var adapter: DetailPlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDialogAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setDialogAdapter()

    }

    fun setDialogAdapter() {
        adapter =
            DetailPlaylistAdapter { item: DetailVideo -> onItemClick(item)  }
        sheet_dialog_recycler.adapter = adapter
        adapter.initList(modelList)
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(sheet_dialog_recycler)
    }


    fun onItemClick(item: DetailVideo) {

    }


    companion object {
        val TAG = "ActionBottomDialog"
        fun newIns(list: MutableList<DetailVideo>): DialogFragment? {
            return DialogFragment(list)
        }
    }


}
