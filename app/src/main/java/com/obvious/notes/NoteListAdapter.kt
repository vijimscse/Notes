package com.obvious.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.obvious.notes.db.Note
import com.obvious.notes.utils.DateUtils

class NoteListAdapter(
    var context: Context,
    var listener: NoteListFragment.OnFragmentInteractionListener?
) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of notes

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener?.onFragmentInteraction(notes[adapterPosition])
            }
        }
        val titleView: TextView = itemView.findViewById(R.id.title)
        val descriptionView: TextView = itemView.findViewById(R.id.description)
        val dateView: TextView = itemView.findViewById(R.id.createdDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleView.text = note.title
        holder.descriptionView.text = note.description
        holder.dateView.text = context.getString(R.string.created_text) + " " +
                DateUtils.toSimpleString(note.createdTime)
    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}