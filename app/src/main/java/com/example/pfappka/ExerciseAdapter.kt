package com.example.pfappka

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pfappka.db.Exercise

//https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow#6

class ExerciseAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    var cachedExercises : List<Exercise> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = Images()

        val textTitle1: TextView = itemView.findViewById(R.id.textTitle)
        val textDesc1: TextView = itemView.findViewById(R.id.textDesc)
        val imageView1: ImageView = itemView.findViewById(R.id.imageViewExercise)

        fun bind(exercise: Exercise, listener: OnItemClickListener){
            textTitle1.text = exercise.name
            textDesc1.text = exercise.desc

            imageView1.setImageResource(image.getImageIds(exercise.id))
            itemView.setOnClickListener{
                listener.onItemClick(exercise)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(
                R.layout.list_item,
                parent, false
            ).let {
                ExerciseViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(cachedExercises[position], listener)
    }

    override fun getItemCount() = cachedExercises.size
}
