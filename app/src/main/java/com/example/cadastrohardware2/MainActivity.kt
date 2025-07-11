package com.example.cadastrohardware2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cadastrohardware2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Variável para usar ViewBinding com o layout activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Instância do banco de dados Room
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o banco de dados local
        db = AppDatabase.getDatabase(this)

        // Botão de salvar peça
        binding.btnSalvar.setOnClickListener {
            salvarPeca()
        }

        // Botão para ver lista de peças cadastradas
        binding.btnVerPecas.setOnClickListener {
            val intent = Intent(this, ListagemActivity::class.java)
            startActivity(intent) // Corrigido: estava sem parâmetro
        }
    }

    // Função para validar e salvar a peça no banco
    private fun salvarPeca() {
        val tipo = binding.etTipo.text.toString().trim()
        val marca = binding.etMarca.text.toString().trim()
        val capacidade = binding.etCapacidade.text.toString().trim()
        val modelo = binding.etModelo.text.toString().trim()

        // Validação dos campos obrigatórios
        if (tipo.isEmpty() || marca.isEmpty() || capacidade.isEmpty() || modelo.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val novaPeca = PecaHardware(
            tipo = tipo,
            marca = marca,
            capacidade = capacidade,
            modelo = modelo
        )

        // Inserção no banco com coroutine
        lifecycleScope.launch {
            db.pecaDao().inserir(novaPeca)
            Toast.makeText(this@MainActivity, "Peça salva com sucesso!", Toast.LENGTH_SHORT).show()
            limparCampos()
        }
    }

    // Função para limpar os campos após salvar
    private fun limparCampos() {
        binding.etTipo.text.clear()
        binding.etMarca.text.clear()
        binding.etCapacidade.text.clear()
        binding.etModelo.text.clear()
    }
}
