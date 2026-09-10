package com.emulador.xmb

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var categoryRecyclerView: RecyclerView
    private var currentColumnIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryRecyclerView = findViewById(R.id.xmbCategories)
        
        // Define o layout horizontal para parecer a barra do PS3
        categoryRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        
        // Aqui você chamará o adaptador que preenche os ícones (Configurações, NDS, etc.)
    }

    // Intercepta os comandos do controle remoto da Android TV ou d-pad do celular
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                // Move o menu XMB para a esquerda
                currentColumnIndex = maxOf(0, currentColumnIndex - 1)
                categoryRecyclerView.smoothScrollToPosition(currentColumnIndex)
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                // Move o menu XMB para a direita
                currentColumnIndex += 1
                categoryRecyclerView.smoothScrollToPosition(currentColumnIndex)
                return true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                // Sobe na lista de jogos do NDS
                return true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                // Desce na lista de jogos do NDS
                return true
            }
            KeyEvent.KEYCODE_BUTTON_A, KeyEvent.KEYCODE_ENTER -> {
                // Abre o jogo selecionado usando o núcleo do NDS (.so)
                launchGame()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun launchGame() {
        // Lógica para chamar o carregador em C++ do NDS que criamos anteriormente
    }
}
