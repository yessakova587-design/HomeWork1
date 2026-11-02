package com.example.homework1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(initialCount: Int) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    // Список номеров элементов
    private val items = MutableList(initialCount) { it }

    // Класс, который хранит ссылки на элементы интерфейса
    class NumberViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val container: View = itemView

        val number: TextView =
            itemView.findViewById(R.id.itemNumber)
    }

    // Создание нового квадратика
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_square, parent, false)

        return NumberViewHolder(view)
    }

    // Заполнение квадратика номером и цветом
    override fun onBindViewHolder(
        holder: NumberViewHolder,
        position: Int
    ) {

        val number = items[position]

        holder.number.text = number.toString()

        // Чётные числа красные, нечётные синие
        val color = if (number % 2 == 0) {
            R.color.red_item
        } else {
            R.color.blue_item
        }

        holder.container.setBackgroundResource(color)
    }

    // Количество элементов списка
    override fun getItemCount(): Int {
        return items.size
    }

    // Добавление нового элемента
    fun addItem() {

        val newNumber = items.size

        items.add(newNumber)

        notifyItemInserted(items.lastIndex)
    }
}