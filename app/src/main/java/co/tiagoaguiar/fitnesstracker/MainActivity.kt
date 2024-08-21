package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnItemClickListener {

    //private lateinit var btnImc: LinearLayout

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId =  R.drawable.baseline_accessibility_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId =  R.drawable.baseline_boy_24,
                textStringId = R.string.label_tmb,
                color = Color.YELLOW
            )
        )

        //1) o layout XML
        //2) a onde a recycleview vai aparecer (tela principal, tela cheia)
        //3) logica - conectar o xml da celula dentro do recycleView + a sua quantidade de elementos dinamicos
        val adapter = MainAdapter(mainItems,this)
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)


        // clase para administrar a recyclerview e suas celulas (os seus layouts de itens)
        //adapter ->

//        btnImc = findViewById(R.id.btn_imc)
//
//        btnImc.setOnClickListener{
//            val i = Intent(this, ImcActivity::class.java)
//            startActivity(i)
//        }
    }

    override fun onClick() {
        Log.i("teste", "clicou!")
    }

    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val onItemClickListener: OnItemClickListener
        ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        // 1 - Qual é o layout XML da celula especifica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // 2 - disparado toda vez que houver uma rolagem na tela e for necessario trocar o conteudo da celula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }


        // 3 - Informar quantas celulas essa listagem terá
        override fun getItemCount(): Int {
            return mainItems.size
        }

        // é a classe da celula em si!
        private inner class MainViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            fun bind(item: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    OnItemClickListener.onClick()

//                    val intent = Intent(this, ImcActivity::class.java)
//                    startActivity(intent)

                }
            }
        }

    }





    // 3 maneiras de clicar(escutar eventos de click) usando celular (viewholder) activities
    //1. implementação de inteface
    //2. objetos anonimos
    //3. funcional

}