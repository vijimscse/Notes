package com.obvious.notes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obvious.notes.db.Note

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NoteListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NoteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteListFragment : Fragment() {
    internal var listener: OnFragmentInteractionListener? = null

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NoteListAdapter(activity as Context, listener)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        observeNotes()
    }

    private fun observeNotes() {
        activity?.let {
            noteViewModel.allNotes.observe(it, Observer { notes ->
                // Update the cached copy of the notes in the adapter.
                notes?.let { adapter.setNotes(it) }
            })
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(note: Note) {
        listener?.onFragmentInteraction(note)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(note: Note)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NoteListFragment.
         */
        @JvmStatic
        fun newInstance() = NoteListFragment()
    }
}
