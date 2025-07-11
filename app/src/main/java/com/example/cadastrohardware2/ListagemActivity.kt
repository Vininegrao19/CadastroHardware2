package com.example.cadastrohardware2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastrohardware2.databinding.ActivityListagemBinding
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

class ListagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListagemBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: PecaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        adapter = PecaAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            val lista = db.pecaDao().listarTodas()
            adapter.submitList(lista)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}