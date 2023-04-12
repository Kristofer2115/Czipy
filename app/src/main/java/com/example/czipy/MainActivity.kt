package com.example.czipy

import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var addRadioButton: RadioButton
    private lateinit var removeRadioButton: RadioButton
    private lateinit var vegetablesCheckBoxes: List<CheckBox>
    private lateinit var meatsCheckBoxes: List<CheckBox>
    private lateinit var breadCheckBoxes: List<CheckBox>
    private lateinit var chips: List<Chip>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        radioGroup = findViewById(R.id.modeRadioGroup)
        addRadioButton = findViewById(R.id.addRadioButton)
        removeRadioButton = findViewById(R.id.checkRadioButton)
        vegetablesCheckBoxes = listOf(
            findViewById(R.id.tomatoesCheckBox),
            findViewById(R.id.cucumbersCheckBox),
            findViewById(R.id.lettuceCheckBox)
        )
        meatsCheckBoxes = listOf(
            findViewById(R.id.hamCheckBox),
            findViewById(R.id.sausageCheckBox),
            findViewById(R.id.baconCheckBox)
        )
        breadCheckBoxes = listOf(
            findViewById(R.id.breadCheckBox),
            findViewById(R.id.rollsCheckBox),
            findViewById(R.id.croissantCheckBox)
        )
        chips = listOf(
            findViewById(R.id.vegetableChips),
            findViewById(R.id.meatChips),
            findViewById(R.id.breadChip)
        )

        //set initial visibility of checkboxes and chips
        for (checkBox in vegetablesCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
        }
        for (checkBox in meatsCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
        }
        for (checkBox in breadCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
        }
        for (chip in chips) {
            chip.visibility = Chip.GONE
        }


        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.addRadioButton -> {
                    showCheckboxes()
                    hideChips()
                }
                R.id.checkRadioButton -> {
                    hideCheckboxes()
                    showChips()
                }
            }
        }


        for (checkBox in vegetablesCheckBoxes + meatsCheckBoxes + breadCheckBoxes) {
            checkBox.setOnCheckedChangeListener { _, _ ->
                updateChips()
            }
        }
    }

    private fun showCheckboxes() {
        for (checkBox in vegetablesCheckBoxes) {
            checkBox.visibility = CheckBox.VISIBLE
        }
        for (checkBox in meatsCheckBoxes) {
            checkBox.visibility = CheckBox.VISIBLE
        }
        for (checkBox in breadCheckBoxes) {
            checkBox.visibility = CheckBox.VISIBLE
        }
    }

    private fun hideCheckboxes() {
        for (checkBox in vegetablesCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
            checkBox.isChecked = false
        }
        for (checkBox in meatsCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
            checkBox.isChecked = false
        }
        for (checkBox in breadCheckBoxes) {
            checkBox.visibility = CheckBox.GONE
            checkBox.isChecked = false
        }
    }

    private fun showChips() {
        for (chip in chips) {
            chip.visibility = Chip.VISIBLE
        }
    }

    private fun hideChips() {
        for (chip in chips) {
            chip.visibility = Chip.GONE
        }
    }

        private fun updateChips() {
            val selectedVegetables = vegetablesCheckBoxes.filter { it.isChecked }.map { it.text.toString() }
            val selectedMeats = meatsCheckBoxes.filter { it.isChecked }.map { it.text.toString() }
            val selectedBread = breadCheckBoxes.filter { it.isChecked }.map { it.text.toString() }





            if (selectedVegetables.isNotEmpty()) {
                val chipGroup = findViewById<ChipGroup>(R.id.vegetableChips)
                addChips(selectedVegetables, chipGroup, R.color.vegetableColor)
            }
            if (selectedMeats.isNotEmpty()) {
                val chipGroup = findViewById<ChipGroup>(R.id.meatChips)
                addChips(selectedMeats, chipGroup, R.color.meatColor)
            }
            if (selectedBread.isNotEmpty()) {
                val chipGroup = findViewById<ChipGroup>(R.id.breadChips)
                addChips(selectedBread, chipGroup, R.color.breadColor)
            }
        }

        private fun addChips(items: List<String>, chipGroup: ChipGroup, colorResId: Int) {
            for (item in items) {
                val chip = Chip(this)
                chip.text = item
                chip.isClickable = false
                chip.isCheckable = false
                chip.chipBackgroundColor = getColorStateList(colorResId)
                chipGroup.addView(chip)
            }
        }
    }