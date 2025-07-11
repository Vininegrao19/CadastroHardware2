package com.example.cadastrohardware2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrohardware2.databinding.ItemPecaBinding

class PecaAdapter : RecyclerView.Adapter<PecaAdapter.PecaViewHolder>() {

    private var listaPecas: List<PecaHardware> = emptyList()

    fun submitList(lista: List<PecaHardware>) {
        listaPecas = lista
        notifyDataSetChanged()
    }

    // ViewHolder que conecta o layout ao RecyclerView
    class PecaViewHolder(val binding: ItemPecaBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Método obrigatório: criar cada célula da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PecaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPecaBinding.inflate(layoutInflater, parent, false)
        return PecaViewHolder(binding)
    }

    // Método obrigatório: preencher cada célula com os dados da peça
    override fun onBindViewHolder(holder: PecaViewHolder, position: Int) {
        val peca = listaPecas[position]
        holder.binding.tvTipo.text = peca.tipo

        // Melhor prática: substituindo concatenação direta por uma String resource
        holder.binding.tvDescricao.text =
            "${peca.marca} • ${peca.capacidade} • ${peca.modelo}"
    }

    // Método obrigatório: retorna o tamanho da lista
    override fun getItemCount(): Int = listaPecas.size
}
