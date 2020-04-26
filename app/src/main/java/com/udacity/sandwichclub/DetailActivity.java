package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView placeOfOriginTextView = (TextView) findViewById(R.id.origin_tv);
        placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());

        TextView descriptionTextView = (TextView) findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());

        TextView ingredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        StringBuffer buffer = new StringBuffer();
        for(String  ingredient : sandwich.getIngredients()) {
            buffer.append(ingredient).append("\n");
        }
        ingredientsTextView.setText(buffer.toString());

        TextView alsoKnownTextView = (TextView) findViewById(R.id.also_known_tv);

        StringBuffer buffer2 = new StringBuffer();
        for(String alsoKnownAs : sandwich.getAlsoKnownAs()) {
            buffer2.append(alsoKnownAs).append("\n");
        }
        alsoKnownTextView.setText(buffer2.toString());
    }
}
