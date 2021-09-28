package com.tryfi.android_challenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // views
        val playButton = view.findViewById<ImageButton>(R.id.playButton)
        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        val animView = view.findViewById<LottieAnimationView>(R.id.animView)

        // play animView animation
        animView.playAnimation()

        // set progress on seekBar (takes Int)
//        seekBar.progress = 10

        // set progress on animView (takes Float from 0f to 1f)
//        animView.progress = 0.1f
    }
}