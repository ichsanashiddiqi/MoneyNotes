package com.zzz.moneyspentnotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzz.moneyspentnotes.DetailMoneyNote;
import com.zzz.moneyspentnotes.R;
import com.zzz.moneyspentnotes.models.MoneyNotes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoneyNotesAdapter extends RecyclerView.Adapter<MoneyNotesAdapter.MoneyNotesViewHolder> {
    private List<MoneyNotes> moneyNotesList;
    private Context context;

    public MoneyNotesAdapter(Context context,List<MoneyNotes> moneyNotesList) {
        this.moneyNotesList = moneyNotesList;
        this.context = context;
    }

    @Override
    public MoneyNotesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_moneynotes,viewGroup,false);
        return new MoneyNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyNotesViewHolder holder, final int position) {
        //assigning moneyNoteslist to taskclass
        final MoneyNotes moneyNotesClass = moneyNotesList.get(position);
        //set namaPengeluaran to textview
        holder.namaPengeluaran.setText(moneyNotesClass.getNama_pengeluaran());
        //set tglPengeluaran to textview
        holder.tanggalPengeluaran.setText(moneyNotesClass.getTanggal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detail = new Intent(v.getContext(),DetailMoneyNote.class);
                detail.putExtra("idMoneyNote",String.valueOf(position+1));
                v.getContext().startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (moneyNotesList != null)?moneyNotesList.size():0;
    }

    public class MoneyNotesViewHolder extends RecyclerView.ViewHolder {
        TextView namaPengeluaran,tanggalPengeluaran;
        public MoneyNotesViewHolder( View itemView) {
            super(itemView);
            namaPengeluaran = itemView.findViewById(R.id.namaPengeluaran);
            tanggalPengeluaran = itemView.findViewById(R.id.tglPengeluaran);
        }
    }
}
