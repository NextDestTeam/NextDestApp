package com.nextdest.nextdest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nextdest.model.Event;
import com.nextdest.service.EventService;
import com.nextdest.view.model.CommentViewModel;
import com.nextdest.adapter.CommentAdpter;
import com.nextdest.form.EventForm;
import com.nextdest.service.EventFormService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class SelectedEventActivity extends AppCompatActivity implements OnMapFragmentReadyCallback {

    public static final String EXTRA_EVENT_CLICKED = "EXTRA_EVENT_CLICKED";

    TextView tvName;
    ImageView ivPhoto;
    TextView tvShortDescription;
    TextView tvLocation;
    TextView tvCost;
    TextView tvDate;
    TextView tvDescription;
    /*Button btEdit;*/
    EventForm form;
    Button btloc;
    RecyclerView rvComments;
    ImageButton ibComment;
    private TextView tvReviews;
    private RatingBar rbRating;
    private EventForm eventForm;
    List<CommentViewModel> listComment;
    private Event event;
    private CommentAdpter commentAdapter;
    MapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_activity);

        tvName = (TextView) findViewById(R.id.tvSelectedActivityTitle);
        ivPhoto = (ImageView) findViewById(R.id.ivSelectedActivityPhoto);
        tvShortDescription = (TextView) findViewById(R.id.tvSelectedActivityShortDescription);
        tvDescription = (TextView) findViewById(R.id.tvSelectedActivityDescription);
        tvLocation = (TextView) findViewById(R.id.tvSelectedActivityLocation);
        tvCost = (TextView) findViewById(R.id.tvSelectedActivityCost);
        tvDate = (TextView) findViewById(R.id.tvSelectedActivityDate);
        /*btEdit = (Button) findViewById(R.id.btSelectActivityEdit);*/
        btloc = (Button) findViewById(R.id.loc);
        //mapView = findViewById(R.id.selectedActivityMapView);
        ibComment = findViewById(R.id.ibComment);


        rvComments = findViewById(R.id.rvSelectedActivityComments);
        tvReviews = findViewById(R.id.tvSelectedActivityEvaluationCounter);
        rbRating = findViewById(R.id.rbSelectedActivityRatingBar);

        rvComments.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvComments.setLayoutManager(llm);


        Intent intent = getIntent();
        int idEvent = intent.getIntExtra("idEvent",0);
        event = EventService.getInstance().load(idEvent);
        tvName.setText(event.getName());


        ivPhoto.setImageDrawable(new BitmapDrawable(
                BitmapFactory.decodeByteArray(event.getPhoto(), 0, event.getPhoto().length)));

        tvShortDescription.setText(event.getShortDescription());
        tvCost.setText(String.format("$%.2f",event.getCost()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        tvDate.setText(sdf.format(event.getDate()));
        tvLocation.setText(event.getLocation());

        listComment = new ArrayList<>();


        commentAdapter = new CommentAdpter(listComment);
        rvComments.setAdapter(commentAdapter);
        updateComments();

        ibComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance(1,1);
                commentDialogFragment.show(fragmentManager,"COMMENT_DIALOG_FRAGMENT");
                fragmentManager.executePendingTransactions();
                commentDialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        updateComments();
                    }
                });
            }
        });

        /*Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_EVENT_CLICKED,0);
        form =  EventFormService.getInstance().load(id);
        loadForm(form);
        */
        /*btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EventFormActivity.class);
                i.putExtra(EXTRA_EVENT_CLICKED,form.getId());
                startActivity(i);
            }
        });*/


//        btloc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String location=tvLocation.getText().toString();
//                Intent intent =new Intent(SelectedEventActivity.this,MapsActivity.class);
//                intent.putExtra("loc",location);
//                startActivity(intent);
//            }
//        });
    }

    private void updateComments() {

        float avg = 0;

        listComment.clear();
        for (int i=0; i< event.getCommentList().size(); i++) {
            CommentViewModel commentViewModel = new CommentViewModel();
            commentViewModel.setComment(event.getCommentList().get(i).getComment());
            commentViewModel.setRating(event.getRatingList().get(i).getRating());
            avg = (avg+event.getRatingList().get(i).getRating())/2;
            BitmapDrawable drawable = (BitmapDrawable) this.getResources().getDrawable(R.drawable.barca);
            commentViewModel.setPhoto(drawable);
            listComment.add(commentViewModel);
        }

        avg*=2;

        rbRating.setRating(avg);

        tvReviews.setText(String.format("%d %s",event.getCommentList().size(),
                getResources().getString(R.string.reviews)));


        commentAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume(){
        super.onResume();
        updateComments();
    }


    private void loadForm(EventForm form) {
        tvName.setText(form.getName());
        tvShortDescription.setText(form.getShortDescription());
        tvDescription.setText(form.getDescription());
        try {
            tvDate.setText(DateFormat
                    .getDateFormat(getApplicationContext())
                    .format(form.getDate()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        tvCost.setText(String.valueOf(form.getCost()));
        tvLocation.setText(form.getLocation());
        Bitmap bMap = BitmapFactory.decodeByteArray(form.getPhoto(), 0, form.getPhoto().length);
        ivPhoto.setImageBitmap(bMap);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof MapFragment) {
            MapFragment mapFragmentFragment = (MapFragment) fragment;
            mapFragmentFragment.setOnMapFragmentReadyCallback(this);
        }
    }


    @Override
    public void mapFragmentReady(MapFragment mapFragment) {
        mapFragment.locate(event.getLocation(),event.getName());
    }
}
