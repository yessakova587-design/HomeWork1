package com.example.homework1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(initialCount: Int) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    private val items = MutableList(initialCount) { it }
    private val deletedItems = mutableListOf<Int>()

    // Номер следующего нового элемента
    private var nextNumber = initialCount

    // Восстановление состояния после поворота
    constructor(
        savedItems: ArrayList<Int>,
        savedDeletedItems: ArrayList<Int>,
        savedNextNumber: Int
    ) : this(0) {
        items.addAll(savedItems)
        deletedItems.addAll(savedDeletedItems)
        nextNumber = savedNextNumber
    }

    class NumberViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val container: View = itemView
        val number: TextView =
            itemView.findViewById(R.id.itemNumber)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_square, parent, false)

        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NumberViewHolder,
        position: Int
    ) {
        val number = items[position]

        holder.number.text = number.toString()

        val color = if (number % 2 == 0) {
            R.color.red_item
        } else {
            R.color.blue_item
        }

        holder.container.setBackgroundResource(color)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Удаление первого элемента
    fun removeFirstItem() {
        if (items.isEmpty()) return

        val removedItem = items.removeAt(0)
        deletedItems.add(removedItem)

        notifyItemRemoved(0)
        notifyItemRangeChanged(0, items.size)
    }

    // Добавление: сначала возвращаем удалённые,
    // затем создаём новые элементы
    fun addItem() {

        if (deletedItems.isNotEmpty()) {

            val restoredItem = deletedItems.removeAt(0)
            items.add(restoredItem)

        } else {

            items.add(nextNumber)
            nextNumber++
        }

        notifyItemInserted(items.lastIndex)
    }

    fun getItems(): List<Int> = items.toList()

    fun getDeletedItems(): List<Int> = deletedItems.toList()

    fun getNextNumber(): Int = nextNumber
}
