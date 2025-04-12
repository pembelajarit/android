package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class QuizFragment : Fragment(), View.OnClickListener {

    lateinit var tvCount: TextView
    lateinit var tvQuestion: TextView
    lateinit var btOption1: Button
    lateinit var btOption2: Button
    lateinit var btOption3: Button
    lateinit var btOption4: Button

    var currentQuestionIndex = 0
    val listOfQuestion = QuizHandler.getQuestion()
    lateinit var currentQuestion: Question
    var score = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCount = view.findViewById(R.id.tvCount)
        tvQuestion = view.findViewById(R.id.tvQuestion)
        btOption1 = view.findViewById(R.id.btOption1)
        btOption2 = view.findViewById(R.id.btOption2)
        btOption3 = view.findViewById(R.id.btOption3)
        btOption4 = view.findViewById(R.id.btOption4)

        btOption1.setOnClickListener(this)
        btOption2.setOnClickListener(this)
        btOption3.setOnClickListener(this)
        btOption4.setOnClickListener(this)

        displayQuestion()
    }

    fun displayQuestion() {
        if (currentQuestionIndex >= listOfQuestion.size) {
            //navigate to results
            val bundle = Bundle()
            bundle.putInt("result", score)
            findNavController().navigate(R.id.action_quizFragment_to_resultsFragment, bundle)
            return
        }
        currentQuestion = listOfQuestion[currentQuestionIndex++]
        tvCount.text = currentQuestionIndex.toString() + "/5"
        tvQuestion.text = currentQuestion.question
        btOption1.text = currentQuestion.Option1
        btOption2.text = currentQuestion.Option2
        btOption3.text = currentQuestion.Option3
        btOption4.text = currentQuestion.Option4
    }

    override fun onClick(v: View?) {
        //compare results
        val selectedOption = when (v?.id) {
            R.id.btOption1 -> currentQuestion.Option1
            R.id.btOption2 -> currentQuestion.Option2
            R.id.btOption3 -> currentQuestion.Option3
            else -> currentQuestion.Option4
        }

        if (selectedOption.equals(currentQuestion.answer)) {
            score++
        }
        displayQuestion()
    }

}