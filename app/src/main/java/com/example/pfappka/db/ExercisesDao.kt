package com.example.pfappka.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Query("SELECT * FROM exercises WHERE lok=:localization")
    fun getAllExercises(localization: String) : Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE lok=:localization AND id=:iden")
    fun getExercise(localization: String, iden: Int) : Flow<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)

    @Query("DELETE FROM exercises WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()


}