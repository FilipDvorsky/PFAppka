package com.example.pfappka.ui.about

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pfappka.databinding.FragmentAboutBinding
import kotlin.math.pow
import kotlin.math.roundToInt


class AboutFragment : Fragment() {

    private val BUNDLE_KEY1 = "meno"
    private val BUNDLE_KEY2 = "vaha"
    private val BUNDLE_KEY3 = "vyska"
    private val BUNDLE_KEY4 = "bmi"

    private var nameValue: String? = null
    private var weightValue: String? = null
    private var heightValue: String? = null
    private var bmiValue: String? = null


    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val aboutViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        if (savedInstanceState == null){
            aboutViewModel.setStringsFromSharedPreferences(sharedPreferences)
            Log.d("TAG", "savedinstance = null")
        }else {
            nameValue = savedInstanceState.getString(BUNDLE_KEY1)
            weightValue = savedInstanceState.getString(BUNDLE_KEY2)
            heightValue = savedInstanceState.getString(BUNDLE_KEY3)
            bmiValue = savedInstanceState.getString(BUNDLE_KEY4)

            val name = savedInstanceState.getString(BUNDLE_KEY1)
            val weight = savedInstanceState.getString(BUNDLE_KEY2)
            val height = savedInstanceState.getString(BUNDLE_KEY3)
            val bmi  =savedInstanceState.getString(BUNDLE_KEY4)
            Log.d("TAG", "$name $weight $height $bmi")
            if (height != null && weight != null && name != null) {
                aboutViewModel.setValuesMinusBMI(name, height, weight)
            }
            if (bmi != null) {
                aboutViewModel.setBMI(bmi)
            }
        }

        aboutViewModel.name.observe(viewLifecycleOwner) { newText ->
            binding.editTextAbout.setText(newText)
        }
        aboutViewModel.weight.observe(viewLifecycleOwner) { newOtherText ->
            binding.editTextAbout2.setText(newOtherText)
        }
        aboutViewModel.height.observe(viewLifecycleOwner) { newAdditionalText ->
            binding.editTextAbout3.setText(newAdditionalText)
        }

        aboutViewModel.bmi.observe(viewLifecycleOwner) {
            binding.textView2.text = it
        }

        binding.button.setOnClickListener {
            val bmi = calcBMI(binding.editTextAbout2.text.toString(), binding.editTextAbout3.text.toString())
            aboutViewModel.setBMI(bmi)
            saveValuesToSharedPreferences()
        }
        //Log.d("TAG", binding.textView2.text.toString() )
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_KEY1, nameValue)
        outState.putString(BUNDLE_KEY2, weightValue)
        outState.putString(BUNDLE_KEY3, heightValue)
        outState.putString(BUNDLE_KEY4, bmiValue)

    }
    private fun saveValuesToSharedPreferences() {
        val sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val aboutText = binding.editTextAbout.text.toString()
        editor.putString("name", aboutText)

        val otherText = binding.editTextAbout2.text.toString()
        editor.putString("weight", otherText)

        val additionalText = binding.editTextAbout3.text.toString()
        editor.putString("height", additionalText)

        val bmi = binding.textView2.text.toString()
        editor.putString("bmi", bmi)

        //Log.d("TAG", "$aboutText $otherText $additionalText $bmi")
        editor.apply()
    }


    fun calcBMI(otherText: String,additionalText: String ): String {
        var bmi: String = ""
        Log.d("TAG", " $otherText $additionalText $bmi")

        if (otherText.toFloatOrNull() != null && additionalText.toFloatOrNull() != null) {
            if (otherText.toFloat() > 0F || additionalText.toFloat() > 0F) {
                val a = otherText.toFloat()
                val b = additionalText.toFloat().div(100)
                val c = (a.div(b.pow(2)).times(100)).roundToInt().div(100)
                bmi = c.toString()
            }
        }
        return bmi
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nameValue = binding.editTextAbout.text.toString()
        weightValue = binding.editTextAbout2.text.toString()
        heightValue = binding.editTextAbout3.text.toString()
        bmiValue = binding.textView2.text.toString()
        _binding = null
    }
}