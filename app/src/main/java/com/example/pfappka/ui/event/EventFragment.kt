package com.example.pfappka.ui.event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pfappka.Images
import com.example.pfappka.databinding.FragmentEventBinding

// safeargs z ↓
//https://www.youtube.com/watch?v=vtAHzpmibXo

class EventFragment : Fragment() {
    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EventFragmentArgs>()
    val image = Images()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.currentExercise.id
        val sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val newValue1 = sharedPreferences.getString("name", "User")

        binding.imageViewEvent.setImageResource(image.getImageIds(id))
        binding.textEventTitleTitle.text = args.currentExercise.name
        binding.textEventDesc.text = args.currentExercise.desc
        binding.textEventDescLong.text = args.currentExercise.descLong

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra("title", "${args.currentExercise.name} (${newValue1})")
            intent.putExtra("description", "${args.currentExercise.desc} \n" +
                    "\n"+
                    "${args.currentExercise.descLong}")
            startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}