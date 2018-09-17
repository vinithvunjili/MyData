package data.volive.mydata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotificationAdapternew extends RecyclerView.Adapter<NotificationAdapternew.Myviewholder> {
    HomeScreen mainActivity;
    String[] invoice;
    String[] itemtype;
    String[] items;
    TextView t1, t2;

    public NotificationAdapternew(HomeScreen mainActivity, String[] invoice) {
        this.mainActivity = mainActivity;
        this.invoice = invoice;
    }


    @Override
    public NotificationAdapternew.Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notification, parent, false);
        return new NotificationAdapternew.Myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapternew.Myviewholder holder, int position) {

        t1.setText(invoice[position]);


    }

    @Override
    public int getItemCount() {
        return invoice.length;
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        public Myviewholder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.title);

        }
    }
}
