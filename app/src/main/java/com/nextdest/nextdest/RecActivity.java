package com.nextdest.nextdest;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nextdest.adapter.CommentAdpter;
import com.nextdest.adapter.ReactionAdapter;
import com.nextdest.form.EventForm;
import com.nextdest.model.Event;
import com.nextdest.model.Reaction;
import com.nextdest.model.ReactionType;
import com.nextdest.service.ReactionService;
import com.nextdest.view.model.CommentViewModel;
import com.nextdest.view.model.ReactionViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecActivity extends AppCompatActivity implements OnMapFragmentReadyCallback, AdapterView.OnItemSelectedListener {

    TextView tvName;
    ImageView ivPhoto;
    TextView tvShortDescription;
    TextView tvLocation;
    TextView tvCost;
    TextView tvDate;
    TextView tvDescription;
    Button btloc;
    RecyclerView rvComments;
    ImageButton ibComment;
    private TextView tvReviews;
    private RatingBar rbRating;
    List<CommentViewModel> listComment;
    List<ReactionViewModel> reactionViewModelList;
    private CommentAdpter commentAdapter;
    private Spinner spReaction;
    MapFragment mapFragment;
    ReactionAdapter adapter;
    Row row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);
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
        rbRating.setVisibility(View.GONE);

        spReaction = findViewById(R.id.spActivitySelectedReaction);

        spReaction.setOnItemSelectedListener( this);

       createReactionView();

        rvComments.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvComments.setLayoutManager(llm);


        Intent intent = getIntent();
        tvName.setText(intent.getCharSequenceExtra("name"));
        ivPhoto.setImageResource(intent.getIntExtra("image",R.drawable.barca));
        tvShortDescription.setText(intent.getCharSequenceExtra("detail"));
        tvDescription.setText(intent.getCharSequenceExtra("detail"));
        //tvCost.setText(intent.getIntExtra("Price",10));
        tvDate.setText(intent.getCharSequenceExtra("date"));
        tvLocation.setText(intent.getCharSequenceExtra("place"));

        listComment = new ArrayList<>();


        commentAdapter = new CommentAdpter(listComment);
        rvComments.setAdapter(commentAdapter);
        //updateComments();

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
                       // updateComments();
                    }
                });
            }
        });




    }


    private void createReactionView() {

        reactionViewModelList  = new ArrayList<>();

        ReactionViewModel notInterested = new ReactionViewModel();
        notInterested.setText(getResources().getString(R.string.not_interested));
        notInterested.setCode(ReactionType.NO_WAY_TO_GO);
        notInterested.setDrawable(getResources().getDrawable(R.mipmap.ic_thumb_not_interested_round));

        ReactionViewModel interested = new ReactionViewModel();
        interested.setText(getResources().getString(R.string.interested));
        interested.setCode(ReactionType.INTERESTED);
        interested.setDrawable(getResources().getDrawable(R.mipmap.ic_thumb_interested_round));

        ReactionViewModel maybe = new ReactionViewModel();
        maybe.setText(getResources().getString(R.string.maybe
        ));
        maybe.setCode(ReactionType.MAY_GO);
        maybe.setDrawable(getResources().getDrawable(R.mipmap.ic_thumb_may_be_round));

        reactionViewModelList.add(notInterested);
        reactionViewModelList.add(interested);
        reactionViewModelList.add(maybe);

        adapter = new ReactionAdapter(this.getApplicationContext(),
                R.layout.reaction_item,reactionViewModelList);

        adapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(R.layout.reaction_item);
        spReaction.setAdapter(adapter);
    }
 /*   private void updateComments() {

        float avg = 0;

        listComment.clear();
        for (int i=0; i< row.getCommentList().size(); i++) {
            CommentViewModel commentViewModel = new CommentViewModel();
            commentViewModel.setComment(row.getCommentList().get(i).getComment());
            //commentViewModel.setRating(event.getRatingList().get(i).getRating());
            //avg = (avg+event.getRatingList().get(i).getRating())/2;
            BitmapDrawable drawable = (BitmapDrawable) this.getResources().getDrawable(R.drawable.barca);
            commentViewModel.setPhoto(drawable);
            listComment.add(commentViewModel);
        }

        avg*=2;

        rbRating.setRating(avg);

        tvReviews.setText(String.format("%d %s",row.getCommentList().size(),
                getResources().getString(R.string.reviews)));


        commentAdapter.notifyDataSetChanged();

    }
    @Override
    public void onResume(){
        super.onResume();
       // updateComments();
    }*/

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof MapFragment) {
            MapFragment mapFragmentFragment = (MapFragment) fragment;
            mapFragmentFragment.setOnMapFragmentReadyCallback(this);
        }
    }


   @Override
    public void mapFragmentReady(MapFragment mapFragment) {
        mapFragment.locate(tvLocation.getText().toString(),tvName.getText().toString());
    }

  @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ReactionViewModel reactionViewModel = adapter.getItem(position);

        Reaction reaction = new Reaction();
        ReactionService reactionService = new ReactionService(getApplicationContext());
        reaction.setIdUser(Session.LoggedPerson.getId());
        //reaction.setIdActivity(reactionViewModel.getId());
        reaction.setReaction(reactionViewModel.getCode());
        reactionService.save(reaction);

    }

   // @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spReaction.setSelection(0);
    }
}
