package com.example.todolist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvTasks = findViewById<ListView>(R.id.lvTasks)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)

        val taskList = mutableListOf<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        lvTasks.adapter = adapter

        fabAdd.setOnClickListener {
            val input = EditText(this)
            input.hint = ""

            AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(input)
                .setPositiveButton("Add") { _, _ ->
                    val task = input.text.toString()
                    if (task.isNotEmpty()) {
                        taskList.add(task)
                        adapter.notifyDataSetChanged()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        lvTasks.setOnItemClickListener { _, _, position, _ ->
            val taskName = taskList[position]
            AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete \"$taskName\"?")
                .setPositiveButton("Delete") { _, _ ->
                    taskList.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}