package com.zk.samplenewsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zk.samplenewsapp.databinding.ArticleFragmentBinding
import com.zk.samplenewsapp.model.Event
import com.zk.samplenewsapp.model.ViewEffect
import com.zk.samplenewsapp.model.ViewState
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
        binding.link.setOnClickListener {
            viewModel.event(Event.tapLink)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.event(Event.screenLoadEvent)
        viewModel.obtainState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

        viewModel.obtainAction.observe(viewLifecycleOwner, Observer {
            trigger(it)
        })
    }

    private fun renderState(state: ViewState) {
        with(binding) {
            title.text = state.title
            content.text = state.description
        }
    }

    private fun trigger(effect: ViewEffect) {
        when(effect) {
            is ViewEffect.OpenLinkExternally -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(effect.article?.url))
                startActivity(browserIntent)
            }
        }
    }
}