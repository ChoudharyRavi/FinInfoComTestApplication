package com.example.fininfocomtestapplication.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fininfocomtestapplication.R
import com.example.fininfocomtestapplication.adapter.NumberAdapter
import com.example.fininfocomtestapplication.databinding.ActivityMainBinding
import com.example.fininfocomtestapplication.utils.Utilities
import com.example.fininfocomtestapplication.viewmodel.NumberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NumberViewModel by viewModels<NumberViewModel>()
    private lateinit var numberAdapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setAdapter()
        setonClickListeners()
        setObserver()
    }

    private fun setAdapter() {
        numberAdapter = NumberAdapter()
        binding.rvNumbersList.adapter = numberAdapter
    }

    private fun setonClickListeners() {
        binding.apply {
            btnOddNumbers.apply {
                setOnClickListener {
                    setPatternsListener(
                        this,
                        Utilities.AppPatterns.ODD_NUMBER,
                        R.color.color_odd_numbers
                    )
                }
            }
            btnEvenNumbers.apply {
                setOnClickListener {
                    setPatternsListener(
                        this,
                        Utilities.AppPatterns.EVEN_NUMBER,
                        R.color.color_even_numbers
                    )
                }
            }
            btnPrimeNumbers.apply {
                setOnClickListener {
                    setPatternsListener(
                        this,
                        Utilities.AppPatterns.PRIME_NUMBER,
                        R.color.color_prime_numbers
                    )
                }
            }
            btnFiboNumbers.apply {
                setOnClickListener {
                    setPatternsListener(
                        this,
                        Utilities.AppPatterns.FIBONACCI_SEQUENCE,
                        R.color.color_fibonacci_numbers
                    )
                }
            }
            btnOddNumbers.performClick()
        }
    }

    private fun setPatternsListener(
        button: Button,
        patterns: Utilities.AppPatterns,
        backgroundColor: Int
    ) {
        button.apply {
            viewModel.setPatterns(patterns)
            resetPatterns()
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity, backgroundColor
                )
            )
        }
    }

    private fun setObserver() {
        viewModel.filteredNumbers.observe(this) { numbers ->
            numberAdapter.submitList(numbers)
        }
    }

    private fun resetPatterns() {
        binding.apply {
            btnOddNumbers.setBackgroundColor(Color.LTGRAY)
            btnEvenNumbers.setBackgroundColor(Color.LTGRAY)
            btnPrimeNumbers.setBackgroundColor(Color.LTGRAY)
            btnFiboNumbers.setBackgroundColor(Color.LTGRAY)
        }
    }
}