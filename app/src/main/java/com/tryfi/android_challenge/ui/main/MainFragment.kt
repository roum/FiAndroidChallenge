package com.tryfi.android_challenge.ui.main

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tryfi.android_challenge.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // views
        val playPauseButton = view.findViewById<ImageButton>(R.id.playPauseButton)
        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val animView = view.findViewById<ImageView>(R.id.animView)

        // play animView animation
        val animationDrawable = animView.drawable as AnimationDrawable
        animationDrawable.start()

        // set progress on seekBar (takes Int)
        seekBar.progress = 10

        // set speeds on spinner
        spinner.adapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.speeds,
            android.R.layout.simple_spinner_item
        )
        spinner.setSelection(1)
    }
}