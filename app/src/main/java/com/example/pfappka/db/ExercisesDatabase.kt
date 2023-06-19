package com.example.pfappka.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow#6


@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExercisesDatabase : RoomDatabase() {

    abstract fun exercisesDao(): ExercisesDao

    companion object {
        @Volatile
        private var INSTANCE: ExercisesDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExercisesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExercisesDatabase::class.java,
                    "exercises_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let {
                                scope.launch {
                                    it.exercisesDao().deleteAll()
                                    it.exercisesDao().insert(Exercise(1,"English","Hands/Biceps Exercise","Simple exercise for arms/biceps","15 REPS OF SQUATS\n" +
                                            "15 REPS OF LUNGES\n" +
                                            "20 REPS OF DUMBBELL BICEP CURLS\n" +
                                            "REPEAT 7 TIMES."))
                                    it.exercisesDao().insert(Exercise(2,"English","Chest/ Upper Body","Exercises for Upper Body","DUMBBELL CHEST PRESS 20X\n" +
                                            "BENCH DUMBBELL FLYES 20X\n" +
                                            "REPEAT 3 TIMES."))
                                    it.exercisesDao().insert(Exercise(3,"English","Legs Exercise","Easy exercises for legs","SQUATS 30X\n" +
                                            "5-MINUTE REST\n" +
                                            "BULGARIAN SPLIT SQUATS 15X\n" +
                                            "REPEAT 2 TIMES."))
                                    it.exercisesDao().insert(Exercise(4,"English","Cardio Exercise","30 minute cardio exercise","RUNNING 15 MINUTES\n" +
                                            "5-MINUTE REST\n" +
                                            "RUNNING 10 MINUTES."))
                                    it.exercisesDao().insert(Exercise(5,"English","MuscleMastery Kit","The set of exercises for the MuscleMastery Kit is focused on comprehensive body strengthening. It includes various exercises that target different muscle groups. Exercises such as squats with a barbell, bench press, and single-arm dumbbell lunges are aimed at strengthening the lower body, including the legs, glutes, and hamstrings. Sit-ups strengthen the abdominal muscles, while deadlifts target the muscles of the back, glutes, and hamstrings.","SQUAT WITH A BARBELL - 15X\n" +
                                            "BENCH PRESS - 15X\n" +
                                            "SINGLE-ARM DUMBBELL LUNGE - 15X ON EACH SIDE\n" +
                                            "SIT-UPS - 15X\n" +
                                            "DEADLIFT - 15X\n" +
                                            "SHOULDER PRESS - 15X\n" +
                                            "BARBELL ROW - 15X\n" +
                                            "CABLE FRONT RAISE - 15X\n" +
                                            "PULL-UPS - 15X\n" +
                                            "PLANK WITH ARM SUPPORT - 15 SECONDS"))
                                    it.exercisesDao().insert(Exercise(6,"slovenčina","Ruky/ Biceps Cvičenie","Jednoduché cvičenie zamerané na ruky)","KLIKY 15x\n" +
                                            "VZPAŽOVANIE 15x\n" +
                                            "BICEPS S JEDNORUČKAMI 20x\n" +
                                            "OPAKUJTE 7x"))
                                    it.exercisesDao().insert(Exercise(7,"slovenčina","Hruď/ Prsné svaly" ,"Cviky na spevnenie hornej časti tela","TLAKY S JEDNORUČKAMI 20x\n" +
                                            "ROZPAŽOVANIE NA LAVIČKE 20x\n" +
                                            "OPAKUJTE 3x "))
                                    it.exercisesDao().insert(Exercise(8,"slovenčina","Cvičenie Nôh","Jednoduché cviky na nohy","DREPY 30x\n" +
                                            "PAUZA 5 minút\n" +
                                            "BULHARSKÉ DREPY 15x\n" +
                                            "OPAKUJTE 2x"))
                                    it.exercisesDao().insert(Exercise(9,"slovenčina","Kardio Cvičenie","30 minutove cvičenie zamerané na kardio","BEH 15 minút\n" +
                                            "PAUZA 5 minút\n" +
                                            "BEH 10 minút"))
                                    it.exercisesDao().insert(Exercise(10,"slovenčina","MuscleMastery Kit","Sada cvikov pre MuscleMastery Kit je zameraná na komplexné posilňovanie tela. Obsahuje rôzne cviky, ktoré posilňujú rôzne svalové skupiny. Cviky ako drep s činkou, bench press a predkop s jednoručkou sú zamerané na posilnenie dolnej časti tela, vrátane nôh, zadku a hamstringov. Sedy-lehy posilňujú brušné svaly, zatiaľ čo mrtvý ťah posilňuje svaly chrbta, zadku a hamstringy.","DREP S ČINKOU - 15X\n" +
                                            "BENCH PRESS - 15X\n" +
                                            "PREDKOP S JEDNORUČKOU - 15X NA KAŽDÚ STRANU\n" +
                                            "SEDY-LEHY - 15X\n" +
                                            "MRTVÝ ŤAH - 15X\n" +
                                            "RAMENNÝ TLAČ - 15X\n" +
                                            "PREKOP S ČINKOU - 15X\n" +
                                            "PREDKOP S KLADKOU - 15X\n" +
                                            "ZHYBY NA HRAZDE - 15X\n" +
                                            "PLANK S PODPIERKOU RÚK - 15 SEKÚND"))
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
/*
                                    it.exercisesDao().deleteAll()
                                    it.exercisesDao().insert(Exercise(1,"English","Hands","just hands bro nothing else"))
                                    it.exercisesDao().insert(Exercise(2,"English","Chest","just legs bruh"))
                                    it.exercisesDao().insert(Exercise(3,"English","Legs","just legs bro"))
                                    it.exercisesDao().insert(Exercise(4,"slovenčina","Ruky","Len cvic ruky mojko"))
                                    it.exercisesDao().insert(Exercise(5,"slovenčina","Hrud","Makaj na hrudi"))
                                    it.exercisesDao().insert(Exercise(6,"slovenčina","Nohy","Nekecaj a drepuj"))
*/