package com.example.carlotoledo_antoniochung_comp304sec002_Lab3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FragmentFlightDetailsBinding
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModel
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModelFactory
import kotlinx.coroutines.launch

class FlightDetailsFragment: Fragment() {

    companion object {
        var FLIGHT_ID = "flightID"
    }

    private var _binding: FragmentFlightDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var flightID: String

    private val viewModel: AirportScheduleViewModel by activityViewModels {
        AirportScheduleViewModelFactory(
            (activity?.application as AirportScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            flightID = it.getString(FLIGHT_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlightDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airportScheduleAdapter = AirportScheduleListAdapter {}
        // by passing in the stop name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = airportScheduleAdapter

        lifecycle.coroutineScope.launch {
            viewModel.scheduleForStopName(flightID).collect() {
                airportScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
