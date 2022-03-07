package com.tryfi.android_challenge.ui.main

import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tryfi.android_challenge.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        job = Job()
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

        val animationDrawable = animView.drawable as AnimationDrawable

        // TODO: play animation (using AnimationDrawable)
//         animationDrawable.start()

        // TODO: play animation (using ValueAnimator)
        val animator = ObjectAnimator.ofInt(animationDrawable.numberOfFrames)
        animator.duration = 960L
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.addUpdateListener {
            animationDrawable.selectDrawable(it.animatedValue as Int)
        }
//        animator.start()

        // TODO: play animation (using a coroutine)
        launch(Dispatchers.Main) {
            var lastFrameIndex = -1
            while (false) { // flip to true to start
                val nextFrameIndex = (lastFrameIndex + 1)
                    .takeIf { it < animationDrawable.numberOfFrames } ?: 0
                animView.setImageDrawable(animationDrawable.getFrame(nextFrameIndex))
                lastFrameIndex = nextFrameIndex
                delay(40L)
            }
        }

        // set progress on seekBar (takes Int)
        seekBar.progress = 10
        // set max on seekBar (takes Int)
        seekBar.max = 100

        // set speed values on spinner
        spinner.adapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.speeds,
            android.R.layout.simple_spinner_item
        )
        spinner.setSelection(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }
}