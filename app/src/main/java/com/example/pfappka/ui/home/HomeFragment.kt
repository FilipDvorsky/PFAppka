package com.example.pfappka.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfappka.*
import com.example.pfappka.databinding.FragmentHomeBinding
import com.example.pfappka.db.Exercise

// recyclerview (Fragment) ↓
// https://www.youtube.com/watch?v=5mdV1hLbXzo
// safeargs z ↓
//https://www.youtube.com/watch?v=vtAHzpmibXo

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercisesViewModel: ExercisesViewModel = ExercisesViewModel.ExercisesViewModelFactory((requireActivity().application as ExercisesApplication).repository)
            .create(ExercisesViewModel::class.java)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val exerciseAdapter = ExerciseAdapter(object : OnItemClickListener {
            override fun onItemClick(exercise: Exercise) {
                val id = exercise.id
                val name = exercise.name
                val desc = exercise.desc
                val descLong = exercise.descLong
                val lok = exercise.lok
                val exercise1 = Exercise(id, lok, name, desc, descLong)
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationEvent(exercise1)
                findNavController().navigate(action)

            }
        })

        recyclerView.adapter = exerciseAdapter

        exercisesViewModel.exercises.observe(this) {
            exerciseAdapter.cachedExercises = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}