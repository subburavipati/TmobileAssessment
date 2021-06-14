package com.tmobile.subbu.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tmobile.subbu.databinding.FragmentCardsBinding
import com.tmobile.subbu.view.adapter.CardsAdapter
import com.tmobile.subbu.viewmodel.CardsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cards.view.*

@AndroidEntryPoint
class CardsFragment: Fragment() {

    private val viewModel: CardsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CardsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardsAdapter()
        view.cards.adapter = adapter
        viewModel.apply {
            cards.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
        }
    }
}