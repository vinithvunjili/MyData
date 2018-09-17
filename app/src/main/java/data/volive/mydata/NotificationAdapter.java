package data.volive.mydata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




/**
 * Created by INTEX on 10/21/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Myviewholder> {
    MainActivity mainActivity;
    String[] invoice;
    String[] itemtype;
    String[] items;
    TextView t1,t2;

    public NotificationAdapter(MainActivity mainActivity, String[] invoice) {
        this.mainActivity=mainActivity;
        this.invoice=invoice;
    }


    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notifications, parent, false);
        return new Myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {

        t1.setText(invoice[position]);


    }

    @Override
    public int getItemCount() {
        return invoice.length;
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        public Myviewholder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.title);

        }
    }
}


