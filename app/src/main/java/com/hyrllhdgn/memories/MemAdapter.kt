package com.hyrllhdgn.memories

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyrllhdgn.memories.databinding.RecyclerRowBinding

class MemAdapter(private val memList : ArrayList<Mem>) : RecyclerView.Adapter<MemAdapter.MemHolder>() {
    class MemHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemHolder(binding)
    }

    override fun onBindViewHolder(holder: MemHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = memList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,MemActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id",memList[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return memList.size
    }

}