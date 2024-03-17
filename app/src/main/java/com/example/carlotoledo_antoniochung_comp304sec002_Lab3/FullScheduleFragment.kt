package com.example.carlotoledo_antoniochung_comp304sec002_Lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FragmentFullScheduleBinding
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModel
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModelFactory
import kotlinx.coroutines.launch

class FullScheduleFragment: Fragment() {

    private var _binding: FragmentFullScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: AirportScheduleViewModel by activityViewModels {
        AirportScheduleViewModelFactory(
            (activity?.application as AirportScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val airportScheduleAdapter = AirportScheduleListAdapter {
            val action = FullScheduleFragmentDirections
                .actionFullScheduleFragmentToFlightDetailsFragment(
                    flightID = it.id
                )
            view.findNavController().navigate(action)
        }

        recyclerView.adapter = airportScheduleAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                airportScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}