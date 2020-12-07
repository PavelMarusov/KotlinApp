package com.example.kotlinapp.iu.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.kotlinapp.R
import com.example.kotlinapp.iu.detailplaylist.adaptre.DetailPlaylistAdapter
import com.example.kotlinapp.models.PlaylistItems
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.sheet_dialog_fragment.*


class DialogFragment(private val modelList:MutableList<PlaylistItems>) : BottomSheetDialogFragment(){
    private lateinit var adapter: DetailPlaylistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sheet_dialog_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogAdapter()

    }
    private fun setDialogAdapter() {
        adapter =
            DetailPlaylistAdapter { item: PlaylistItems -> onItemClick(item)  }
        sheet_dialog_recycler.adapter = adapter
        adapter.initList(modelList)
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(sheet_dialog_recycler)
    }


   private fun onItemClick(item: PlaylistItems) {

    }


    companion object {
        val TAG = "ActionBottomDialog"
        fun newIns(list: MutableList<PlaylistItems>): DialogFragment? {
            return DialogFragment(list)
        }
    }


}
