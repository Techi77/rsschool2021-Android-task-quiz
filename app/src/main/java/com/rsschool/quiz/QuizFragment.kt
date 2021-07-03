package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var launcher: Launcher? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val questionNumber = arguments?.getInt(QUESTION_KEY) ?: 0
        val question1 = QuestionBase.questions[questionNumber]
        requireActivity().setTheme(question1.theme)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val QUESTION_KEY = "question"
        fun newInstance(questionNumber: Int): Fragment {
            val fragment: Fragment = QuizFragment()
            val bundle = Bundle(1).apply {
                putInt(QUESTION_KEY, questionNumber)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Launcher) {
            launcher = context
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionNumber = arguments?.getInt(QUESTION_KEY) ?: 0
        val question1 = QuestionBase.questions[questionNumber]
        binding.apply {
            question.text = question1.textQuestion
            optionOne.text = question1.answers[0].first
            optionTwo.text = question1.answers[1].first
            optionThree.text = question1.answers[2].first
            optionFour.text = question1.answers[3].first
            optionFive.text = question1.answers[4].first
            nextButton.text =
                if (questionNumber == 4) context?.getString(R.string.submit) else context?.getString(
                    R.string.next
                )
            toolbar.title = "Question ${questionNumber.inc()}"
            toolbar.navigationIcon =
                if (questionNumber == 0) null else context?.getDrawable(R.drawable.ic_baseline_chevron_left_24)
            previousButton.visibility = if (questionNumber == 0) View.GONE else View.VISIBLE
            if (!optionOne.isChecked && !optionTwo.isChecked && !optionThree.isChecked && !optionFour.isChecked && !optionFive.isChecked) nextButton.isEnabled =
                question1.userAnswer != -1

            when (question1.userAnswer) {
                0 -> optionOne.isChecked = true
                1 -> optionTwo.isChecked = true
                2 -> optionThree.isChecked = true
                3 -> optionFour.isChecked = true
                4 -> optionFive.isChecked = true
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    optionOne.id -> {
                        question1.userAnswer = 0
                        nextButton.isEnabled = true
                    }
                    optionTwo.id -> {
                        question1.userAnswer = 1
                        nextButton.isEnabled = true
                    }
                    optionThree.id -> {
                        question1.userAnswer = 2
                        nextButton.isEnabled = true
                    }
                    optionFour.id -> {
                        question1.userAnswer = 3
                        nextButton.isEnabled = true
                    }
                    optionFive.id -> {
                        question1.userAnswer = 4
                        nextButton.isEnabled = true
                    }
                }
            }

            previousButton.setOnClickListener {
                if (questionNumber > 0)
                    launcher?.launchQuestionFragment(questionNumber.dec())
                else {
                    Toast.makeText(context, "You're on the first question!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            nextButton.setOnClickListener {
                if (questionNumber == 4)
                    launcher?.launchResultFragment()
                else {
                    launcher?.launchQuestionFragment(questionNumber.inc())
                }
            }
            toolbar.setNavigationOnClickListener {
                if (questionNumber > 0)
                    launcher?.launchQuestionFragment(questionNumber.dec())
                else {
                    Toast.makeText(context, "You're on the first question!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}