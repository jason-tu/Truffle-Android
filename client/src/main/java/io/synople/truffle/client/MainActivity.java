package io.synople.truffle.client;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import io.synople.truffle.common.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rvID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Ticket> tickets = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(tickets);
        recyclerView.setAdapter(adapter);

        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());
        tickets.add(new Ticket());

        adapter.notifyDataSetChanged();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<Ticket> tickets;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView01;
            public TextView mTextView02;
            public TextView mTextView03;
            public TextView mTextView04;

            public MyViewHolder(TextView v, TextView w, TextView x, TextView y) {
                super(ConstraintLayout);

                mTextView01 = v;
                mTextView02 = w;
                mTextView03 = x;
                mTextView04 = y;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(List<Ticket> ticket) {
            tickets = ticket;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_ticket, parent, false);

            return new MyViewHolder(
                    (TextView) v.findViewById(R.id.tvRestaurantNameID), (TextView) v.findViewById(R.id.tvItemID),
                    (TextView) v.findViewById(R.id.tvCostID), (TextView) v.findViewById(R.id.tvDateID));

        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView01.setText(tickets.get(position).toString());
            holder.mTextView02.setText(tickets.get(position).toString());
            holder.mTextView03.setText(tickets.get(position).toString());
            holder.mTextView04.setText(tickets.get(position).toString());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return tickets.size();
        }
    }

}
