package com.sandeep.pixie.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.sandeep.pixie.R
import com.sandeep.pixie.databinding.DialogMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
@AndroidEntryPoint
class MainDialog: DialogFragment() {

    private lateinit var binding: DialogMainBinding
    private lateinit var url: String
    private lateinit var desc: String

    // Creating singleton object of the class
    companion object {
        private var instance: MainDialog? = null

        fun getInstance(): MainDialog {
            if (instance == null) {
                instance = MainDialog()
            }

            return instance!!
        }

        fun clearInstance() {
            instance = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DialogMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true

        binding.baseLayout.setOnClickListener { dismiss() }

        binding.dialogText.text = desc

        Glide.with(view)
            .load(url)
            .into(binding.dialogImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        clearInstance()
    }

    fun setContext(url: String, desc: String) {
        this.url = url
        this.desc = desc
    }
}