package com.zk.samplenewsapp.views

import android.os.Build
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.model.Article

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        // Retrieve the correct Item instance, using the ID provided in the Intent


    }



    /**
     * Try and add a [Transition.TransitionListener] to the entering shared element
     * [Transition]. We do this so that we can load the full-size image after the transition
     * has completed.
     *
     * @return true if we were successful in adding a listener to the enter transition
     */
    @RequiresApi(21)
    fun addTransitionListener(): Boolean {
        val transition =
            window.sharedElementEnterTransition
        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    //loadFullSizeImage();

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                    //loadFullSizeImage();
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }

    companion object {
        // Extra name for the ID parameter
        const val EXTRA_PARAM_ID = "detail:_id"

        // View name of the header image. Used for activity scene transitions
        const val VIEW_NAME_HEADER_IMAGE = "detail:header:image"
    }
}