package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MenuFragment : Fragment() {
    lateinit var buttonStart: Button
    lateinit var buttonRules: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonStart  =  view.findViewById(R.id.btStartQuiz)
        buttonRules  =  view.findViewById(R.id.btRules)
        buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_quizFragment)
        }
        buttonRules.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_rulesFragment)
        }
    }
}
