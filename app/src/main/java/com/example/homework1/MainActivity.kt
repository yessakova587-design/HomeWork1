package com.example.homework1

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var adapter: NumberAdapter

    companion object {
        private const val STATE_ITEMS = "items"
        private const val STATE_DELETED_ITEMS = "deleted_items"
        private const val STATE_NEXT_NUMBER = "next_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)

        val savedItems =
            savedInstanceState?.getIntegerArrayList(STATE_ITEMS)

        val savedDeletedItems =
            savedInstanceState?.getIntegerArrayList(STATE_DELETED_ITEMS)

        adapter = if (savedItems != null) {
            NumberAdapter(
                savedItems,
                savedDeletedItems ?: arrayListOf(),
                savedInstanceState.getInt(STATE_NEXT_NUMBER)
            )
        } else {
            NumberAdapter(
                resources.getInteger(R.integer.initial_item_count)
            )
        }

        val columns = resources.getInteger(R.integer.grid_columns)

        recyclerView.layoutManager =
            GridLayoutManager(this, columns)

        recyclerView.adapter = adapter

        // Кнопка добавления
        addButton.setOnClickListener {
            adapter.addItem()
        }

        // Кнопка удаления
        deleteButton.setOnClickListener {
            adapter.removeFirstItem()
        }
    }

    // Сохранение состояния при повороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntegerArrayList(
            STATE_ITEMS,
            ArrayList(adapter.getItems())
        )

        outState.putIntegerArrayList(
            STATE_DELETED_ITEMS,
            ArrayList(adapter.getDeletedItems())
        )

        outState.putInt(
            STATE_NEXT_NUMBER,
            adapter.getNextNumber()
        )
    }
}
