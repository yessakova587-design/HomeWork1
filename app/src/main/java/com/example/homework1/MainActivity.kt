package com.example.homework1

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var adapter: NumberAdapter

    companion object {
        private const val STATE_ITEM_COUNT = "item_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Находим элементы интерфейса
        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)

        // Получаем количество элементов
        val itemCount = savedInstanceState?.getInt(
            STATE_ITEM_COUNT
        ) ?: resources.getInteger(
            R.integer.initial_item_count
        )

        // Создаём адаптер
        adapter = NumberAdapter(itemCount)

        // Настраиваем количество колонок
        val columns = resources.getInteger(
            R.integer.grid_columns
        )

        recyclerView.layoutManager =
            GridLayoutManager(this, columns)

        recyclerView.adapter = adapter

        // Обработка нажатия кнопки
        addButton.setOnClickListener {
            adapter.addItem()
        }
    }

    // Сохранение количества элементов перед поворотом
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(
            STATE_ITEM_COUNT,
            adapter.itemCount
        )
    }
}