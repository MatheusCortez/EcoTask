import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecotask_.R
import com.example.ecotask_.adapter.TaskItemClickListener
import com.example.ecotask_.model.Tarefa
import com.example.ecotask_.viewModel.MainViewModel

class TarefaAdapter(
    private val taskItemClickListener: TaskItemClickListener,
    private val mainViewModel: MainViewModel
):
    RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listTarefas = emptyList<Tarefa>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemtarefa, parent, false)

        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefas[position]

        holder.textViewNome.text=tarefa.name
        holder.textViewStatus.text=tarefa.status
        holder.textViewData.text=tarefa.dueDate
        holder.textViewResponsavel.text=tarefa.assignetTo

        holder.iconDelete.setOnClickListener {
            mainViewModel.deleteTarefa(tarefa)
        }

        holder.iconEditar.setOnClickListener {
            taskItemClickListener.onTaskClicked(tarefa)
        }
    }


    fun setData(tasklist:List<Tarefa>){
        this.listTarefas = tasklist
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return listTarefas.size
    }

    class TarefaViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

        val textViewNome = itemview.findViewById<TextView>(R.id.nomeTarefa)
        val textViewData = itemview.findViewById<TextView>(R.id.dataTarefa)
        val textViewStatus = itemview.findViewById<TextView>(R.id.statusTarefa)
        val textViewResponsavel = itemview.findViewById<TextView>(R.id.textResponsavel)
        val iconEditar = itemview.findViewById<ImageView>(R.id.iconEdit)
        val iconDelete = itemview.findViewById<ImageView>(R.id.iconDelete)


    }


}