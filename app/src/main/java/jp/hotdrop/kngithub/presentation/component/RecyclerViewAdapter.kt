package jp.hotdrop.kngithub.presentation.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {

    private val list: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = list.size

    fun getAll(): List<T> = list

    fun getItem(index: Int) = list[index]

    fun addAll(items: List<T>) {
        list.addAll(items)
    }

    class BindingHolder<out T: ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)) {
        val binding: T? = DataBindingUtil.bind(itemView)
    }
}

