package com.zk.samplenewsapp.views.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.zk.samplenewsapp.viewModel.ArticleViewModel
import com.zk.samplenewsapp.databinding.ArticleFragmentBinding
import com.zk.samplenewsapp.model.Event
import com.zk.samplenewsapp.model.ViewEffect
import com.zk.samplenewsapp.model.DetailViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private val viewModel by sharedViewModel<ArticleViewModel>()

    private lateinit var binding: ArticleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleFragmentBinding.inflate(inflater, container, false)
        setClickEvents()
        return binding.root
    }

    private fun setClickEvents() {
        binding.link.setOnClickListener {
            viewModel.event(Event.ClickLink)
        }

        binding.fab.setOnClickListener {
            viewModel.event(Event.AddToFavouritesEvent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.event(Event.ScreenLoadEvent)
        observeViewState()
        observeViewEffects()
    }

    private fun observeViewEffects() {
        viewModel.obtainAction.observe(viewLifecycleOwner, Observer {
            trigger(it)
        })
    }

    private fun observeViewState() {
        viewModel.obtainStateDetail.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }

    private fun renderState(stateDetail: DetailViewState) {
        with(binding) {
            title.text = stateDetail.title
            content.text = stateDetail.description
            Picasso.get().load(stateDetail.backDrop).into(backdrop)
        }
    }

    private fun trigger(effect: ViewEffect) {
        when(effect) {
            is ViewEffect.OpenLinkExternally -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(effect.article?.url))
                startActivity(browserIntent)
            }
            is ViewEffect.ShowSnackBar -> {
                Snackbar.make(binding.coordinator, getString(effect.messageResource), Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}