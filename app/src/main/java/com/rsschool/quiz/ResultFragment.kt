package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.databinding.FragmentResultBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment() {

    private var launcher: Launcher? = null
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Launcher)
            launcher = context
    }

    private fun counting(): Int {
        return QuestionBase.questions.fold(0) { sum, question ->
            question.answers[question.userAnswer].second + sum
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val counting = counting()
        binding.apply {
            resultText.text = resources.getQuantityString(R.plurals.count_id, counting, counting)

            btnShare.setOnClickListener {
                launcher?.shareResult(counting)
            }

            btnRestart.setOnClickListener {
                QuestionBase.clearResult()
                launcher?.launchQuestionFragment(0)
            }

            btnExit.setOnClickListener {
                exitProcess(0)
            }
        }
    }
}